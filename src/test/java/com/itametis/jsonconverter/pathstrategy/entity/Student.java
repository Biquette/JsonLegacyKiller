package com.itametis.jsonconverter.pathstrategy.entity;

import com.itametis.jsonconverter.annotation.JsonField;
import com.itametis.jsonconverter.annotation.Jsonnable;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@Jsonnable()
public class Student {

    @JsonField
    private String motherName;

    @JsonField
    private String toy;


    public Student(String motherName, String toy) {
        this.motherName = motherName;
        this.toy = toy;
    }


    public Student() {
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
