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
