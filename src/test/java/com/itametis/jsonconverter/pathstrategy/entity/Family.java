package com.itametis.jsonconverter.pathstrategy.entity;

import com.itametis.jsonconverter.annotation.JsonField;
import com.itametis.jsonconverter.annotation.Jsonnable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@Jsonnable("family")
public class Family {

    @JsonField(name = "parent")
    private List<Parent> parents;


    public Family() {
        this.parents = new ArrayList<>();
    }


    public List<Parent> getParents() {
        return parents;
    }


    public void addParent(Parent parent) {
        this.parents.add(parent);
    }

}
