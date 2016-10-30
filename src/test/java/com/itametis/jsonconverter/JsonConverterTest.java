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
package com.itametis.jsonconverter;

import com.itametis.jsonconverter.entities.ComplexClass;
import com.itametis.jsonconverter.entities.SimpleClass;
import com.itametis.jsonconverter.entities.SimpleClassWithIgnoredField;
import com.itametis.jsonconverter.exception.JsonException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class JsonConverterTest {

    @Test
    public void convert_from_json_with_simple_string() throws JsonException {
        JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.entities");

        SimpleClass test = converter.convertFromJson("{\"toto\":\"TOTO\",\"map\":{\"test\":\"themap\"}}", SimpleClass.class);

        Assert.assertEquals("TOTO", test.getToto());
        Assert.assertEquals("themap", test.getMap().get("test"));
    }


    @Test
    public void convert_from_json_with_ignored_field() throws JsonException {
        JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.entities");

        SimpleClassWithIgnoredField test = converter.convertFromJson("{\"toto\":\"TOTO\",\"tata\":true}", SimpleClassWithIgnoredField.class);

        Assert.assertEquals("TOTO", test.getToto());
        Assert.assertFalse(test.getTata());
    }


    @Test
    public void convert_from_json_with_complex_class() throws JsonException {
        JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.entities");

        ComplexClass test = converter.convertFromJson("{\"titi\":\"Titi\",\"tutu\":\"Tutu\",\"simple\":{\"toto\":\"truc\",\"tata\":true}}", ComplexClass.class);

        Assert.assertEquals("truc", test.getSimple().getToto());
        Assert.assertFalse(test.getSimple().getTata());
        Assert.assertEquals("Titi", test.getTiti());
    }


    @Test
    public void convert_to_json_with_simple_string() throws JsonException {
        JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.entities");

        SimpleClass test = new SimpleClass();

        Assert.assertEquals("{\"toto\":\"TOTO\",\"map\":{\"test\":\"themap\"}}", converter.convertToJson(test));
    }


    @Test
    public void convert_to_json_with_ignored_field() throws JsonException {
        JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.entities");

        SimpleClassWithIgnoredField test = new SimpleClassWithIgnoredField();

        Assert.assertEquals("{\"toto\":\"TOTO\"}", converter.convertToJson(test));
    }


    @Test
    public void convert_to_json_with_complex_class() throws JsonException {
        JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.entities");

        ComplexClass test = new ComplexClass();

        Assert.assertEquals("{\"titi\":\"Titi\",\"tutu\":\"Tutu\",\"simple\":{\"toto\":\"truc\"}}", converter.convertToJson(test));
    }
}
