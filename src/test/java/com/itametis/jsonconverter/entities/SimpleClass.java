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
import com.itametis.jsonconverter.annotation.Jsonnable;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@Jsonnable
public class SimpleClass {

   private final static Logger LOGGER = LoggerFactory.getLogger("pouet");

   @JsonField
    private String toto;

   @JsonField
    private HashMap<String, String> map = new HashMap<String, String>();


    public SimpleClass() {
        this.map.put("test", "themap");
        this.toto = "TOTO";
    }


    public String getToto() {
        return toto;
    }


    public void setToto(String toto) {
        this.toto = toto;
    }


    public HashMap<String, String> getMap() {
        return map;
    }


    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }



}
