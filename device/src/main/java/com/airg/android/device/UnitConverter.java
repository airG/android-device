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
import android.util.DisplayMetrics;
import android.util.TypedValue;

import lombok.Getter;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.util.TypedValue.COMPLEX_UNIT_IN;
import static android.util.TypedValue.COMPLEX_UNIT_MM;
import static android.util.TypedValue.COMPLEX_UNIT_PT;
import static android.util.TypedValue.COMPLEX_UNIT_PX;
import static android.util.TypedValue.COMPLEX_UNIT_SP;
import static com.airg.android.device.Device.getDefaultDisplayMetrics;

/**
 * This class provides methods to convert between different size units in Android.
 */

@SuppressWarnings({"UnusedDeclaration", "WeakerAccess"})
public final class UnitConverter {

    @Getter
    final DisplayMetrics displayMetrics;

    public UnitConverter(final Context context) {
        this(getDefaultDisplayMetrics(context));
    }

    public UnitConverter(final DisplayMetrics dm) {
        displayMetrics = dm;
    }

    /* ----------------- Instance Methods ----------------- */

    /**
     * Convert a dimension to a complex size
     *
     * @param f        dimension value
     * @param fromUnit source dimension unit
     * @param toUnit   target dimension unit
     * @return converted dimension value (in pixels or portions thereof)
     */
    public float convert(final float f, final int fromUnit, final int toUnit) {
        if (!validUnit(fromUnit))
            throw new IllegalArgumentException("Unknown source unit");

        if (!validUnit(toUnit))
            throw new IllegalArgumentException("Unknown target unit");

        if (0f == f) return 0;
        if (fromUnit == toUnit) return f;

        final float value = TypedValue.applyDimension(fromUnit, f, displayMetrics);

        // inverting applyDimension, basically
        switch (toUnit) {
            case COMPLEX_UNIT_PX:
                return value;
            case COMPLEX_UNIT_DIP:
                return f2dp(value);
            case COMPLEX_UNIT_SP:
                return f2sp(value);
            case COMPLEX_UNIT_PT:
                return f2pt(value);
            case COMPLEX_UNIT_IN:
                return f2in(value);
            case COMPLEX_UNIT_MM:
                return f2mm(value);
            default:
                throw new IllegalArgumentException("Unknown target unit");
        }
    }

    /**
     * Convert a dimension to points (pt)
     *
     * @param f simension value
     * @return converted to points
     */
    public float f2pt(final float f) {
        return 0f == f ? 0f : (f * 72f) / displayMetrics.xdpi;
    }

    /**
     * Convert a dimension to device independent pixels (dips)
     *
     * @param f dimension size
     * @return dimension size in dips
     */
    public float f2dp(final float f) {
        return 0f == f ? 0f : f / displayMetrics.density;
    }

    /**
     * Convert a dimension to scaled pixels
     *
     * @param f dimension size
     * @return dimension size in sps
     */
    public float f2sp(final float f) {
        return 0f == f ? 0f : f / displayMetrics.scaledDensity;
    }

    /**
     * Convert a dimension to inches
     *
     * @param f dimension size
     * @return dimension size in inches
     */
    public float f2in(final float f) {
        return 0f == f ? 0f : f / displayMetrics.xdpi;
    }

    /**
     * Convert a dimension to millimeters
     *
     * @param f dimension size
     * @return dimension size in millimeters
     */
    public float f2mm(final float f) {
        return 0f == f ? 0f : f2in(f) * 25.4f;
    }

    /**
     * Convert a dimension to centimeters
     *
     * @param f dimension size
     * @return dimension size in centimeters
     */
    public float f2cm(final float f) {
        return 0f == f ? 0f : f2in(f) * 2.54f;
    }

    /**
     * Convert a pixel size to dps
     *
     * @param px pixel size
     * @return dp equivalent of the given pixel size.
     */
    public float px2dp(final int px) {
        return f2dp((float) px);
    }

    /**
     * Convert a single dp value to pixels
     *
     * @param dp dp size
     * @return pixel equivalent of provided dps
     */
    public int dp2px(final float dp) {
        return 0 == dp ? 0 : Math.round(dp * displayMetrics.density);
    }

    /**
     * Convert a pixel size to sps
     *
     * @param px pixel size
     * @return sp equivalent of the given pixel size.
     */
    public float px2sp(final int px) {
        return 0 == px ? 0f : px / displayMetrics.scaledDensity;
    }

    /**
     * Convert a single sp value to pixels
     *
     * @param sp sp size
     * @return pixel equivalent of provided sps
     */
    public int sp2px(final float sp) {
        return 0f == sp ? 0 : Math.round(sp * displayMetrics.scaledDensity);
    }

    /**
     * Convert at an array of pixel sizes to dps
     *
     * @param pxs pixel sizes
     * @return converted equivalent dp values
     */
    public float[] pxs2dps(final int... pxs) {
        if (null == pxs) return null;
        if (pxs.length == 0) return new float[0];

        final float[] dps = new float[pxs.length];

        for (int i = 0; i < pxs.length; i++)
            dps[i] = px2dp(pxs[i]);

        return dps;
    }

