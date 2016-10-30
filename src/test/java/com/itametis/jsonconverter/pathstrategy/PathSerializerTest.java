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
package com.itametis.jsonconverter.pathstrategy;

import com.itametis.jsonconverter.JsonConverter;
import com.itametis.jsonconverter.exception.JsonException;
import com.itametis.jsonconverter.pathstrategy.entity.Family;
import com.itametis.jsonconverter.pathstrategy.entity.Parent;
import com.itametis.jsonconverter.pathstrategy.entity.Student;
import com.itametis.jsonconverter.pathstrategy.entity.Teacher;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class PathSerializerTest {

    private final static String EXPECTED_JSON = "{\"child\":"
                                                + "{\"motherName\":\"mom\",\"toy\":\"PlayStation\"},"
                                                + "\"name\":\"dad\",\"age\":30,\"hobbies\":[\"movies\",\"beer\"]}";

    private final static String EXPECTED_JSON_2 = "{\"name\":\"Dora\",\"age\":50,\"students\":["
                                                  + "{\"motherName\":\"mommy\",\"toy\":\"Game Boy\"},"
                                                  + "{\"motherName\":\"Old Woman\",\"toy\":\"Facebook\"},"
                                                  + "{\"motherName\":\"granny\",\"toy\":\"Scrabble\"},"
                                                  + "{\"motherName\":\"you\",\"toy\":\"puzzle\"}"
                                                  + "]}";

    private final static String EXPECTED_JSON_3 = "{\"parent\":["
                                                  + "{\"child\":{\"motherName\":\"trololo\",\"toy\":\"hat\"},"
                                                  + "\"name\":\"dad\",\"age\":30,\"hobbies\":[\"movies\",\"beer\"]},"
                                                  + "{\"child\":{\"motherName\":\"mom\",\"toy\":\"PlayStation\"},"
                                                  + "\"name\":\"mom\",\"age\":34,\"hobbies\":[\"beer\",\"running\"]}"
                                                  + "]}";


    @Test
    public void from_object_to_json_simple_object_move() throws JsonException {
        JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.pathstrategy.entity");
        Parent toConvert = new Parent("dad", 30, "movies", "beer");
        toConvert.getChild().setMotherName("mom");
        toConvert.getChild().setToy("PlayStation");
        Assert.assertEquals(EXPECTED_JSON, converter.convertToJson(toConvert));
    }


    @Test
    public void from_object_to_json_list_level_move() throws JsonException {
        JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.pathstrategy.entity");
        Teacher teacher = new Teacher("Dora", 50);

        //Terminal 1
        Student child1 = new Student("mommy", "Game Boy");
        Student child2 = new Student("Old Woman", "Facebook");
        teacher.addStudent(child1);
        teacher.addStudent(child2);
        Student child3 = new Student("granny", "Scrabble");
        Student child4 = new Student("you", "puzzle");
        teacher.addStudent(child3);
        teacher.addStudent(child4);

        Assert.assertEquals(EXPECTED_JSON_2, converter.convertToJson(teacher));
    }


    @Test
    public void from_object_to_json_complex_object_move() throws JsonException {
        JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.pathstrategy.entity");
        Parent father = new Parent("dad", 30, "movies", "beer");
        father.getChild().setMotherName("trololo");
        father.getChild().setToy("hat");
        Parent mommy = new Parent("mom", 34, "beer", "running");
        mommy.getChild().setMotherName("mom");
        mommy.getChild().setToy("PlayStation");
        Family family = new Family();
        family.addParent(father);
        family.addParent(mommy);
        Assert.assertEquals(EXPECTED_JSON_3, converter.convertToJson(family));
    }

}
