package com.itametis.jsonconverter.ignorestrategy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.itametis.jsonconverter.annotation.JsonField;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class ReadIgnoreStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipClass(Class<?> arg0) {
        return false;
    }


    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        JsonField annotation = f.getAnnotation(JsonField.class);
        return annotation == null || annotation.useType() == JsonField.UseType.WRITE;
    }

}
