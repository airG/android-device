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

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A {@link SharedPreferences} implementation with a few extra features. This class wraps an
 * Android native {@link SharedPreferences} instance for maximum compatibility.
 *
 * @author Mahram Z. Foadi
 */
@SuppressWarnings({"UnusedDeclaration", "WeakerAccess"})
public class PreferenceStore implements SharedPreferences {
    private final SharedPreferences store;

    /**
     * Create/access the default {@link SharedPreferences}. This method wraps the
     * {@link PreferenceManager#getDefaultSharedPreferences(Context)} method.
     *
     * @param context context to access system paths
     */
    public PreferenceStore(final Context context) {
        store = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Create/access a non-default {@link SharedPreferences} store. This method wraps the
     * {@link Context#getSharedPreferences(String, int)} with mode set to
     * {@link Context#MODE_PRIVATE}.
     *
     * @param context context to access system paths
     * @param name    preferences store file name
     */
    public PreferenceStore(final Context context, final String name) {
        this(context, name, Context.MODE_PRIVATE);
    }

    /**
     * Create/access a non-default {@link SharedPreferences} store. This method wraps the
     * {@link Context#getSharedPreferences(String, int)}.
     *
     * @param context context to access system paths
     * @param name    preferences store file name
     * @param mode    preference store access mode (e.g. {@link Context#MODE_PRIVATE}
     */
    public PreferenceStore(final Context context, final String name, final int mode) {
        store = context.getSharedPreferences(name, mode);
    }

    /**
     * See {@link SharedPreferences#getAll()}
     */
    @Override
    public Map<String, ?> getAll() {
        return store.getAll();
    }

    /**
     * See {@link SharedPreferences#getString(String, String)}
     */
    @Nullable
    @Override
    public String getString(final String key, final String defValue) {
        return store.getString(key, defValue);
    }

    /**
     * See {@link SharedPreferences#getStringSet(String, Set)}
     */
    @Nullable
    @Override
    public Set<String> getStringSet(final String key, final Set<String> defValues) {
        final String array = store.getString(key, null);

        if (null == array) return defValues;

        return new Gson().fromJson(array,
                new TypeToken<Set<String>>() {
                }.getType());
    }

    /**
     * Get a stored list of strings with preserved order.
     * @param key value key
     * @param defValues default value to return if no data is stored with the specified key
     * @return The stored list of strings or default value if none found.
     */
    @Nullable
    public List<String> getStringList(final String key, final List<String> defValues) {
        final String array = store.getString(key, null);

        if (null == array) return defValues;

        return new Gson().fromJson(array,
                new TypeToken<List<String>>() {
                }.getType());
    }

    /**
     * See {@link SharedPreferences#getInt(String, int)}
     */
    @Override
    public int getInt(final String key, final int defValue) {
        return store.getInt(key, defValue);
    }

    /**
     * See {@link SharedPreferences#getLong(String, long)}
     */
    @Override
    public long getLong(final String key, final long defValue) {
        return store.getLong(key, defValue);
    }

    /**
     * See {@link SharedPreferences#getFloat(String, float)}
     */
    @Override
    public float getFloat(final String key, final float defValue) {
        return store.getFloat(key, defValue);
    }

    /**
     * Access a stored double value in the {@link SharedPreferences} store. See
     * http://stackoverflow.com/questions/16319237/cant-put-double-sharedpreferences/18098090#18098090
     * for more.
     *
     * @param key      preference key
     * @param defValue default value
     * @return stored double value or <code>defValue</code> if no stored value is found for <code>key</code>
     */
    public double getDouble(final String key, final double defValue) {
        return store.contains(key)
                ? Double.longBitsToDouble(store.getLong(key, 0))
                : defValue;
    }

    /**
     * See {@link SharedPreferences#getBoolean(String, boolean)}
     */
    @Override
    public boolean getBoolean(final String key, final boolean defValue) {
        return store.getBoolean(key, defValue);
    }

    /**
     * See {@link SharedPreferences#contains(String)}
     */
    @Override
    public boolean contains(final String key) {
        return store.contains(key);
    }

    /**
     * See {@link SharedPreferences#edit()}
     */
    @Override
    public PreferenceEditor edit() {
        return new PreferenceEditor(store.edit());
    }

    /**
     * See {@link SharedPreferences#registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener)}
     */
    @Override
    public void registerOnSharedPreferenceChangeListener(final OnSharedPreferenceChangeListener listener) {
        store.registerOnSharedPreferenceChangeListener(listener);
    }

    /**
     * See {@link SharedPreferences#unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener)}
     */
    @Override
    public void unregisterOnSharedPreferenceChangeListener(final OnSharedPreferenceChangeListener listener) {
        store.unregisterOnSharedPreferenceChangeListener(listener);
    }

    /**
     * An implementation of {@link Editor} with a few additions.
     */
    public static class PreferenceEditor implements Editor {
        private final Editor editor;

        private PreferenceEditor(final Editor e) {
            editor = e;
        }

        /**
         * See {@link Editor#putString(String, String)}
         */
        @Override
        public PreferenceEditor putString(final String key, final String value) {
            editor.putString(key, value);
            return this;
        }

        /**
         * See {@link Editor#putStringSet(String, Set)}
         */
        @Override
        public PreferenceEditor putStringSet(final String key, final Set<String> values) {
            editor.putString(key, new Gson().toJson(values));
            return this;
        }

        /**
         * Similar to {@link Editor#putStringSet(String, Set)}, but preserves the order of the list
         *
         * @param key    value key
         * @param values values to store
         * @return this {@link PreferenceEditor} for your chaining pleasure.
         */
        public PreferenceEditor putStringList(final String key, final List<String> values) {
            editor.putString(key, new Gson().toJson(values));
            return this;
        }

        /**
         * See {@link Editor#putInt(String, int)}
         */
        @Override
        public PreferenceEditor putInt(final String key, final int value) {
            editor.putInt(key, value);
            return this;
        }

        /**
         * See {@link Editor#putLong(String, long)}
         */
        @Override
        public PreferenceEditor putLong(final String key, final long value) {
            editor.putLong(key, value);
            return this;
        }

        /**
         * See {@link Editor#putFloat(String, float)}
         */
        @Override
        public PreferenceEditor putFloat(final String key, final float value) {
            editor.putFloat(key, value);
            return this;
        }

        /**
         * Efficiently stores a double value in the {@link SharedPreferences} store because you
         * don't want to store a double as a string (inefficient) or float (loss of precision). See
         * http://stackoverflow.com/questions/16319237/cant-put-double-sharedpreferences/18098090#18098090
         *
         * @param key   store key
         * @param value value to store
         * @return this {@link PreferenceEditor} for your chaining pleasure.
         */
        public PreferenceEditor putDouble(final String key, final double value) {
            editor.putLong(key, Double.doubleToRawLongBits(value));
            return this;
        }

        /**
         * See {@link Editor#putBoolean(String, boolean)}
         */
        @Override
        public PreferenceEditor putBoolean(final String key, final boolean value) {
            editor.putBoolean(key, value);
            return this;
        }

        /**
         * See {@link Editor#remove(String)}
         */
        @Override
        public PreferenceEditor remove(final String key) {
            editor.remove(key);
            return this;
        }

        /**
         * See {@link Editor#clear()}
         */
        @Override
        public PreferenceEditor clear() {
            editor.clear();
            return this;
        }

        /**
         * See {@link Editor#commit()}
         */
        @Override
        public boolean commit() {
            return editor.commit();
        }

        /**
         * See {@link Editor#apply()}
         */
        @Override
        public void apply() {
            editor.apply();
        }
    }
}
