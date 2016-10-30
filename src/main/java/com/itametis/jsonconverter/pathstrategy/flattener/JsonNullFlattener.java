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
package com.itametis.jsonconverter.pathstrategy.flattener;

import com.google.gson.JsonNull;
import com.itametis.jsonconverter.exception.JsonException;
import com.itametis.jsonconverter.pathstrategy.JsonElementProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class JsonNullFlattener extends Flattener<JsonNull> {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonNullFlattener.class.getSimpleName());


    public JsonNullFlattener(Flattenizer flattenizer) {
        super(flattenizer);
    }


    @Override
    protected void setChildhood(JsonElementProxy element) {
        this.addChildToParentOf(element);
    }


    @Override
    protected void addInJsonFlattenedTree(JsonElementProxy element) throws JsonException {
        this.flattenizer.addInJsonTree(element);
    }


    @Override
    protected void flatten(JsonNull jsonElement, JsonElementProxy parent) {
        //Do nothing.
    }


    @Override
    protected void empty(JsonNull element) {
        //Do nothing. Json null has no members.
    }

}
