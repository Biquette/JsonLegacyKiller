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
package com.itametis.jsonconverter.pathstrategy.flattener;

import com.itametis.jsonconverter.exception.JsonException;
import com.itametis.jsonconverter.pathstrategy.JsonElementList;
import com.itametis.jsonconverter.pathstrategy.JsonElementProxy;
import com.itametis.jsonconverter.pathstrategy.JsonElementType;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This flattenizer is able to execute a recursive flatten action on every member of a tree.
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class Flattenizer {

    private final static Logger LOGGER = LoggerFactory.getLogger(Flattener.class.getSimpleName());

    /**
     * The JsonTree that will contains the elements.
     */
    protected final HashMap<String, JsonElementList> jsonTree;

    private final HashMap<JsonElementType, Flattener> flattenersByElementType;


    /**
     * Each node of a Json tree does not flattens the same way.
     *
     * @return an hashMap containing the best methodology to apply to each type of node.
     */
    private HashMap<JsonElementType, Flattener> initializeFlatteners() {
        HashMap<JsonElementType, Flattener> toReturn = new HashMap<>();
        toReturn.put(JsonElementType.JSONARRAY, new JsonArrayFlattener(this));
        toReturn.put(JsonElementType.JSONNULL, new JsonNullFlattener(this));
        toReturn.put(JsonElementType.JSONOBJECT, new JsonObjectFlattener(this));
        toReturn.put(JsonElementType.JSONPRIMITIVE, new JsonPrimitiveFlattener(this));
        return toReturn;
    }


    /**
     * Constructor.
     */
    public Flattenizer() {
        this.jsonTree = new HashMap<>();
        this.flattenersByElementType = this.initializeFlatteners();
    }


    /**
     * Start the recursive flatten action starting from the the top element of the json tree.
     *
     * @param element the top element of the json tree.
     *
     * @return the flatten tree.
     *
     * @throws JsonException
     */
    public HashMap<String, JsonElementList> flatten(JsonElementProxy element) throws JsonException {
        Flattener flattener = this.flattenersByElementType.get(element.getElementType());

        flattener.setChildhood(element);
        flattener.addInJsonFlattenedTree(element);
        flattener.flatten(element.getElement(), element);
        flattener.empty(element.getElement());

        return this.jsonTree;
    }


    /**
     * Add a node in the json tree.
     *
     * @param proxy the element to add in the tree.
     *
     * @throws JsonException
     */
    public void addInJsonTree(JsonElementProxy proxy) throws JsonException {
        LOGGER.info("Adding element '{}' of type {} in json tree with parent {}", proxy.getJsonName(), proxy.getElementType().toString(), proxy.getParent() != null ? proxy.getParent().getJsonName() : "null");
        if (!this.jsonTree.containsKey(proxy.getJsonName())) {
            this.jsonTree.put(proxy.getJsonName(), new JsonElementList());
        }
        this.jsonTree.get(proxy.getJsonName()).add(proxy);
    }

}
