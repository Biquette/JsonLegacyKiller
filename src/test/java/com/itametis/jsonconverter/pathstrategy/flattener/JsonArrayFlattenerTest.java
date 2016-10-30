package com.itametis.jsonconverter.pathstrategy.flattener;

import com.itametis.jsonconverter.pathstrategy.JsonElementProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class JsonArrayFlattenerTest {

    @Mock
    private Flattenizer flattenizer;

    @Mock
    private JsonElementProxy element;


    @Test
    public void flatten_object() {
        JsonArrayFlattener flattener = new JsonArrayFlattener(flattenizer);
        flattener.setChildhood(element);
    }

}
