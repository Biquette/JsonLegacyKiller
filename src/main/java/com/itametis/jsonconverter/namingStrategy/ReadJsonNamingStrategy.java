package com.itametis.jsonconverter.namingStrategy;

import com.google.gson.FieldNamingStrategy;
import com.itametis.jsonconverter.annotation.JsonField;
import java.lang.reflect.Field;

/**
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class ReadJsonNamingStrategy implements FieldNamingStrategy {

    @Override
    public String translateName(Field f) {
        if (f.isAnnotationPresent(JsonField.class)) {
            JsonField annotation = f.getAnnotation(JsonField.class);

            if (annotation.nameInJson() != null && !annotation.nameInJson().isEmpty()) {
                return annotation.nameInJson();
            }
            else if (annotation.name() != null && !annotation.name().isEmpty()) {
                return annotation.name();
            }
        }

        return f.getName();
    }

}
