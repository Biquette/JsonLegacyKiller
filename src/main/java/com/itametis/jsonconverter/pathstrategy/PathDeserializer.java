/* This file is part of JsonLegacyKiller.
 *
 * JsonLegacyKiller is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JsonLegacyKiller is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JsonLegacyKiller.  If not, see <http://www.gnu.org/licenses/gpl.txt >.
 *
 * If you need to develop a closed-source software, please contact us
 * at 'social@itametis.com' to get a commercial version of JsonLegacyKiller,
 * with a proprietary license instead.
 */
package com.itametis.jsonconverter.pathstrategy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.itametis.jsonconverter.classpathscan.JsonMappingPackage;
import com.itametis.jsonconverter.classpathscan.ScannedClass;
import com.itametis.jsonconverter.classpathscan.ScannedField;
import com.itametis.jsonconverter.exception.JsonException;
import com.itametis.jsonconverter.ignorestrategy.ReadIgnoreStrategy;
import com.itametis.jsonconverter.namingStrategy.ReadJsonNamingStrategy;
import com.itametis.jsonconverter.pathstrategy.flattener.Flattenizer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @formatter:off
/**
 * Improved Deserialisation to change the structure of the Json before converting it into an object.
 *
 * Gson converts a String into a Json Tree. Each leaf is a JsonElement.
 * A JsonElement (abstract type) can be :
 * - A jsonObject : An Object as represented in the java World. This class would be represented as a JsonObject.
 * - A jsonPrimitive : like a int, a double or a String.
 * - A jsonArray : contains a list of other json element.
 * - A jsonNull : well... a null.
 *
 * A json tree looks globally like that :
 *
 * jsonObject''''''''''''''''''''''''''''''''''''
 * '''|''''''''''''''''''''''''''''''''''''''''''
 * '''|-----------------|---------------|''''''''
 * jsonObject'''''''jsonObject''''''jsonPrimitive
 * '''|'''''''''''''''''|''''''''''''''''''''''''
 * '''|'''''''''''''''''|---------------|''''''''
 * JsonArray''''''jsonPrimitive''''jsonPrimitive'
 * '''|''''''''''''''''''''''''''''''''''''''''''
 * '''|-----------------|---------------|''''''''
 * JsonObject'''''''JsonObject'''''''JsonObject''
 *
 * Each child of a JsonElement is called a member.
 * A JsonArray has a list of elements.
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
// @formatter:on
public class PathDeserializer implements JsonDeserializer<Object> {

    private final static Logger LOGGER = LoggerFactory.getLogger(PathDeserializer.class.getSimpleName());

    /**
     * We use Gson to do the conversion.
     */
    private final Gson gson;

    /**
     * The identified Objects that are flagged as misplaced.
     */
    private final HashMap<String, ScannedField> fieldsToMove = new HashMap<>();

    /**
     * The flattened Json tree. Map of json name -> the list of Json Element with this name.
     */
    private HashMap<String, JsonElementList> jsonTree = new HashMap<>();

    /**
     * The top element of the Json tree.
     */
    private JsonElementProxy topElement;


    /**
     * The Deserializer that change the structure of a Gson tree.
     */
    public PathDeserializer() {
        //Instanciate a Gson access to convert to modified tree.
        this.gson = new GsonBuilder()
            .setFieldNamingStrategy(new ReadJsonNamingStrategy())
            .setExclusionStrategies(new ReadIgnoreStrategy())
            .create();
    }


    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (this.isAJsonnableObject(typeOfT)) {

            //1. search what is flagged with path.
            this.searchForObjectsToMove(typeOfT);
            if (this.existsObjectToMove()) {

                //2. duplicate jsonTree with JsonName.
                this.flattenJsonTree(typeOfT, json);

                //3. move misplaced nodes
                this.moveMisplacedNodes();

                //4. rebuild tree
                this.rebuildTree(this.topElement);
            }
        }
        else {
            LOGGER.info("The class " + typeOfT.getTypeName() + " is not flagged as Jsonnable with @Jsonnable annotation.");
        }

        this.fieldsToMove.clear();
        this.jsonTree.clear();
        this.topElement = null;
        // The new structure is sent to Gson, to be converted in an object.
        return this.gson.fromJson(json, typeOfT);
    }


    /**
     * Indicated that the class type is a jsonnable object.
     *
     * @param type the class type
     *
     * @return true is the class is annotated with @Jsonnable.
     */
    private boolean isAJsonnableObject(Type type) {
        return JsonMappingPackage.getInstance().getJsonnableContent().containsKey(type);
    }


    /**
     * Search in the class details, the fields that may need to be moved.
     * The class panel is defined when initializing the JsonConverter class through the constructor.
     *
     * @param type the class type.
     */
    private void searchForObjectsToMove(Type type) {
        if (this.isAJsonnableObject(type)) {
            ScannedClass jsonnable = JsonMappingPackage.getInstance().getJsonnableContent().get(type);

            for (ScannedField field : jsonnable.getjSonFields()) {
                if (field.needsToBeMoved()) {
                    LOGGER.debug("Found Field to move : {} of type {}", field.getName(), field.getField().getType().getSimpleName());
                    this.fieldsToMove.put(field.getNameInJson(), field);
                }
                if (field.isCollectionOrMap()) {
                    this.searchForObjectsToMove(field.getParameters().get(0));
                }
                else {
                    this.searchForObjectsToMove(field.getField().getType());
                }
            }
        }
        else {
            LOGGER.debug("Found non jsonnable field {}", type.getTypeName());
        }
    }


    /**
     * Indicated that there is fields at the wrong place.
     *
     * @return true if there are identified fields at the wrong place.
     */
    private boolean existsObjectToMove() {
        return !this.fieldsToMove.isEmpty();
    }


    /**
     * Create a dictionary between the Json Element and its name.
     * Delete the links between the Json Elements.
     * The tree is then an HashMap and every element knows who is their children/parent.
     *
     * @param type    the class type.
     * @param element the JsonElement.
     */
    private void flattenJsonTree(Type type, JsonElement element) {
        if (this.isAJsonnableObject(type)) {

            //Element name :
            String jsonClassName = JsonMappingPackage.getInstance().getJsonnableContent().get(type).getjSonName();

            this.topElement = new JsonElementProxy(element, jsonClassName, null);

            Flattenizer flattenizer = new Flattenizer();
            LOGGER.debug("Flatten top element of the json tree '{}'.", jsonClassName);

            try {
                //Start a recursive flattening.
                this.jsonTree = flattenizer.flatten(this.topElement);
            }
            catch (JsonException ex) {
                throw new JsonParseException(ex);
            }
        }
        else {
            throw new JsonParseException("Element on top '" + type.getTypeName() + "' is not flagged as jsonnable with annotation @Jsonnable."
                                         + "It can not be analysed. Litteral conversion will be done.");
        }
    }


    /**
     * Relocate fields that must be placed somewhere else.
     *
     * @throws JsonParseException
     */
    private void moveMisplacedNodes() throws JsonParseException {
        for (Map.Entry<String, ScannedField> toMove : this.fieldsToMove.entrySet()) {
            for (JsonElementProxy proxyToMove : this.jsonTree.get(toMove.getKey())) {

                //1. We remove.
                this.removeField(proxyToMove, toMove);

                //2. We insert.
                this.insertField(proxyToMove, toMove);
            }
        }
    }


    /**
     * Remove a field from an indicated parent.
     *
     * @param toMove the combo jsonName - field information.
     */
    private void removeField(JsonElementProxy element, Map.Entry<String, ScannedField> toMove) {

        JsonElementList parents = getParentFromPath(toMove.getValue().getPathInJson(), element);
        for (JsonElementProxy parent : parents) {
            if (parent == null) {
                throw new JsonParseException("received null as a parent for element " + element.getJsonName() + " while deleting.");
            }
            try {
                parent.removeChild(element);
                LOGGER.info("Removed node {} from parent {} (jsonName)", element.getJsonName(), parent.getJsonName());
            }
            catch (JsonException ex) {
                throw new JsonParseException(ex);
            }
        }
    }


    /**
     * Insert a field to an indicated Parent.
     *
     * @param toMove        the combo jsonName - field information.
     * @param elementToMove the element to insert.
     */
    private void insertField(JsonElementProxy element, Map.Entry<String, ScannedField> toMove) {
        JsonElementList parents = getParentFromPath(toMove.getValue().getPathInCode(), element);
        for (JsonElementProxy parent : parents) {
            if (parent == null) {
                throw new JsonParseException("Received null as a parent for element " + element.getJsonName() + " while inserting.");
            }
            parent.addChild(element);
            LOGGER.info("Inserted node {} in parent {} (jsonName)", element.getJsonName(), parent.getJsonName());
        }
    }


    /**
     * Get the parent associated with the element according to the indicated path. The path is relative to the
     * position of the element.
     *
     * @param paths          the path.
     * @param currentElement the current element.
     *
     * @return the parents corresponding to the path.
     */
    private JsonElementList getParentFromPath(String[] paths, JsonElementProxy currentElement) {

        JsonElementList parents = new JsonElementList();
        parents.add(currentElement);
        for (String path : paths) {
            LOGGER.debug("Searching parent '{}' from {}", path, currentElement.getJsonName());
            for (JsonElementProxy parent : parents) {
                if (parent == null) {
                    this.returnErrorNoParent(path, parent);
                }

                parents.remove(parent);

                if ("".equals(path) || ".".equals(path)) { // Current
                    parents.add(parent.getParent());
                }
                else if ("..".equals(path)) { //On top
                    if (parent.getParent() == null) {
                        this.returnErrorNoParent(path, parent);
                    }
                    parents.add(parent.getParent().getParent());
                }
                else if (parent.getChildren().containsKey(path)) { //In children
                    parents.addAll(parent.getChildren().get(path));
                }
                else { //does not exists.
                    this.returnErrorNoParent(path, parent);
                }

                LOGGER.info("Indicating parent : {} found for element {} with path {}", (parent == null ? "null" : parent.getJsonName()), currentElement.getJsonName(), path);
            }
        }
        return parents;
    }


    /**
     * Return an error when looking for a parent.
     *
     * @param path   the path.
     * @param parent the parent.
     */
    private void returnErrorNoParent(String path, JsonElementProxy parent) {
        String availableList = ".., .";
        for (String name : parent.getChildren().keySet()) {
            availableList += ", " + name;
        }
        LOGGER.error("Searched child path '{}'. But available paths were : [{}]", path, availableList);
        throw new JsonParseException("Searched child path '" + path + "'. But available paths were : [" + availableList + "]");
    }


    /**
     * Rebuild a new Gson tree from flattened tree.
     *
     * @param node the top element.
     */
    private void rebuildTree(JsonElementProxy node) {
        if (node.getElement().isJsonObject()) {
            JsonObject nodeObject = node.getElement().getAsJsonObject();

            //For each child :
            for (Map.Entry<String, JsonElementList> entry : node.getChildren().entrySet()) {
                for (JsonElementProxy child : entry.getValue()) {

                    //If not part of a collection, then it is a primitive, a null or an object.
                    if (!child.isPartOfACollection()) {
                        nodeObject.add(child.getJsonName(), child.getElement());
                    }
                    //If part of a collection, then it is an array.
                    else {
                        //We create the array if it does not exists.
                        if (nodeObject.get(child.getJsonName()) == null) {
                            nodeObject.add(child.getJsonName(), new JsonArray());
                        }
                        //And add the child to the array.
                        JsonArray array = nodeObject.get(child.getJsonName()).getAsJsonArray();
                        array.add(child.getElement());
                    }
                    //We build the tree from the child.
                    this.rebuildTree(child);
                }
            }
        }
    }

}
