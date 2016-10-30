package com.itametis.jsonconverter.pathstrategy.flattener;

import com.google.gson.JsonNull;
import com.itametis.jsonconverter.exception.JsonException;
import com.itametis.jsonconverter.pathstrategy.JsonElementProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class JsonNullFlattener extends Flattener<JsonNull> {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonNullFlattener.class.getSimpleName());


    public JsonNullFlattener(Flattenizer flattenizer) {
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
    protected void flatten(JsonNull jsonElement, JsonElementProxy parent) {
        //Do nothing.
    }


    @Override
    protected void empty(JsonNull element) {
        //Do nothing. Json null has no members.
    }

}
