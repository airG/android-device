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
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.airg.android.device.test.R;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by mahramf.
 */
public class UnitConverterAndroidTest {

    private static final float DELTA = 0.01f;

    private Context context;
    private Resources resources;

    @Before
    public void loadFromResources () {
        context = InstrumentationRegistry.getTargetContext();
        resources = context.getResources();


        // todo: load stuff
    }

    @Test
    public void px2dp () {
        final int dim5dppx = resources.getDimensionPixelSize(R.dimen.dim5dp);
        assertEquals(5, UnitConverter.px2dp(context, dim5dppx), DELTA);
        assertEquals(0, UnitConverter.px2dp(context, 0), DELTA);
    }

    @Test
    public void dp2px () {
        final int dim5dppx = resources.getDimensionPixelSize(R.dimen.dim5dp);
        assertEquals(dim5dppx, UnitConverter.dp2px(context, 5));
        assertEquals(0, UnitConverter.dp2px(context, 0));
    }

    @Test
    public void px2sp () {
        final int dim12sppx = resources.getDimensionPixelSize(R.dimen.dim12sp);
        assertEquals(12, UnitConverter.px2sp(context, dim12sppx), DELTA);
        assertEquals(0, UnitConverter.px2sp(context, 0), DELTA);
    }

    @Test
    public void sp2px () {
        final int dim12sppx = resources.getDimensionPixelSize(R.dimen.dim12sp);
        assertEquals(dim12sppx, UnitConverter.sp2px(context, 12));
        assertEquals(0, UnitConverter.sp2px(context, 0));
    }

    @Test
    public void f2dp () {
        final float dim5d = resources.getDimension(R.dimen.dim5dp);
        assertEquals(5.0f, UnitConverter.f2dp(context, dim5d), DELTA);
        assertEquals(0f, UnitConverter.f2dp(context, 0), DELTA);
    }

    @Test
    public void dp2f () {
        final float dim5d = resources.getDimension(R.dimen.dim5dp);
        assertEquals(dim5d, UnitConverter.dp2f(context, 5f), DELTA);
        assertEquals(0f, UnitConverter.f2dp(context, 0), DELTA);
    }

    @Test
    public void f2sp () {

    }

    @Test
    public void pxs2dps () {
        // TODO: test with null
        // TODO: test with empty array
        // TODO: test with mixed values
        fail ("not implemented");
    }

    @Test
    public void dps2pxs () {
        // TODO: test with null
        // TODO: test with empty array
        // TODO: test with mixed values
        fail ("not implemented");
    }

    @Test
    public void pxs2sps () {
        // TODO: test with null
        // TODO: test with empty array
        // TODO: test with mixed values
        fail ("not implemented");
    }

    @Test
    public void sps2pxs () {
        // TODO: test with null
        // TODO: test with empty array
        // TODO: test with mixed values
        fail ("not implemented");
    }
}