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
import android.support.annotation.DimenRes;
import android.support.test.InstrumentationRegistry;
import android.util.TypedValue;

import com.airg.android.device.test.R;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mahramf.
 */
public class UnitConverterAndroidTest {

    private static final float TIGHT_DELTA = 0.01f;
    private static final float ZERO_DELTA = 0.0f;
    private static final float ROUNDING_DELTA = 0.5f;

    @DimenRes
    private final static int[] allDips =  {
            R.dimen.dim0dp,
            R.dimen.dim5dp,
            R.dimen.dim23dp,
            R.dimen.dim55dp,
            R.dimen.dim123dp,
            R.dimen.dim467dp
    };

    private static final float[] expectedDips = { 0, 5, 23, 55, 123, 467 };
    private static int[] allDipsAsPx;
    private static float[] allDipsAsF;

    @DimenRes
    private final static int[] allSps = {
            R.dimen.dim0sp,
            R.dimen.dim12sp,
            R.dimen.dim40sp,
            R.dimen.dim69sp,
            R.dimen.dim123sp
    };

    private static final float[] expectedSps = { 0, 12, 40, 69, 123 };
    private static int[] allSpsAsPx;
    private static float[] allSpsAsF;

    @DimenRes
    private final static int[] allMms = {
            R.dimen.dim0mm,
            R.dimen.dim33mm,
            R.dimen.dim90mm,
            R.dimen.dim182mm
    };

    private static final float [] expectedMms = {0 , 33, 90, 182};
    private static final float [] expectedCms = {0 , 3.3f, 9.0f, 18.2f};
    private static int[] allMmsAsPx;
    private static float[] allMmsAsF;

    @DimenRes
    private final static int[] allPts = {
            R.dimen.dim0pt,
            R.dimen.dim8pt,
            R.dimen.dim72pt,
            R.dimen.dim169pt,
            R.dimen.dim666pt
    };

    private static final float[] expectedPts = {0, 8, 72, 169, 666};
    private static int[] allPtsAsPx;
    private static float[] allPtsAsF;

    @DimenRes
    private final static int[] allIns = {
            R.dimen.dim0in,
            R.dimen.dim2in,
            R.dimen.dim31in,
            R.dimen.dim101in,
            R.dimen.dim1234in};

    private static final float [] expectedIns = {0, 2, 31, 101, 1234};
    private static int[] allInsAsPx;
    private static float[] allInsAsF;

    private Context context;
    private UnitConverter converter;


    @Test
    public void getDisplayMetrics() throws Exception {
        assertNotNull(new UnitConverter(context).getDisplayMetrics());
    }

    @Test
    public void f2dp() throws Exception {
        assertEquals(0f, converter.f2dp(0), TIGHT_DELTA);

        for (int i = 0; i < allDips.length; i++)
            assertEquals(expectedDips[i], converter.f2dp(allDipsAsF[i]), TIGHT_DELTA);
    }

    @Test
    public void f2dpStatic() throws Exception {
        assertEquals(0f, UnitConverter.f2dp(context, 0), TIGHT_DELTA);

        for (int i = 0; i < allDips.length; i++)
            assertEquals(expectedDips[i], UnitConverter.f2dp(context, allDipsAsF[i]), TIGHT_DELTA);
    }

    @Test
    public void f2sp() throws Exception {
        assertEquals(0f, converter.f2sp(0), TIGHT_DELTA);

        for (int i = 0; i < allSps.length; i++)
            assertEquals(expectedSps[i], converter.f2sp(allSpsAsF[i]), TIGHT_DELTA);
    }

    @Test
    public void f2spStatic() throws Exception {
        assertEquals(0f, UnitConverter.f2sp(context, 0), TIGHT_DELTA);

        for (int i = 0; i < allSps.length; i++)
            assertEquals(expectedSps[i], UnitConverter.f2sp(context, allSpsAsF[i]), TIGHT_DELTA);
    }

    @Test
    public void f2pt() throws Exception {
        assertEquals(0f, converter.f2pt(0), TIGHT_DELTA);

        for (int i = 0; i < allPts.length; i++)
            assertEquals(expectedPts[i], converter.f2pt(allPtsAsF[i]), TIGHT_DELTA);
    }

