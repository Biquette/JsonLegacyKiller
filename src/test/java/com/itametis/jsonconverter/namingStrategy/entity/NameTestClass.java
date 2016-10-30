package com.itametis.jsonconverter.namingStrategy.entity;

import com.itametis.jsonconverter.annotation.JsonField;
import com.itametis.jsonconverter.annotation.Jsonnable;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@Jsonnable
public class NameTestClass {

    @JsonField(nameInJson = "test")
    private String tata;

    @JsonField(name = "plouf")
    private String toto;

    @JsonField
    private String tutu;

    private String titi;

}
