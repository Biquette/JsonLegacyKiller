package com.itametis.jsonconverter.namingStrategy;

import com.itametis.jsonconverter.namingStrategy.entity.NameTestClass;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class ReadJsonNamingStrategyTest {

    @Test
    public void overridden_name() throws NoSuchFieldException {
        ReadJsonNamingStrategy test = new ReadJsonNamingStrategy();
        Assert.assertEquals("test", test.translateName(NameTestClass.class.getDeclaredField("tata")));
    }

    @Test
    public void not_overridden_name() throws NoSuchFieldException {
        ReadJsonNamingStrategy test = new ReadJsonNamingStrategy();
        Assert.assertEquals("plouf", test.translateName(NameTestClass.class.getDeclaredField("toto")));
    }


    @Test
    public void annotation_no_overridden_name() throws NoSuchFieldException {
        ReadJsonNamingStrategy test = new ReadJsonNamingStrategy();
        Assert.assertEquals("tutu", test.translateName(NameTestClass.class.getDeclaredField("tutu")));
    }


    @Test
    public void no_overridden_name() throws NoSuchFieldException {
        ReadJsonNamingStrategy test = new ReadJsonNamingStrategy();
        Assert.assertEquals("titi", test.translateName(NameTestClass.class.getDeclaredField("titi")));
    }

}
