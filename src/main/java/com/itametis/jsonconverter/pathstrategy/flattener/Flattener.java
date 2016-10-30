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
