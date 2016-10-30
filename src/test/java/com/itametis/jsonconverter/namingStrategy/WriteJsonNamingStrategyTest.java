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
package com.itametis.jsonconverter.namingStrategy;

import com.itametis.jsonconverter.namingStrategy.entity.NameTestClass;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class WriteJsonNamingStrategyTest {

    @Test
    public void not_overridden_name() throws NoSuchFieldException {
        WriteJsonNamingStrategy test = new WriteJsonNamingStrategy();
        Assert.assertEquals("tata", test.translateName(NameTestClass.class.getDeclaredField("tata")));
    }

        @Test
    public void overridden_name() throws NoSuchFieldException {
        WriteJsonNamingStrategy test = new WriteJsonNamingStrategy();
        Assert.assertEquals("plouf", test.translateName(NameTestClass.class.getDeclaredField("toto")));
    }


    @Test
    public void annotation_no_overridden_name() throws NoSuchFieldException {
        WriteJsonNamingStrategy test = new WriteJsonNamingStrategy();
        Assert.assertEquals("tutu", test.translateName(NameTestClass.class.getDeclaredField("tutu")));
    }


    @Test
    public void no_overridden_name() throws NoSuchFieldException {
        WriteJsonNamingStrategy test = new WriteJsonNamingStrategy();
        Assert.assertEquals("titi", test.translateName(NameTestClass.class.getDeclaredField("titi")));
    }

}
