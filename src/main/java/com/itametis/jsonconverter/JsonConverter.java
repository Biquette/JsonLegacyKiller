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
package com.itametis.jsonconverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itametis.jsonconverter.classpathscan.JsonMappingPackage;
import com.itametis.jsonconverter.exception.JsonException;
import com.itametis.jsonconverter.pathstrategy.PathDeserializer;
import com.itametis.jsonconverter.pathstrategy.PathSerializer;
import java.io.IOException;

/**
 * Conversion class.
 * Will convert a class to Json or the opposite with a redefinition of the structure.
 *
 * All fields must be flagged with JsonField annotation.
 * The Jsonnable object class must be flagged with Jsonnable annotation.
 *
 * Uses the Gson API to do the convertion after the re-structuration.
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class JsonConverter {

    private Gson mapper;


    /**
     * Constructor.
     * Indicate a package name to scan the content, looking for jsonnable content.
     *
     * @param JsonnablePackage the package to scan.
     *
     * @throws JsonException
     */
    public JsonConverter(String JsonnablePackage) throws JsonException {
        this.setPackageToScan(JsonnablePackage);
        mapper = new GsonBuilder()
            .registerTypeHierarchyAdapter(Object.class, new PathDeserializer())
            .registerTypeHierarchyAdapter(Object.class, new PathSerializer())
            .create();
    }


    /**
     * Constructor.
     * On start, will scan all classpath looking for jsonnable content.
     *
     * @throws JsonException
     */
    public JsonConverter() throws JsonException {
        this(null);
    }


    /**
     * Convert a Json to an object.
     *
     * @param <T>  the type of object to convert to Json. This object must contain the @Jsonnable annotation.
     * @param json the json, string formatted.
     * @param type the type of object to create.
     *
     * @return the object converted from the json.
     */
    public <T> T convertFromJson(String json, Class<T> type) {
        return mapper.fromJson(json, type);
    }


    /**
     * Convert an Object To Json.
     *
     * @param <T>    the type of object to convert to Json. This object must contain the @Jsonnable annotation.
     * @param object the object to convert to json.
     *
     * @return the String Json corresponding to the object.
     */
    public <T> String convertToJson(T object) {
        return mapper.toJson(object);
    }


    /**
     * By default, scan all classpath.
     *
     * @param packageName the package name.
     *
     * @exception JsonException
     */
    public void setPackageToScan(String packageName) throws JsonException {
        JsonMappingPackage.getInstance().setPackageToScan(packageName);
        try {
            JsonMappingPackage.getInstance().scanClassesInPackage();
        }
        catch (IOException ex) {
            throw new JsonException("Error while reading classpath looking for Jsonnable content.", ex);

        }
    }

}
