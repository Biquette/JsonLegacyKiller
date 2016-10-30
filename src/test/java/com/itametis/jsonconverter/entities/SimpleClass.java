package com.itametis.jsonconverter.entities;

import com.itametis.jsonconverter.annotation.JsonField;
import com.itametis.jsonconverter.annotation.Jsonnable;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@Jsonnable
public class SimpleClass {

   private final static Logger LOGGER = LoggerFactory.getLogger("pouet");

   @JsonField
    private String toto;

   @JsonField
    private HashMap<String, String> map = new HashMap<String, String>();


    public SimpleClass() {
        this.map.put("test", "themap");
        this.toto = "TOTO";
    }


    public String getToto() {
        return toto;
    }


    public void setToto(String toto) {
        this.toto = toto;
    }


    public HashMap<String, String> getMap() {
        return map;
    }


    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }



}
