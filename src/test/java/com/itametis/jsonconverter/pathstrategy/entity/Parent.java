/* This file is part of JsonLegacyKiller.
 *
 * JsonLegacyKiller is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JsonLegacyKiller is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JsonLegacyKiller.  If not, see <http://www.gnu.org/licenses/gpl.txt >.
 *
 * If you need to develop a closed-source software, please contact us
 * at 'social@itametis.com' to get a commercial version of JsonLegacyKiller,
 * with a proprietary license instead.
 */
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
