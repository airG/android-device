package com.airg.android.device;

import org.junit.BeforeClass;
import org.junit.Test;

import static android.os.Build.VERSION.SDK_INT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mahramf.
 */
public class ApiLevelAndroidTest {
    @BeforeClass
    public static void deviceAPILevelCantBeZero() {
        assertTrue(SDK_INT > 0);
    }

    @Test
    public void atLeast() throws Exception {
        assertTrue("current version is at least itself", ApiLevel.atLeast(SDK_INT));
        assertTrue("current version is at least the previous version (wat?)",
                ApiLevel.atLeast(SDK_INT - 1));
        assertFalse("current version is not at least the next one",
                ApiLevel.atLeast(SDK_INT + 1));
        assertTrue("Current API is at least API 1, no?", ApiLevel.atLeast(1));
    }

    @Test
    public void atMost() throws Exception {
        assertTrue("Current version is at most itself", ApiLevel.atMost(SDK_INT));
        assertTrue("Current version is at most the next one", ApiLevel.atMost(SDK_INT + 1));
        assertFalse("Current version is at most the previous one",
                ApiLevel.atMost(SDK_INT - 1));
    }

    @Test
    public void below() throws Exception {
        assertTrue("current version is below the next one", ApiLevel.below(SDK_INT + 1));
        assertFalse("current version is not below itself", ApiLevel.below(SDK_INT));
        assertFalse("current version is not below the previous one", ApiLevel.below(SDK_INT - 1));
    }

    @Test
    public void above() throws Exception {
        assertTrue("current version is above the previous one", ApiLevel.above(SDK_INT - 1));
        assertFalse("current version is not above itself", ApiLevel.above(SDK_INT));
        assertFalse("current version is not above the next one", ApiLevel.above(SDK_INT + 1));
    }

    @Test
    public void is() throws Exception {
        assertTrue("current version is current version", ApiLevel.is(SDK_INT));
        assertFalse("current version is not the next version", ApiLevel.is(SDK_INT + 1));
        assertFalse("current version is not the previous version", ApiLevel.is(SDK_INT - 1));
    }

    @Test
    public void get() throws Exception {
        assertEquals("current version is current version", SDK_INT, ApiLevel.get());
        assertTrue("current version is current version", ApiLevel.is(ApiLevel.get()));
    }
}