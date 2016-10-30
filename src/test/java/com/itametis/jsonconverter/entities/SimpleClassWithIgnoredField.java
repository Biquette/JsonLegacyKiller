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
public class SimpleClassWithIgnoredField {

    private final static Logger LOGGER = LoggerFactory.getLogger("pouet");

    @JsonField
    @JsonPath(pathInCode = ".",pathInJson = ".")
    private String toto;

    private Boolean tata;

    public SimpleClassWithIgnoredField() {
        this.toto = "TOTO";
        this.tata = false;
    }


    public void setToto(String toto) {
        this.toto = toto;
    }


    public void setTata(Boolean tata) {
        this.tata = tata;
    }


    public String getToto() {
        return toto;
    }


    public Boolean getTata() {
        return tata;
    }



}
