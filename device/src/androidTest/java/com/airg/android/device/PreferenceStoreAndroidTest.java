/*
 * ****************************************************************************
 *   Copyright  2016 airG Inc.                                                 *
 *                                                                             *
 *   Licensed under the Apache License, Version 2.0 (the "License");           *
 *   you may not use this file except in compliance with the License.          *
 *   You may obtain a copy of the License at                                   *
 *                                                                             *
 *       http://www.apache.org/licenses/LICENSE-2.0                            *
 *                                                                             *
 *   Unless required by applicable law or agreed to in writing, software       *
 *   distributed under the License is distributed on an "AS IS" BASIS,         *
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 *   See the License for the specific language governing permissions and       *
 *   limitations under the License.                                            *
 * ***************************************************************************
 */

package com.airg.android.device;

import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by mahramf.
 */
public class PreferenceStoreAndroidTest {
    PreferenceStore store;

    @Before
    public void setUp() throws Exception {
        store = new PreferenceStore(InstrumentationRegistry.getTargetContext());
        store.edit().clear().apply();
    }

    @After
    public void tearDown() {
        store.edit().clear().apply();
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(0, store.getAll().size());

        store.edit().putBoolean("first one", true).apply();

        assertEquals(1, store.getAll().size());

        store.edit()
                .putBoolean("next one", true)
                .putInt("another", 1)
                .putFloat("Floater", 3f)
                .putDouble("double down", Math.PI)
                .putStringSet("sets", new HashSet<String>())
                .apply();

        assertEquals(6, store.getAll().size());

        store.edit().clear().apply();
        assertEquals(0, store.getAll().size());
    }

    @Test
    public void getString() throws Exception {
        final String key = "nonexistent";
        final String defaultValue = "default";
        final String expected = "Expected value";

        assertNull(store.getString(key, null));
        assertEquals(defaultValue, store.getString(key, defaultValue));

        store.edit().putString(key, expected).apply();

        assertEquals(expected, store.getString(key, defaultValue));

        store.edit().remove(key).apply();

        assertNull(store.getString(key, null));
        assertEquals(defaultValue, store.getString(key, defaultValue));
    }

    @Test
    public void getStringSet() throws Exception {
        final String key = "set key";
        final String[] array = {"first", "second value", "3rd", "That's enough."};
        final Set<String> emptySet = new HashSet<>();
        final Set<String> nonEmptySet = new HashSet<>();
        Collections.addAll(nonEmptySet, array);

        assertNull(store.getStringSet(key, null));
        assertEquals(emptySet, store.getStringSet(key, emptySet));

        store.edit().putStringSet(key, emptySet).apply();
        final Set<String> emptystored = store.getStringSet(key, null);
        assertNotNull(emptystored);
        assertEquals(emptySet, emptystored);
        assertEquals(0, emptystored.size());

        store.edit().putStringSet(key, nonEmptySet).apply();
        final Set<String> stored = store.getStringSet(key, null);
        assertNotNull(stored);
        assertEquals(nonEmptySet, stored);
        assertEquals(array.length, stored.size());

        for (final String str : array) {
            assertTrue(stored.contains(str));
        }

        store.edit().remove(key).apply();
        assertNull(store.getStringSet(key, null));
    }

    @Test
    public void getStringList() throws Exception {
        final String key = "list key";
        final String[] array = {"first", "second value", "3rd", "That's enough."};
        final List<String> emptyList = new ArrayList<>();
        final List<String> nonEmptyList = new ArrayList<>();
        Collections.addAll(nonEmptyList, array);

        assertNull(store.getStringList(key, null));
        assertEquals(emptyList, store.getStringList(key, emptyList));

        store.edit().putStringList(key, emptyList).apply();
        final List<String> emptystored = store.getStringList(key, null);
        assertNotNull(emptystored);
        assertEquals(emptyList, emptystored);
        assertEquals(0, emptystored.size());

        store.edit().putStringList(key, nonEmptyList).apply();
        final List<String> stored = store.getStringList(key, null);
        assertNotNull(stored);
        assertEquals(nonEmptyList, stored);
        assertEquals(array.length, stored.size());

        for (int i = 0; i < nonEmptyList.size(); i++) {
            assertEquals(nonEmptyList.get(i), stored.get(i));
        }

        store.edit().remove(key).apply();
        assertNull(store.getStringSet(key, null));
    }

