package com.itametis.jsonconverter.pathstrategy.flattener;

import com.google.gson.JsonPrimitive;
import com.itametis.jsonconverter.exception.JsonException;
import com.itametis.jsonconverter.pathstrategy.JsonElementProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class JsonPrimitiveFlattener extends Flattener<JsonPrimitive> {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonPrimitiveFlattener.class.getSimpleName());


    public JsonPrimitiveFlattener(Flattenizer flattenizer) {
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
    protected void flatten(JsonPrimitive jsonElement, JsonElementProxy parent) throws JsonException {
        //Do nothing.
    }


    @Override
    protected void empty(JsonPrimitive element) {
        //Do nothing. Json primitive has no members.
    }

}