    @Test
    public void f2ptStatic() throws Exception {
        assertEquals(0f, UnitConverter.f2pt(context, 0), TIGHT_DELTA);

        for (int i = 0; i < allPts.length; i++)
            assertEquals(expectedPts[i], UnitConverter.f2pt(context, allPtsAsF[i]), TIGHT_DELTA);
    }

    @Test
    public void f2mm() throws Exception {
        assertEquals(0f, converter.f2mm(0), TIGHT_DELTA);

        for (int i = 0; i < allMms.length; i++)
            assertEquals(expectedMms[i], converter.f2mm(allMmsAsF[i]), TIGHT_DELTA);
    }

    @Test
    public void f2mmStatic() throws Exception {
        assertEquals(0f, UnitConverter.f2mm(context, 0), TIGHT_DELTA);

        for (int i = 0; i < allMms.length; i++)
            assertEquals(expectedMms[i], UnitConverter.f2mm(context, allMmsAsF[i]), TIGHT_DELTA);
    }

    @Test
    public void f2cm() throws Exception {
        assertEquals(0f, converter.f2cm(0), TIGHT_DELTA);

        for (int i = 0; i < allMms.length; i++)
            assertEquals(expectedCms[i], converter.f2cm(allMmsAsF[i]), TIGHT_DELTA);
    }

    @Test
    public void f2cmStatic() throws Exception {
        assertEquals(0f, UnitConverter.f2cm(context, 0), TIGHT_DELTA);

        for (int i = 0; i < allMms.length; i++)
            assertEquals(expectedCms[i], UnitConverter.f2cm(context, allMmsAsF[i]), TIGHT_DELTA);
    }

    @Test
    public void f2in() throws Exception {
        assertEquals(0f, converter.f2in(0), TIGHT_DELTA);

        for (int i = 0; i < allIns.length; i++)
            assertEquals(expectedIns[i], converter.f2in(allInsAsF[i]), TIGHT_DELTA);
    }

    @Test
    public void f2inStatic() throws Exception {
        assertEquals(0f, UnitConverter.f2in(context, 0), TIGHT_DELTA);

        for (int i = 0; i < allIns.length; i++)
            assertEquals(expectedIns[i], UnitConverter.f2in(context, allInsAsF[i]), TIGHT_DELTA);
    }

    @Test
    public void px2dp() throws Exception {
        assertEquals(0, converter.px2dp(0), TIGHT_DELTA);

        for (int i = 0; i < allDipsAsF.length; i++)
            assertEquals(expectedDips[i], converter.px2dp(allDipsAsPx[i]), ROUNDING_DELTA);
    }

    @Test
    public void px2dpStatic() throws Exception {
        assertEquals(0, UnitConverter.px2dp(context, 0), TIGHT_DELTA);

        for (int i = 0; i < allDipsAsF.length; i++)
            assertEquals(expectedDips[i], UnitConverter.px2dp(context, allDipsAsPx[i]), ROUNDING_DELTA);
    }

    @Test
    public void dp2px() throws Exception {
        assertEquals(0, converter.dp2px(0));

        for (int i = 0; i < expectedDips.length; i++)
            assertEquals(allDipsAsPx[i], converter.dp2px(expectedDips[i]));
    }

    @Test
    public void dp2pxStatic() throws Exception {
        assertEquals(0, UnitConverter.dp2px(context, 0));

        for (int i = 0; i < expectedDips.length; i++)
            assertEquals(allDipsAsPx[i], UnitConverter.dp2px(context, expectedDips[i]));
    }

    @Test
    public void px2sp() throws Exception {
        assertEquals(0, converter.px2sp(0), TIGHT_DELTA);

        for (int i = 0; i < allSpsAsF.length; i++)
            assertEquals(expectedSps[i], converter.px2sp(allSpsAsPx[i]), ROUNDING_DELTA);
    }

    @Test
    public void px2spStatic() throws Exception {
        assertEquals(0, UnitConverter.px2sp(context, 0), TIGHT_DELTA);

        for (int i = 0; i < allSpsAsF.length; i++)
            assertEquals(expectedSps[i], UnitConverter.px2sp(context, allSpsAsPx[i]), ROUNDING_DELTA);
    }

    @Test
    public void sp2px() throws Exception {
        assertEquals(0, converter.sp2px(0));

        for (int i = 0; i < expectedSps.length; i++)
            assertEquals(allSpsAsPx[i], converter.sp2px(expectedSps[i]));
    }