    @Test
    public void getInt() throws Exception {
        final String key = "int key";

        assertEquals(-1, store.getInt(key, -1));
        assertEquals(66, store.getInt(key, 66));

        store.edit().putInt(key, 0).apply();
        assertEquals(0, store.getInt(key, -1));

        store.edit().putInt(key, 33).apply();
        assertEquals(33, store.getInt(key, 0));

        store.edit().remove(key).apply();
        assertEquals(0, store.getInt(key, 0));
    }

    @Test
    public void getLong() throws Exception {
        final String key = "long key";

        assertEquals(-1, store.getLong(key, -1));
        assertEquals(6000000000L, store.getLong(key, 6000000000L));

        store.edit().putLong(key, 0L).apply();
        assertEquals(0L, store.getLong(key, 12345678901L));

        store.edit().putLong(key, 12345678901L).apply();
        assertEquals(12345678901L, store.getLong(key, 12345678901L));

        store.edit().remove(key).apply();
        assertEquals(638573652527L, store.getLong(key, 638573652527L));
    }

    @Test
    public void getFloat() throws Exception {
        final String key = "floater";

        assertEquals(-123.34f, store.getFloat(key, -123.34f), 0f);
        assertEquals(0.0034f, store.getFloat(key, 0.0034f), 0f);

        store.edit().putFloat(key, 0f).apply();
        assertEquals(0f, store.getFloat(key, 5f), 0);

        store.edit().putFloat(key, 0.005f).apply();
        assertEquals(0.005f, store.getFloat(key, 107f), 0);

        store.edit().remove(key).apply();
        assertEquals(2.345f, store.getFloat(key, 2.345f), 0);
    }

    @Test
    public void getDouble() throws Exception {
        final String key = "double trouble";

        assertEquals(-1.003450023, store.getDouble(key, -1.003450023), 0);
        assertEquals(32543466474567.346536, store.getDouble(key, 32543466474567.346536), 0);

        store.edit().putDouble(key, 0.0).apply();
        assertEquals(0.0, store.getDouble(key, 0.0), 0.0);

        store.edit().putDouble(key, 123758584589.4435235667004).apply();
        assertEquals(123758584589.4435235667004, store.getDouble(key, 0.0), 0.0);

        store.edit().remove(key).apply();
        assertEquals(-3456.7890, store.getDouble(key, -3456.7890), 0.0);
    }

    @Test
    public void getBoolean() throws Exception {
        final String key = "yesnomaybe";

        assertFalse(store.getBoolean(key, false));
        assertTrue(store.getBoolean(key, true));

        store.edit().putBoolean(key, true).apply();
        assertTrue(store.getBoolean(key, false));

        store.edit().putBoolean(key, false).apply();
        assertFalse(store.getBoolean(key, true));

        store.edit().remove(key).apply();
        assertTrue(store.getBoolean(key, true));
    }

    @Test
    public void contains() throws Exception {
        final String nokeylikemykey = "nokeylikemykey";
        assertFalse(store.contains(nokeylikemykey));
        store.edit().putBoolean(nokeylikemykey, true).apply();
        assertTrue(store.contains(nokeylikemykey));
        store.edit().remove(nokeylikemykey).apply();
        assertFalse(store.contains(nokeylikemykey));
    }

    @Test
    public void edit() throws Exception {
        SharedPreferences.Editor e = store.edit();
        assertNotNull(e);
        //noinspection ConstantConditions
        assertTrue(e instanceof PreferenceStore.PreferenceEditor);
    }

    @Test
    public void sharedPreferenceChangeListener() throws Exception {
        final List<String> keys = new ArrayList<>();

        SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
                    keys.add(key);
            }
        };
        store.registerOnSharedPreferenceChangeListener(listener);

        assertTrue(keys.isEmpty());
        store.edit().putBoolean("k1", true).apply();
        Thread.sleep(1000);
        assertEquals(1, keys.size());


        store.edit().putInt("k2", 99)
                .putBoolean("k1", false)
                .putFloat("k3", 40.65f)
                .putDouble("k4", 34537663647.246457)
                .apply();
        Thread.sleep(1000);
        assertEquals(5, keys.size());

        store.edit().clear().apply();
        Thread.sleep(1000);
        // clear doesn't notify changes
        assertEquals(5, keys.size());

        store.unregisterOnSharedPreferenceChangeListener(listener);
        store.edit().putBoolean("k1", true).apply();
        Thread.sleep(1000);
        assertEquals(5, keys.size());
    }
}