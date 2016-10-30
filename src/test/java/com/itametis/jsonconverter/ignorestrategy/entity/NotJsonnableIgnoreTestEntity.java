package com.itametis.jsonconverter.ignorestrategy.entity;

import com.itametis.jsonconverter.annotation.JsonField;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class NotJsonnableIgnoreTestEntity {

    @JsonField(useType = JsonField.UseType.READ)
    private String tata;

    @JsonField(useType = JsonField.UseType.WRITE)
    private String tutu;

    @JsonField(useType = JsonField.UseType.ALL)
    private String toto;

    private String titi;


    public NotJsonnableIgnoreTestEntity() {
        this.tata = "tata";
        this.tutu = "tutu";
        this.toto = "toto";
    }

}