    @Test
    public void sp2pxStatic() throws Exception {
        assertEquals(0, UnitConverter.sp2px(context, 0));

        for (int i = 0; i < expectedSps.length; i++)
            assertEquals(allSpsAsPx[i], UnitConverter.sp2px(context, expectedSps[i]));
    }

    @Test
    public void pxs2dps() throws Exception {
        final float[] empty = converter.pxs2dps();
        assertNotNull(empty);
        assertEquals(0, empty.length);

        final float[] actual = converter.pxs2dps(allDipsAsPx);

        for (int i = 0; i < expectedDips.length; i++)
            assertEquals(expectedDips[i], actual[i], ROUNDING_DELTA);
    }

    @Test
    public void pxs2dpsStatic() throws Exception {
        final float[] empty = UnitConverter.pxs2dps(context);
        assertNotNull(empty);
        assertEquals(0, empty.length);

        final float[] actual = UnitConverter.pxs2dps(context, allDipsAsPx);

        for (int i = 0; i < expectedDips.length; i++)
            assertEquals(expectedDips[i], actual[i], ROUNDING_DELTA);
    }

    @Test
    public void dps2pxs() throws Exception {
        final int[] empty = converter.dps2pxs();
        assertNotNull(empty);
        assertEquals(0, empty.length);

        final int[] actual = converter.dps2pxs(expectedDips);

        for (int i = 0; i < allDipsAsPx.length; i++)
            assertEquals(allDipsAsPx[i], actual[i]);
    }

    @Test
    public void dps2pxsStatic() throws Exception {
        final int[] empty = UnitConverter.dps2pxs(context);
        assertNotNull(empty);
        assertEquals(0, empty.length);

        final int[] actual = UnitConverter.dps2pxs(context, expectedDips);

        for (int i = 0; i < allDipsAsPx.length; i++)
            assertEquals(allDipsAsPx[i], actual[i]);
    }

    @Test
    public void pxs2sps() throws Exception {
        final float[] empty = converter.pxs2sps();
        assertNotNull(empty);
        assertEquals(0, empty.length);

        final float[] actual = converter.pxs2sps(allSpsAsPx);

        for (int i = 0; i < expectedSps.length; i++)
            assertEquals(expectedSps[i], actual[i], ROUNDING_DELTA);
    }

    @Test
    public void pxs2spsStatic() throws Exception {
        final float[] empty = UnitConverter.pxs2sps(context);
        assertNotNull(empty);
        assertEquals(0, empty.length);

        final float[] actual = UnitConverter.pxs2sps(context, allSpsAsPx);

        for (int i = 0; i < expectedSps.length; i++)
            assertEquals(expectedSps[i], actual[i], ROUNDING_DELTA);
    }

    @Test
    public void sps2pxs() throws Exception {
        final int[] empty = converter.sps2pxs();
        assertNotNull(empty);
        assertEquals(0, empty.length);

        final int[] actual = converter.sps2pxs(expectedSps);

        for (int i = 0; i < allSpsAsPx.length; i++)
            assertEquals(allSpsAsPx[i], actual[i]);
    }

    @Test
    public void sps2pxsStatic() throws Exception {
        final int[] empty = UnitConverter.sps2pxs(context);
        assertNotNull(empty);
        assertEquals(0, empty.length);

        final int[] actual = UnitConverter.sps2pxs(context, expectedSps);

        for (int i = 0; i < allSpsAsPx.length; i++)
            assertEquals(allSpsAsPx[i], actual[i]);
    }

