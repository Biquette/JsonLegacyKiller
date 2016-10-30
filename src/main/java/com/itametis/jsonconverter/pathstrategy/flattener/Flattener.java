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

import com.google.gson.JsonElement;
import com.itametis.jsonconverter.exception.JsonException;
import com.itametis.jsonconverter.pathstrategy.JsonElementProxy;

/**
 * A generic flattener. Knows how to flatten a Json Element.
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public abstract class Flattener<T extends JsonElement> {

    protected Flattenizer flattenizer;


    /**
     * Constructor.
     *
     * @param flattenizer the flattenizer. Required because flattening is recursive.
     */
    public Flattener(Flattenizer flattenizer) {
        this.flattenizer = flattenizer;
    }


    /**
     * Set a link between the father and the child if needed.
     *
     * @param element the element.
     */
    protected abstract void setChildhood(JsonElementProxy element);


    /**
     * Set the element as child of its parent.
     *
     * @param element the element.
     */
    protected void addChildToParentOf(JsonElementProxy element) {
        if (element.getParent() != null) {
            element.getParent().addChild(element);
        }
    }


    /**
     * Add the element in the flattened tree if its type is ok with it.
     *
     * @param element the element.
     *
     * @throws JsonException
     */
    protected abstract void addInJsonFlattenedTree(JsonElementProxy element) throws JsonException;


    /**
     * Flatten the element. Consists mostly in flattening the children of the element.
     *
     * @param jsonElement    the element to flatten.
     * @param currentElement the parent.
     *
     * @throws JsonException
     */
    protected abstract void flatten(T jsonElement, JsonElementProxy currentElement) throws JsonException;


    /**
     * Break the links between the element and its childs (or members).
     *
     * @param element the element.
     */
    protected abstract void empty(T element);

}
