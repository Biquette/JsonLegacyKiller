package com.itametis.jsonconverter.pathstrategy.flattener;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.itametis.jsonconverter.exception.JsonException;
import com.itametis.jsonconverter.pathstrategy.JsonElementProxy;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class JsonArrayFlattener extends Flattener<JsonArray> {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonArrayFlattener.class.getSimpleName());


    public JsonArrayFlattener(Flattenizer flattenizer) {
        super(flattenizer);
    }


    @Override
    protected void setChildhood(JsonElementProxy element) {
        //Do nothing.
    }


    @Override
    protected void addInJsonFlattenedTree(JsonElementProxy element) {
        //Do nothing.
    }


    @Override
    protected void flatten(JsonArray jsonElement, JsonElementProxy parent) throws JsonException {
        for (int i = 0; i < jsonElement.size(); i++) {
            JsonElementProxy element = new JsonElementProxy(jsonElement.get(i), parent.getJsonName(), parent.getParent(), true);
            LOGGER.debug("'{}' is the new parent of element '{}'", element.getParent().getJsonName(), element.getJsonName());
            this.flattenizer.flatten(element);
        }
    }


    @Override
    protected void empty(JsonArray element) {
        LOGGER.debug("emptying json array {}", element.toString());
        List<JsonElement> toRemove = new ArrayList<>();
        for (int i = 0; i < element.size(); i++) {
            toRemove.add(element.get(i));
        }
        for (JsonElement elementToRemove : toRemove) {
            element.remove(elementToRemove);
        }
    }

}
