package com.itametis.jsonconverter.pathstrategy.entity;

import com.itametis.jsonconverter.annotation.JsonField;
import com.itametis.jsonconverter.annotation.Jsonnable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
@Jsonnable("parent")
public class Parent {

    @JsonField
    private Child child;

    @JsonField
    private String name;

    @JsonField
    private int age;

    @JsonField
    private List<String> hobbies;


    public Parent() {
        this.child = new Child();
        this.hobbies = new ArrayList<>();
    }


    public Parent(String name, int age, String... hobbies) {
        this();
        this.name = name;
        this.age = age;
        for (String hobby : hobbies) {
            this.hobbies.add(hobby);
        }
    }


    public Child getChild() {
        return child;
    }


    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }


    public List<String> getHobbies() {
        return hobbies;
    }

}
