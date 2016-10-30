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
