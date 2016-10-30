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
package com.itametis.jsonconverter.entities;

import com.itametis.jsonconverter.annotation.JsonField;
import com.itametis.jsonconverter.annotation.JsonPath;
import com.itametis.jsonconverter.annotation.Jsonnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@Jsonnable
public class SimpleClassWithIgnoredField {

    private final static Logger LOGGER = LoggerFactory.getLogger("pouet");

    @JsonField
    @JsonPath(pathInCode = ".",pathInJson = ".")
    private String toto;

    private Boolean tata;

    public SimpleClassWithIgnoredField() {
        this.toto = "TOTO";
        this.tata = false;
    }


    public void setToto(String toto) {
        this.toto = toto;
    }


    public void setTata(Boolean tata) {
        this.tata = tata;
    }


    public String getToto() {
        return toto;
    }


    public Boolean getTata() {
        return tata;
    }



}
