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