    @Test
    public void convert() throws Exception {
        assertEquals(5f, converter.convert(5f, TypedValue.COMPLEX_UNIT_DIP, TypedValue.COMPLEX_UNIT_DIP), ZERO_DELTA);
        assertEquals(21345f, converter.convert(21345f, TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_PX), ZERO_DELTA);
        assertEquals(668f, converter.convert(668f, TypedValue.COMPLEX_UNIT_SP, TypedValue.COMPLEX_UNIT_SP), ZERO_DELTA);
        assertEquals(33f, converter.convert(33f, TypedValue.COMPLEX_UNIT_PT, TypedValue.COMPLEX_UNIT_PT), ZERO_DELTA);
        assertEquals(50f, converter.convert(50f, TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_IN), ZERO_DELTA);
        assertEquals(1003f, converter.convert(1003f, TypedValue.COMPLEX_UNIT_MM, TypedValue.COMPLEX_UNIT_MM), ZERO_DELTA);

        assertEquals(72f, converter.convert(1f, TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_PT), TIGHT_DELTA);
        assertEquals(1f, converter.convert(72f, TypedValue.COMPLEX_UNIT_PT, TypedValue.COMPLEX_UNIT_IN), TIGHT_DELTA);

        assertEquals(1f, converter.convert(25.4f, TypedValue.COMPLEX_UNIT_MM, TypedValue.COMPLEX_UNIT_IN), TIGHT_DELTA);
        assertEquals(25.4f, converter.convert(1f, TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_MM), TIGHT_DELTA);

        for (int i = 0; i < allDips.length; i++) {
            assertEquals(allDipsAsPx[i], converter.convert(expectedDips[i], TypedValue.COMPLEX_UNIT_DIP, TypedValue.COMPLEX_UNIT_PX), ROUNDING_DELTA);
            assertEquals(expectedDips[i], converter.convert(allDipsAsPx[i], TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_DIP), ROUNDING_DELTA);
        }

        for (int i = 0; i < allSps.length; i++) {
            assertEquals(allSpsAsPx[i], converter.convert(expectedSps[i], TypedValue.COMPLEX_UNIT_SP, TypedValue.COMPLEX_UNIT_PX), ROUNDING_DELTA);
            assertEquals(expectedSps[i], converter.convert(allSpsAsPx[i], TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_SP), ROUNDING_DELTA);
        }

        for (int i = 0; i < allPts.length; i++) {
            assertEquals(allPtsAsPx[i], converter.convert(expectedPts[i], TypedValue.COMPLEX_UNIT_PT, TypedValue.COMPLEX_UNIT_PX), ROUNDING_DELTA);
            assertEquals(expectedPts[i], converter.convert(allPtsAsPx[i], TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_PT), ROUNDING_DELTA);
        }

        for (int i = 0; i < allIns.length; i++) {
            assertEquals(allInsAsPx[i], converter.convert(expectedIns[i], TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_PX), ROUNDING_DELTA);
            assertEquals(expectedIns[i], converter.convert(allInsAsPx[i], TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_IN), ROUNDING_DELTA);
        }

        for (int i = 0; i < allMms.length; i++) {
            assertEquals(allMmsAsPx[i], converter.convert(expectedMms[i], TypedValue.COMPLEX_UNIT_MM, TypedValue.COMPLEX_UNIT_PX), ROUNDING_DELTA);
            assertEquals(expectedMms[i], converter.convert(allMmsAsPx[i], TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_MM), ROUNDING_DELTA);
        }
    }

