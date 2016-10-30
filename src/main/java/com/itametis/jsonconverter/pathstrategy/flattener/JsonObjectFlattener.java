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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.itametis.jsonconverter.exception.JsonException;
import com.itametis.jsonconverter.pathstrategy.JsonElementProxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class JsonObjectFlattener extends Flattener<JsonObject> {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonObjectFlattener.class.getSimpleName());


    public JsonObjectFlattener(Flattenizer flattenizer) {
        super(flattenizer);
    }


    @Override
    protected void setChildhood(JsonElementProxy element) {
        this.addChildToParentOf(element);
    }

    @Override
    protected void addInJsonFlattenedTree(JsonElementProxy element) throws JsonException {
        this.flattenizer.addInJsonTree(element);
    }


    @Override
    protected void flatten(JsonObject element, JsonElementProxy currentElement) throws JsonException {
        for (Map.Entry<String, JsonElement> field : element.entrySet()) {
            LOGGER.debug("Found Json Leaf '{}' : {}, applied to parent {}", field.getKey(), field.getValue().toString(), currentElement.getJsonName());
            JsonElementProxy elementProxy = new JsonElementProxy(field.getValue(), field.getKey(), currentElement);
            this.flattenizer.flatten(elementProxy);
        }
    }


    /**
     * Delete all links with other json Elements.
     *
     * @param element
     */
    protected void empty(JsonObject element) {
        LOGGER.debug("emptying json object {}", element.toString());
        List<String> toRemove = new ArrayList<>();
        for (Map.Entry<String, JsonElement> field : element.entrySet()) {
            toRemove.add(field.getKey());
        }
        for (String elementToRemove : toRemove) {
            element.remove(elementToRemove);
        }
    }

}
