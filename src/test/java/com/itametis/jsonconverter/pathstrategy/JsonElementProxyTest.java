package com.itametis.jsonconverter.pathstrategy;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.itametis.jsonconverter.exception.JsonException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class JsonElementProxyTest {

    private JsonObject object = new JsonObject();

    private JsonNull nullElement = JsonNull.INSTANCE;

    private JsonArray array = new JsonArray();

    private JsonPrimitive primitive = new JsonPrimitive("pouet");



    @Test
    public void instanciation() {
        JsonElementProxy element = new JsonElementProxy(this.object, "toto", null);
        Assert.assertEquals("toto", element.getJsonName());
        Assert.assertNull(element.getParent());
        Assert.assertEquals(this.object, element.getElement());
    }


    @Test
    public void with_parent() {
        JsonElementProxy parent = new JsonElementProxy(this.object, "daddy", null);
        JsonElementProxy element = new JsonElementProxy(this.primitive, "toto", parent);
        Assert.assertEquals("daddy", element.getParent().getJsonName());
    }


    @Test
    public void add_child() {
        JsonElementProxy element = new JsonElementProxy(this.primitive, "toto", null);
        element.addChild(new JsonElementProxy(this.primitive, "baby", element));
        Assert.assertEquals("baby", element.getChildren().get("baby").get(0).getJsonName());
        Assert.assertEquals(element, element.getChildren().get("baby").get(0).getParent());
    }


    @Test
    public void remove_child() throws JsonException {
        JsonElementProxy element = new JsonElementProxy(this.primitive, "toto", null);
        JsonElementProxy child = new JsonElementProxy(this.primitive, "baby", element);
        element.addChild(child);
        element.removeChild(child);
        Assert.assertNull(element.getChildren().get("baby"));
    }


    @Test(expected = JsonException.class)
    public void remove_unadded_child() throws JsonException {
        JsonElementProxy element = new JsonElementProxy(this.primitive, "toto", null);
        JsonElementProxy child = new JsonElementProxy(this.primitive, "baby", element);
        element.removeChild(child);
    }


    @Test(expected = JsonException.class)
    public void remove_unadded_child_with_same_name_other_child() throws JsonException {
        JsonElementProxy element = new JsonElementProxy(this.primitive, "toto", null);
        JsonElementProxy child = new JsonElementProxy(this.primitive, "baby", element);
        JsonElementProxy child2 = new JsonElementProxy(this.array, "baby", element);
        element.addChild(child);
        element.removeChild(child2);
    }


    @Test
    public void add_two_children_same_name() {
        JsonElementProxy element = new JsonElementProxy(this.primitive, "toto", null);
        element.addChild(new JsonElementProxy(this.primitive, "baby", element));
        element.addChild(new JsonElementProxy(this.primitive, "baby", element));
        Assert.assertEquals(2, element.getChildren().get("baby").size());
    }


    @Test
    public void add_two_children_different_name() {
        JsonElementProxy element = new JsonElementProxy(this.primitive, "toto", null);
        element.addChild(new JsonElementProxy(this.primitive, "baby", element));
        element.addChild(new JsonElementProxy(this.primitive, "child", element));
        Assert.assertEquals(1, element.getChildren().get("baby").size());
        Assert.assertEquals(1, element.getChildren().get("child").size());
    }


    @Test
    public void instanciate_part_of_collection() {
        JsonElementProxy element = new JsonElementProxy(this.object, "element", null, true);
        Assert.assertTrue(element.isPartOfACollection());
    }


    @Test
    public void get_element_type_for_object() throws JsonException {
        JsonElementProxy proxy = new JsonElementProxy(this.object, "element", null);
        Assert.assertEquals(JsonElementType.JSONOBJECT, proxy.getElementType());
    }


    @Test
    public void get_element_type_for_primitive() throws JsonException {
        JsonElementProxy proxy = new JsonElementProxy(this.primitive, "element", null);
        Assert.assertEquals(JsonElementType.JSONPRIMITIVE, proxy.getElementType());
    }


    @Test
    public void get_element_type_for_array() throws JsonException {
        JsonElementProxy proxy = new JsonElementProxy(this.array, "element", null);
        Assert.assertEquals(JsonElementType.JSONARRAY, proxy.getElementType());
    }


    @Test
    public void get_element_type_for_null_element() throws JsonException {
        JsonElementProxy proxy = new JsonElementProxy(this.nullElement, "element", null);
        Assert.assertEquals(JsonElementType.JSONNULL, proxy.getElementType());
    }


    @Test(expected = JsonException.class)
    public void get_element_type_for_null() throws JsonException {
        JsonElementProxy proxy = new JsonElementProxy(null, "element", null);
        proxy.getElementType();
    }

    @Test
    public void equals_not_same_objects() {
        JsonElementProxy proxy = new JsonElementProxy(this.nullElement, "element", null);
        Assert.assertTrue(proxy.equals(this.nullElement));
    }
}
