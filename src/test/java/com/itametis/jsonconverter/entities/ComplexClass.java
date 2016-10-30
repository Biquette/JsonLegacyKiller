package com.itametis.jsonconverter.entities;

import com.itametis.jsonconverter.annotation.JsonField;
import com.itametis.jsonconverter.annotation.JsonPath;
import com.itametis.jsonconverter.annotation.Jsonnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@Jsonnable
public class ComplexClass {

    private final static Logger LOGGER = LoggerFactory.getLogger("pouet");

    @JsonPath(pathInCode = ".",pathInJson = ".")
    @JsonField
    private String titi;

    @JsonField
    private String tutu;

    @JsonField
    private SimpleClassWithIgnoredField simple;


    public ComplexClass() {
        this.simple = new SimpleClassWithIgnoredField();
        this.simple.setToto("truc");
        this.simple.setTata(false);
        this.titi = "Titi";
        this.tutu = "Tutu";
    }


    public String getTiti() {
        return titi;
    }


    public String getTutu() {
        return tutu;
    }


    public SimpleClassWithIgnoredField getSimple() {
        return simple;
    }



}
