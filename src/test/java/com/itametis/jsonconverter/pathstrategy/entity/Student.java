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
package com.itametis.jsonconverter.pathstrategy.entity;

import com.itametis.jsonconverter.annotation.JsonField;
import com.itametis.jsonconverter.annotation.Jsonnable;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@Jsonnable()
public class Student {

    @JsonField
    private String motherName;

    @JsonField
    private String toy;


    public Student(String motherName, String toy) {
        this.motherName = motherName;
        this.toy = toy;
    }


    public Student() {
        this(null, null);
    }


    public String getMotherName() {
        return motherName;
    }


    public String getToy() {
        return toy;
    }


    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }


    public void setToy(String toy) {
        this.toy = toy;
    }

}
