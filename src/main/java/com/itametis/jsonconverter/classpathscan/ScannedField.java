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
package com.itametis.jsonconverter.classpathscan;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class ScannedField {

    private final String nameInJson;

    private final String name;

    private String[] pathInCode;

    private String[] pathInJson;

    private boolean collectionOrMap;

    private final List<Class> parameters;

    private final Field field;


    public ScannedField(String name, String nameInJson, Field field) {
        if (nameInJson == null || nameInJson.isEmpty()) {
            this.nameInJson = name;
        }
        else {
            this.nameInJson = nameInJson;
        }
        this.name = name;
        this.field = field;
        this.parameters = new ArrayList<>();
        this.pathInCode = new String[0];
        this.pathInJson = new String[0];
    }


    public String getNameInJson() {
        return nameInJson;
    }


    public String getName() {
        return name;
    }


    public Field getField() {
        return field;
    }


    public String[] getPathInCode() {
        return pathInCode;
    }


    public String[] getPathInJson() {
        return pathInJson;
    }


    public boolean isCollectionOrMap() {
        return collectionOrMap;
    }


    public void setIsCollectionOrMap(boolean isCollection) {
        this.collectionOrMap = isCollection;
    }


    public List<Class> getParameters() {
        return parameters;
    }


    public void addParameter(Type parameter) {
        this.parameters.add((Class) parameter);
    }


    public void setPathInCode(String[] pathInCode) {
        this.pathInCode = pathInCode;
    }


    public void setPathInJson(String[] pathInJson) {
        this.pathInJson = pathInJson;
    }


    /**
     * Indicates that the field must be somewhere else in the structure.
     *
     * @return true if the field needs to be moved.
     */
    public boolean needsToBeMoved() {
        return this.pathInCode.length != 0
               || this.pathInJson.length != 0;
    }


    @Override
    public String toString() {
        return "ScannedField{" + "nameInJson=" + nameInJson + ", name=" + name + ", path=" + (pathInCode.length == 0) + '}';
    }

}
