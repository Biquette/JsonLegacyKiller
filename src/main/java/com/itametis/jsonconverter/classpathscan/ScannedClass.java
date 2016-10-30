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

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class ScannedClass {

    private Class classDefinition;

    private String jSonName;

    private Set<ScannedField> jSonFields;


    public ScannedClass(String jsonName, Class detail) {
        this.jSonName = jsonName;
        this.classDefinition = detail;
        this.jSonFields = new HashSet<>();
    }


    public void addField(ScannedField jsonnableField) {
        this.jSonFields.add(jsonnableField);
    }


    public Class getClassDefinition() {
        return classDefinition;
    }


    public String getjSonName() {
        return jSonName;
    }


    public Set<ScannedField> getjSonFields() {
        return jSonFields;
    }


    @Override
    public String toString() {
        return "ScannedClass{" + "jSonName=" + jSonName +", classDefinition=" + classDefinition.getSimpleName() + '}';
    }



}
