package com.itametis.jsonconverter.classpathscan;

import com.itametis.jsonconverter.*;
import com.itametis.jsonconverter.entities.SimpleClass;
import com.itametis.jsonconverter.exception.JsonException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class ClassPathScanTest {

    @Test
    public void no_jsonnable_in_classpath() throws JsonException {
        JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.classpathscan");

        SimpleClass test = new SimpleClass();

        Assert.assertEquals("{\"toto\":\"TOTO\",\"map\":{\"test\":\"themap\"}}", converter.convertToJson(test));
    }

}
