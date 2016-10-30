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
import com.itametis.jsonconverter.pathstrategy.entity.Teacher;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class PathDeserializerTest {

    private final static String INPUT_JSON = "{\"child\":"
                                             + "{\"toy\":\"PlayStation\"},"
                                             + "\"name\":\"dad\",\"age\":30,\"wife\":\"mom\",\"hobbies\":[\"movies\",\"beer\"]}";

    private final static String INPUT_JSON_2 = "{\"name\":\"Dora\",\"age\":50,\"classes\":["
                                               + "{\"students\":["
                                               + "{\"motherName\":\"mommy\",\"toy\":\"Game Boy\"},"
                                               + "{\"motherName\":\"Old Woman\",\"toy\":\"Facebook\"}"
                                               + "]},"
                                               + "{\"students\":["
                                               + "{\"motherName\":\"granny\",\"toy\":\"Scrabble\"},"
                                               + "{\"motherName\":\"you\",\"toy\":\"puzzle\"}"
                                               + "]}"
                                               + "]}";

    private final static String INPUT_JSON_3 = "{\"parent\":["
                                               + "{\"child\":{\"toy\":\"hat\"},"
                                               + "\"name\":\"dad\",\"age\":30,\"hobbies\":[\"movies\",\"beer\"],\"wife\":\"trololo\"},"
                                               + "{\"child\":{\"toy\":\"PlayStation\"},"
                                               + "\"name\":\"mom\",\"age\":34,\"hobbies\":[\"beer\",\"running\"],\"wife\":\"mom\"}"
                                               + "]}";


    @Test
    public void from_json_to_object() throws JsonException {
        JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.pathstrategy.entity");
        Parent convertedParent = converter.convertFromJson(INPUT_JSON, Parent.class);
        Assert.assertEquals(30, convertedParent.getAge());
        Assert.assertEquals("dad", convertedParent.getName());
        Assert.assertEquals("mom", convertedParent.getChild().getMotherName());
    }


    @Test
    public void from_object_to_json_2() throws JsonException {
        JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.pathstrategy.entity");
        Teacher convertedTeacher = converter.convertFromJson(INPUT_JSON_2, Teacher.class);

        Assert.assertEquals("Dora", convertedTeacher.getName());
        Assert.assertEquals(50, convertedTeacher.getAge(), 0);
        Assert.assertEquals("mommy", convertedTeacher.getStudents().get(0).getMotherName());
        Assert.assertEquals("Facebook", convertedTeacher.getStudents().get(1).getToy());
        Assert.assertEquals("granny", convertedTeacher.getStudents().get(2).getMotherName());
        Assert.assertEquals("puzzle", convertedTeacher.getStudents().get(3).getToy());

    }


    @Test
    public void from_json_to_object_3() throws JsonException {
        JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.pathstrategy.entity");
        Family convertedFamily = null;
        try {
            convertedFamily = converter.convertFromJson(INPUT_JSON_3, Family.class);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Assert.assertEquals(30, convertedFamily.getParents().get(0).getAge());
        Assert.assertEquals("dad", convertedFamily.getParents().get(0).getName());
        Assert.assertEquals("mom", convertedFamily.getParents().get(1).getChild().getMotherName());
    }

}
