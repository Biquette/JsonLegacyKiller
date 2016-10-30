package com.itametis.jsonconverter.pathstrategy;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class JsonElementTypeTest {

    @Test
    public void textual_array() {
        Assert.assertEquals("JsonArray", JsonElementType.JSONARRAY.toString());
    }

    @Test
    public void textual_object() {
        Assert.assertEquals("JsonObject", JsonElementType.JSONOBJECT.toString());
    }

    @Test
    public void textual_null() {
        Assert.assertEquals("JsonNull", JsonElementType.JSONNULL.toString());
    }

    @Test
    public void textual_primitive() {
        Assert.assertEquals("JsonPrimitive", JsonElementType.JSONPRIMITIVE.toString());
    }
    
}
