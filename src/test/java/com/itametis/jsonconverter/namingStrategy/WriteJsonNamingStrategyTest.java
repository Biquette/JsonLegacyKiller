package com.itametis.jsonconverter.namingStrategy;

import com.itametis.jsonconverter.namingStrategy.entity.NameTestClass;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class WriteJsonNamingStrategyTest {

    @Test
    public void not_overridden_name() throws NoSuchFieldException {
        WriteJsonNamingStrategy test = new WriteJsonNamingStrategy();
        Assert.assertEquals("tata", test.translateName(NameTestClass.class.getDeclaredField("tata")));
    }

        @Test
    public void overridden_name() throws NoSuchFieldException {
        WriteJsonNamingStrategy test = new WriteJsonNamingStrategy();
        Assert.assertEquals("plouf", test.translateName(NameTestClass.class.getDeclaredField("toto")));
    }


    @Test
    public void annotation_no_overridden_name() throws NoSuchFieldException {
        WriteJsonNamingStrategy test = new WriteJsonNamingStrategy();
        Assert.assertEquals("tutu", test.translateName(NameTestClass.class.getDeclaredField("tutu")));
    }


    @Test
    public void no_overridden_name() throws NoSuchFieldException {
        WriteJsonNamingStrategy test = new WriteJsonNamingStrategy();
        Assert.assertEquals("titi", test.translateName(NameTestClass.class.getDeclaredField("titi")));
    }

}
