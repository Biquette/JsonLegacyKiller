package com.itametis.jsonconverter.pathstrategy.entity;

import com.itametis.jsonconverter.annotation.JsonField;
import com.itametis.jsonconverter.annotation.JsonPath;
import com.itametis.jsonconverter.annotation.Jsonnable;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@Jsonnable()
public class Child {

    @JsonField(nameInJson = "wife")
    @JsonPath(pathInCode = {".", "child"}, pathInJson = ".")
    private String motherName;

    @JsonField
    private String toy;


    public Child(String motherName, String toy) {
        this.motherName = motherName;
        this.toy = toy;
    }


    public Child() {
        this(null, null);
    }


    public String getMotherName() {
        return motherName;
    }


    public String getToy() {
        return toy;
    }


    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }


    public void setToy(String toy) {
        this.toy = toy;
    }

}
