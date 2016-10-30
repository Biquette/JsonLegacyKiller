package com.itametis.jsonconverter.pathstrategy;

/**
 * The type of a JsonElement.
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public enum JsonElementType {
    JSONNULL("JsonNull"), JSONPRIMITIVE("JsonPrimitive"), JSONOBJECT("JsonObject"), JSONARRAY("JsonArray");

    private String textual;


    private JsonElementType(String textual) {
        this.textual = textual;
    }


    @Override
    public String toString() {
        return this.textual;
    }
}
