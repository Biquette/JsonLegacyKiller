package com.itametis.jsonconverter.ignorestrategy;

import com.google.gson.FieldAttributes;
import com.itametis.jsonconverter.ignorestrategy.entity.IgnoreTestEntity;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class WriteIgnoreStrategyTest {

    @Test
    public void ignore_field_on_read() throws NoSuchFieldException {
        WriteIgnoreStrategy strategy = new WriteIgnoreStrategy();
        FieldAttributes fieldAttributes = new FieldAttributes(IgnoreTestEntity.class.getDeclaredField("tutu"));
        Assert.assertFalse(strategy.shouldSkipField(fieldAttributes));
    }


    @Test
    public void never_ignore_field_on_all() throws NoSuchFieldException {
        WriteIgnoreStrategy strategy = new WriteIgnoreStrategy();
        FieldAttributes fieldAttributes = new FieldAttributes(IgnoreTestEntity.class.getDeclaredField("toto"));
        Assert.assertFalse(strategy.shouldSkipField(fieldAttributes));
    }


    @Test
    public void ignore_field_on_write() throws NoSuchFieldException {
        WriteIgnoreStrategy strategy = new WriteIgnoreStrategy();
        FieldAttributes fieldAttributes = new FieldAttributes(IgnoreTestEntity.class.getDeclaredField("tata"));
        Assert.assertTrue(strategy.shouldSkipField(fieldAttributes));
    }


    @Test
    public void ignore_field_always() throws NoSuchFieldException {
        WriteIgnoreStrategy strategy = new WriteIgnoreStrategy();
        FieldAttributes fieldAttributes = new FieldAttributes(IgnoreTestEntity.class.getDeclaredField("titi"));
        Assert.assertTrue(strategy.shouldSkipField(fieldAttributes));
    }


}