    @Test
    public void convertStatic() throws Exception {
        assertEquals(5f, UnitConverter.convert(context, 5f, TypedValue.COMPLEX_UNIT_DIP, TypedValue.COMPLEX_UNIT_DIP), ZERO_DELTA);
        assertEquals(21345f, UnitConverter.convert(context, 21345f, TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_PX), ZERO_DELTA);
        assertEquals(668f, UnitConverter.convert(context, 668f, TypedValue.COMPLEX_UNIT_SP, TypedValue.COMPLEX_UNIT_SP), ZERO_DELTA);
        assertEquals(33f, UnitConverter.convert(context, 33f, TypedValue.COMPLEX_UNIT_PT, TypedValue.COMPLEX_UNIT_PT), ZERO_DELTA);
        assertEquals(50f, UnitConverter.convert(context, 50f, TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_IN), ZERO_DELTA);
        assertEquals(1003f, UnitConverter.convert(context, 1003f, TypedValue.COMPLEX_UNIT_MM, TypedValue.COMPLEX_UNIT_MM), ZERO_DELTA);

        assertEquals(72f, UnitConverter.convert(context, 1f, TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_PT), TIGHT_DELTA);
        assertEquals(1f, UnitConverter.convert(context, 72f, TypedValue.COMPLEX_UNIT_PT, TypedValue.COMPLEX_UNIT_IN), TIGHT_DELTA);

        assertEquals(1f, UnitConverter.convert(context, 25.4f, TypedValue.COMPLEX_UNIT_MM, TypedValue.COMPLEX_UNIT_IN), TIGHT_DELTA);
        assertEquals(25.4f, UnitConverter.convert(context, 1f, TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_MM), TIGHT_DELTA);

        for (int i = 0; i < allDips.length; i++) {
            assertEquals(allDipsAsPx[i], UnitConverter.convert(context, expectedDips[i], TypedValue.COMPLEX_UNIT_DIP, TypedValue.COMPLEX_UNIT_PX), ROUNDING_DELTA);
            assertEquals(expectedDips[i], UnitConverter.convert(context, allDipsAsPx[i], TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_DIP), ROUNDING_DELTA);
        }

        for (int i = 0; i < allSps.length; i++) {
            assertEquals(allSpsAsPx[i], UnitConverter.convert(context, expectedSps[i], TypedValue.COMPLEX_UNIT_SP, TypedValue.COMPLEX_UNIT_PX), ROUNDING_DELTA);
            assertEquals(expectedSps[i], UnitConverter.convert(context, allSpsAsPx[i], TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_SP), ROUNDING_DELTA);
        }

        for (int i = 0; i < allPts.length; i++) {
            assertEquals(allPtsAsPx[i], UnitConverter.convert(context, expectedPts[i], TypedValue.COMPLEX_UNIT_PT, TypedValue.COMPLEX_UNIT_PX), ROUNDING_DELTA);
            assertEquals(expectedPts[i], UnitConverter.convert(context, allPtsAsPx[i], TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_PT), ROUNDING_DELTA);
        }

        for (int i = 0; i < allIns.length; i++) {
            assertEquals(allInsAsPx[i], UnitConverter.convert(context, expectedIns[i], TypedValue.COMPLEX_UNIT_IN, TypedValue.COMPLEX_UNIT_PX), ROUNDING_DELTA);
            assertEquals(expectedIns[i], UnitConverter.convert(context, allInsAsPx[i], TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_IN), ROUNDING_DELTA);
        }

        for (int i = 0; i < allMms.length; i++) {
            assertEquals(allMmsAsPx[i], UnitConverter.convert(context, expectedMms[i], TypedValue.COMPLEX_UNIT_MM, TypedValue.COMPLEX_UNIT_PX), ROUNDING_DELTA);
            assertEquals(expectedMms[i], UnitConverter.convert(context, allMmsAsPx[i], TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_MM), ROUNDING_DELTA);
        }
    }

    @BeforeClass
    public static void loadFromResources () {
        final Context context = InstrumentationRegistry.getTargetContext();
        final Resources resources = context.getResources();

        allDipsAsPx = new int[allDips.length];
        allDipsAsF = new float[allDips.length];

        for (int i = 0; i < allDips.length; i++) {
            allDipsAsPx[i] = resources.getDimensionPixelSize(allDips[i]);
            allDipsAsF[i] = resources.getDimension(allDips[i]);
        }

        allSpsAsPx = new int[allSps.length];
        allSpsAsF = new float[allSps.length];

        for (int i = 0; i < allSps.length; i++) {
            allSpsAsPx[i] = resources.getDimensionPixelSize(allSps[i]);
            allSpsAsF[i] = resources.getDimension(allSps[i]);
        }

        allMmsAsPx = new int[allMms.length];
        allMmsAsF = new float[allMms.length];
        for (int i = 0; i < allMms.length; i++) {
            allMmsAsPx[i] = resources.getDimensionPixelSize(allMms[i]);
            allMmsAsF[i] = resources.getDimension(allMms[i]);
        }

        allPtsAsPx = new int[allPts.length];
        allPtsAsF = new float[allPts.length];
        for (int i = 0; i < allPts.length; i++) {
            allPtsAsPx[i] = resources.getDimensionPixelSize(allPts[i]);
            allPtsAsF[i] = resources.getDimension(allPts[i]);
        }

        allInsAsPx = new int[allIns.length];
        allInsAsF = new float[allIns.length];
        for (int i = 0; i < allIns.length; i++) {
            allInsAsPx[i] = resources.getDimensionPixelSize(allIns[i]);
            allInsAsF[i] = resources.getDimension(allIns[i]);
        }
    }

    @Before
    public void initialize() {
        context = InstrumentationRegistry.getTargetContext();
        converter = new UnitConverter(context);
    }
}