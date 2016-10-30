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
package com.itametis.jsonconverter.ignorestrategy;

import com.google.gson.FieldAttributes;
import com.itametis.jsonconverter.ignorestrategy.entity.IgnoreTestEntity;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class WriteIgnoreStrategyTest {

    @Test
    public void ignore_field_on_read() throws NoSuchFieldException {
        WriteIgnoreStrategy strategy = new WriteIgnoreStrategy();
        FieldAttributes fieldAttributes = new FieldAttributes(IgnoreTestEntity.class.getDeclaredField("tutu"));
        Assert.assertFalse(strategy.shouldSkipField(fieldAttributes));
    }


    @Test
    public void never_ignore_field_on_all() throws NoSuchFieldException {
        WriteIgnoreStrategy strategy = new WriteIgnoreStrategy();
        FieldAttributes fieldAttributes = new FieldAttributes(IgnoreTestEntity.class.getDeclaredField("toto"));
        Assert.assertFalse(strategy.shouldSkipField(fieldAttributes));
    }


    @Test
    public void ignore_field_on_write() throws NoSuchFieldException {
        WriteIgnoreStrategy strategy = new WriteIgnoreStrategy();
        FieldAttributes fieldAttributes = new FieldAttributes(IgnoreTestEntity.class.getDeclaredField("tata"));
        Assert.assertTrue(strategy.shouldSkipField(fieldAttributes));
    }


    @Test
    public void ignore_field_always() throws NoSuchFieldException {
        WriteIgnoreStrategy strategy = new WriteIgnoreStrategy();
        FieldAttributes fieldAttributes = new FieldAttributes(IgnoreTestEntity.class.getDeclaredField("titi"));
        Assert.assertTrue(strategy.shouldSkipField(fieldAttributes));
    }


}