    /**
     * Convert at an array of dp sizes to pixels
     *
     * @param dps dp sizes
     * @return converted equivalent pixel values
     */
    public int[] dps2pxs(final float... dps) {
        if (null == dps) return null;
        if (dps.length == 0) return new int[0];

        final int[] pxs = new int[dps.length];

        for (int i = 0; i < dps.length; i++)
            pxs[i] = dp2px(dps[i]);

        return pxs;
    }

    /**
     * Convert at an array of pixel sizes to sps
     *
     * @param pxs pixel sizes
     * @return converted equivalent sp values
     */
    public float[] pxs2sps(final int... pxs) {
        if (null == pxs) return null;
        if (pxs.length == 0) return new float[0];

        final float[] sps = new float[pxs.length];

        for (int i = 0; i < pxs.length; i++)
            sps[i] = px2sp(pxs[i]);

        return sps;
    }

    /**
     * Convert at an array of sp sizes to pixels
     *
     * @param sps sp sizes
     * @return converted equivalent pixel values
     */
    public int[] sps2pxs(final float... sps) {
        if (null == sps) return null;
        if (sps.length == 0) return new int[0];

        final int[] pxs = new int[sps.length];

        for (int i = 0; i < sps.length; i++)
            pxs[i] = sp2px(sps[i]);

        return pxs;
    }

    /* ----------------- Static Methods ----------------- */

    /**
     * See {@link #convert(float, int, int)}
     */
    public static float convert(final Context context,
                                final float f,
                                final int fromUnit,
                                final int toUnit) {
        return new UnitConverter(context).convert(f, fromUnit, toUnit);
    }

    /**
     * See {@link #f2pt(float)}
     */
    public static float f2pt(final Context context, final float f) {
        return 0f == f ? 0f : new UnitConverter(context).f2pt(f);
    }

    /**
     * See {@link #f2dp(float)}
     */
    public static float f2dp(final Context context, final float f) {
        return 0f == f ? 0f : new UnitConverter(context).f2dp(f);
    }

    /**
     * See {@link #f2sp(float)}
     */
    public static float f2sp(final Context context, final float f) {
        return 0f == f ? 0f : new UnitConverter(context).f2sp(f);
    }

    /**
     * See {@link #f2in(float)}
     */
    public static float f2in(final Context context, final float f) {
        return 0f == f ? 0f : new UnitConverter(context).f2in(f);
    }

    /**
     * See {@link #f2mm(float)}
     */
    public static float f2mm(final Context context, final float f) {
        return 0f == f ? 0f : new UnitConverter(context).f2mm(f);
    }

    /**
     * See {@link #f2cm(float)}
     */
    public static float f2cm(final Context context, final float f) {
        return 0f == f ? 0f : new UnitConverter(context).f2cm(f);
    }

    /**
     * See {@link #px2dp(int)}
     */
    public static float px2dp(final Context context, final int px) {
        return 0 == px ? 0 : new UnitConverter(context).px2dp(px);
    }

    /**
     * See {@link #dp2px(float)}
     */
    public static int dp2px(final Context context, final float dp) {
        return 0f == dp ? 0 : new UnitConverter(context).dp2px(dp);
    }

    /**
     * See {@link #px2sp(int)}
     */
    public static float px2sp(final Context context, final int px) {
        return 0 == px ? 0 : new UnitConverter(context).px2sp(px);
    }

    /**
     * See {@link #sp2px(float)}
     */
    public static int sp2px(final Context context, final float sp) {
        return 0f == sp ? 0 : new UnitConverter(context).sp2px(sp);
    }

    /**
     * See {@link #pxs2dps(int...)}
     */
    public static float[] pxs2dps(final Context context, final int... pxs) {
        return new UnitConverter(context).pxs2dps(pxs);
    }

    /**
     * See {@link #dps2pxs(float...)}
     */
    public static int[] dps2pxs(final Context context, final float... dps) {
        return new UnitConverter(context).dps2pxs(dps);
    }

    /**
     * See {@link #pxs2sps(int...)}
     */
    public static float[] pxs2sps(final Context context, final int... pxs) {
        return new UnitConverter(context).pxs2sps(pxs);
    }

    /**
     * See {@link #sps2pxs(float...)}
     */
    public static int[] sps2pxs(final Context context, final float... sps) {
        return new UnitConverter(context).sps2pxs(sps);
    }

    /* ----------------- Helper Methods ----------------- */
    private static boolean validUnit(final int toUnit) {
        switch (toUnit) {
            case COMPLEX_UNIT_PX:
            case COMPLEX_UNIT_DIP:
            case COMPLEX_UNIT_SP:
            case COMPLEX_UNIT_PT:
            case COMPLEX_UNIT_IN:
            case COMPLEX_UNIT_MM:
                // all valid units
                return true;
            default:
                // masks and others
                return false;
        }
    }
}
