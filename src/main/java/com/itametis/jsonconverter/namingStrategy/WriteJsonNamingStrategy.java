package com.itametis.jsonconverter.namingStrategy;

import com.google.gson.FieldNamingStrategy;
import com.itametis.jsonconverter.annotation.JsonField;
import java.lang.reflect.Field;

/**
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class WriteJsonNamingStrategy implements FieldNamingStrategy {

    @Override
    public String translateName(Field f) {
        if (f.isAnnotationPresent(JsonField.class)
            && f.getAnnotation(JsonField.class).name() != null && !f.getAnnotation(JsonField.class).name().isEmpty()) {
            return f.getAnnotation(JsonField.class).name();
        }

        return f.getName();
    }

}
