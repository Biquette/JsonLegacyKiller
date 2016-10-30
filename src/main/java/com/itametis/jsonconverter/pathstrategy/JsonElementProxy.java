package com.itametis.jsonconverter.pathstrategy;

import com.google.gson.JsonElement;
import com.itametis.jsonconverter.exception.JsonException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This proxy is a JsonElement and a Leaf to a local flattened tree.
 *
 * Each JsonElement has a name in the json tree.
 *
 * Each JsonElement has a parent a list of children.
 *
 * A JsonElement might be part of a collection. It is then contained in a Array.
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class JsonElementProxy {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonElementProxy.class.getSimpleName());

    private final JsonElement element;

    private final String jsonName;

    private final JsonElementProxy parent;

    private final Map<String, JsonElementList> children;

    private boolean isPartOfACollection;


    /**
     * Constructor.
     *
     * @param element  the JsonElement being the new leaf in the tree.
     * @param jsonName its name
     * @param parent   its parent on top of him.
     */
    public JsonElementProxy(JsonElement element, String jsonName, JsonElementProxy parent) {
        this(element, jsonName, parent, false);
    }


    /**
     * Constructor.
     *
     * @param elementthe          JsonElement being the new leaf in the tree.
     * @param jsonName            its name
     * @param parent              its parent on top of him.
     * @param isPartOfACollection Indicates that the element is part of an Array.
     */
    public JsonElementProxy(JsonElement element, String jsonName, JsonElementProxy parent, boolean isPartOfACollection) {
        this.element = element;
        this.jsonName = jsonName;
        this.parent = parent;
        this.isPartOfACollection = isPartOfACollection;
        this.children = new HashMap();
    }


    /**
     * Returns the Json element in this leaf.
     * It is always a JsonObject, a JsonPrimitive or a JsonNull.
     *
     * @return the JsonElement.
     */
    public JsonElement getElement() {
        return element;
    }


    /**
     * Returns the name of the JsonElement.
     *
     * @return the name of the JsonElement.
     */
    public String getJsonName() {
        return jsonName;
    }


    /**
     * Returns the parent of the element. The parent is also a leaf in the tree.
     *
     * @return the parent of the element.
     */
    public JsonElementProxy getParent() {
        return parent;
    }


    /**
     * Returns the list of children under the elements.
     *
     * @return the children.
     */
    public Map<String, JsonElementList> getChildren() {
        return children;
    }


    /**
     * Indicates that the JsonElement is part of an Array.
     *
     * @return true if the JsonElement is part of an Array.
     */
    public boolean isPartOfACollection() {
        return isPartOfACollection;
    }


    /**
     * Add a child to the JsonElement.
     * A child is also a leaf of the tree.
     *
     * @param child the child to add to the JsonElement.
     */
    public void addChild(JsonElementProxy child) {
        if (!this.children.containsKey(child.getJsonName())) {
            this.children.put(child.getJsonName(), new JsonElementList());
        }
        this.children.get(child.getJsonName()).add(child);
    }


    /**
     * Remove the child from the jsonElement.
     *
     * @param element the element toRemove.
     *
     * @throws JsonException
     */
    public void removeChild(JsonElementProxy element) throws JsonException {
        if (this.children.containsKey(element.getJsonName())) {
            JsonElementList list = this.children.get(element.getJsonName());
            if (list.contains(element)) {
                list.remove(element);
            }
            else {
                throw new JsonException("Trying to remove child " + element.getJsonName() + " from element " + this.jsonName + " but it not contained in children list.");
            }
            if (list.isEmpty()) {
                this.children.remove(element.getJsonName());
            }
        }
        else {
            throw new JsonException("No child with name = " + element.getJsonName() + " in element " + this.jsonName);
        }
    }


    /**
     * Returns the type of the JsonElement.
     *
     * @return the type.
     *
     * @throws JsonException
     */
    public JsonElementType getElementType() throws JsonException {
        if (this.element == null) {
            throw new JsonException("the Json Element is null");
        }
        if (this.element.isJsonNull()) {
            return JsonElementType.JSONNULL;
        }
        else if (element.isJsonArray()) {
            return JsonElementType.JSONARRAY;
        }
        else if (element.isJsonObject()) {
            return JsonElementType.JSONOBJECT;
        }
        else if (element.isJsonPrimitive()) {
            return JsonElementType.JSONPRIMITIVE;
        }
        throw new JsonException("the Json Element is not one of the major four : " + this.element.toString());
    }


    /**
     * For testing purposes.
     *
     * @return the textual visualization of the object.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(jsonName).append(", collection : ").append(isPartOfACollection).append(", type : ");

        try {
            builder.append(this.getElementType().toString());
        }
        catch (JsonException ex) {
            builder.append("WRONG TYPE");
            LOGGER.error("Finding unknow type to a Json Element.", ex);
        }

        if (!this.children.isEmpty()) {
            builder.append(" {");
            for (Map.Entry<String, JsonElementList> child : children.entrySet()) {
                for (JsonElementProxy element : child.getValue()) {
                    builder.append("\n     ").append(element.toString());
                }
            }
            builder.append("\n}");
        }

        return builder.toString();

    }


    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.element != null && this.element.equals(obj)) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JsonElementProxy other = (JsonElementProxy) obj;
        if (!Objects.equals(this.element, other.element)) {
            return false;
        }
        return true;
    }

}
