package com.itametis.jsonconverter.ignorestrategy.entity;

import com.itametis.jsonconverter.annotation.JsonField;
import com.itametis.jsonconverter.annotation.Jsonnable;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@Jsonnable
public class IgnoreTestEntity {

    @JsonField(useType = JsonField.UseType.READ)
    private String tata;

    @JsonField(useType = JsonField.UseType.WRITE)
    private String tutu;

    @JsonField(useType = JsonField.UseType.ALL)
    private String toto;

    private String titi;


    public IgnoreTestEntity() {
        this.tata = "tata";
        this.tutu = "tutu";
        this.toto = "toto";
    }

}
