package com.itametis.jsonconverter.pathstrategy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.itametis.jsonconverter.ignorestrategy.WriteIgnoreStrategy;
import com.itametis.jsonconverter.namingStrategy.WriteJsonNamingStrategy;
import java.lang.reflect.Type;

/**
 * The serialization is Standardized.
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class PathSerializer implements JsonSerializer<Object> {

    private Gson gson;


    /**
     * Constructor.
     * This class is able to change the structure of a GSon tree to change the mapping.
     */
    public PathSerializer() {
        this.gson = new GsonBuilder()
            .setFieldNamingStrategy(new WriteJsonNamingStrategy())
            .setExclusionStrategies(new WriteIgnoreStrategy())
            .create();
    }


    @Override
    public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
        return this.gson.toJsonTree(src, typeOfSrc);
    }

}
