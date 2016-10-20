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

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.util.Locale;

import lombok.Synchronized;

import static android.os.Build.VERSION_CODES.HONEYCOMB_MR2;
import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;
import static android.os.Build.VERSION_CODES.M;
import static android.os.Build.VERSION_CODES.N;

/**
 * This class provides common answers about the device. This class can be used both as an instance or static methods.
 * Instance methods just relay to static methods so any performance difference between the methods should be negligible.
 */
@SuppressWarnings({"UnusedDeclaration", "WeakerAccess"})
public class Device {
    /**
     * Number of available CPUs
     */
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /**
     * No available version found
     */
    public static final int VERSION_NONE = -1;

    /**
     * Where a size is returned as an array, this is the index of the width
     */
    public static final int SIZE_WIDTH = 0;
    /**
     * Where a size is returned as an array, this is the index of the height
     */
    public static final int SIZE_HEIGHT = 1;

    private static Integer MCC = null;
    private static Integer MNC = null;

    private final Context context;
    public final String hardwareId;

    public Device(final Context c) {
        context = c;
        hardwareId = hardwareId(context);
    }

    /**
     * Get the unique Android ID value. Please do not use this method to obtain an advertising id.
     *
     * @param context to access the device settings
     * @return The android ID
     */
    @SuppressLint("HardwareIds")
    public static String hardwareId(final Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * Obtain a handle to a system service
     *
     * @param context active context
     * @param name    name of the desired service
     * @param <T>     Type of the service (e.g. {@link android.net.ConnectivityManager}, {@link android.view.WindowManager},
     *                etc.).
     * @return The requested system service or null if no such service exists.
     */
    public static <T> T getSystemService(final Context context, final String name) {
        //noinspection unchecked
        return (T) context.getSystemService(name);
    }

    /**
     * See {@link #getSystemService(Context, String)}
     */
    public <T> T getSystemService(final String name) {
        return getSystemService(context, name);
    }

    /**
     * Obtain a handle to a system service. This method requires an API level of at least  23 (Marshmallow)
     *
     * @param context active context
     * @param tClass  service class type
     * @param <T>     Type of the service (e.g. {@link android.net.ConnectivityManager}, {@link android.view.WindowManager},
     *                etc.).
     * @return The matching system service or null if no such service exists.
     */
    @RequiresApi(api = M)
    public static <T> T getSystemService(final Context context, final Class<T> tClass) {
        return context.getSystemService(tClass);
    }

    /**
     * See {@link #getSystemService(Context, Class)}
     */
    @RequiresApi(api = M)
    public <T> T getSystemService(final Class<T> tClass) {
        return getSystemService(context, tClass);
    }

    /**
     * Queries for a specific system feature
     *
     * @param context active context
     * @param feature name of the desired feature (e.g. {@link PackageManager#FEATURE_BLUETOOTH},
     *                {@link PackageManager#FEATURE_CAMERA_FLASH}, etc. ).
     * @return true if the feature is supported and false otherwise
     */
    public static boolean hasSystemFeature(final Context context, final String feature) {
        return context.getPackageManager().hasSystemFeature(feature);
    }

    /**
     * See {@link #hasSystemFeature(Context, String)}
     */
    public boolean hasSystemFeature(final String feature) {
        return hasSystemFeature(context, feature);
    }

    /**
     * Queries for a specific version of a system feature. This method is only available on Android Nougat (24) and above.
     *
     * @param context active context
     * @param feature name of the desired feature (e.g. {@link PackageManager#FEATURE_BLUETOOTH},
     *                {@link PackageManager#FEATURE_CAMERA_FLASH}, etc. ).
     * @param version required version
     * @return true if the feature is supported and the available version is greater than or equal to requested version;
     * false otherwise.
     */
    @RequiresApi(api = N)
    public static boolean hasSystemFeature(final Context context, final String feature, final int version) {
        return context.getPackageManager().hasSystemFeature(feature, version);
    }

    /**
     * See {@link #hasSystemFeature(Context, String, int)}
     */
    @RequiresApi(api = N)
    public boolean hasSystemFeature(final String feature, final int version) {
        return hasSystemFeature(context, feature, version);
    }

    /**
     * Query the available version of the specified feature.
     * Note: Feature versions are not available prior to api 24 (Nougat). This method returns {@link #VERSION_NONE} under
     * API levels lower than 24.
     *
     * @param context active context
     * @param feature feature name to query
     * @return Available feature version, if the feature is supported; {@link #VERSION_NONE} otherwise.
     */
    @TargetApi(N)
    public static int getSystemFeatureVersion(final Context context, final String feature) {
        if (ApiLevel.below(N)) // versions are not supported here
            return VERSION_NONE;

        final FeatureInfo[] features = context.getPackageManager().getSystemAvailableFeatures();

        for (final FeatureInfo fi : features)
            if (fi.name.equals(feature))
                return fi.version;

        return VERSION_NONE;
    }

    /**
     * See {@link #getSystemFeatureVersion(Context, String)}
     */
    @TargetApi(N)
    public int getSystemFeatureVersion(final String feature) {
        return getSystemFeatureVersion(context, feature);
    }

    /**
     * Does the device support <i>all</i> of the specified features
     *
     * @param context  active context
     * @param features list of features to query
     * @return true if and only if <i>all</i> of the specified features are supported
     * @throws IllegalArgumentException if the <code>features</code> parameter is <code>null</code> or empty
     */
    public static boolean hasAllSystemFeatures(final Context context, final String... features) {
        if (null == features || features.length == 0)
            throw new IllegalArgumentException("No features specified");

        final PackageManager pm = context.getPackageManager();

        for (final String feature : features)
            if (!pm.hasSystemFeature(feature))
                return false;

        return true;
    }

    /**
     * See {@link #hasAllSystemFeatures(Context, String...)}
     */
    public boolean hasAllSystemFeatures(final String... features) {
        return hasAllSystemFeatures(context, features);
    }

    /**
     * Does the device support <i>any</i> of the specified features
     *
     * @param context  active context
     * @param features list of features to query
     * @return true if and only if <i>any</i> of the specified features are supported (i.e. at least one of the queried
     * features is supported)
     * @throws IllegalArgumentException if the <code>features</code> parameter is <code>null</code> or empty
     */
    public static boolean hasAnySystemFeatures(final Context context, final String... features) {
        if (null == features || features.length == 0)
            throw new IllegalArgumentException("No features specified");

        final PackageManager pm = context.getPackageManager();

        for (final String feature : features)
            if (pm.hasSystemFeature(feature))
                return true;

        return true;
    }

    /**
     * See {@link #hasAnySystemFeatures(Context, String...)}
     */
    public boolean hasAnySystemFeatures(final String... features) {
        return hasAnySystemFeatures(context, features);
    }

    /**
     * Gets the current device locale
     *
     * @param context Context through which to access app resources
     * @return The current locale for the device
     */
    @TargetApi(N)
    public static Locale getLocale(final Context context) {
        //noinspection deprecation
        return ApiLevel.atLeast(N)
                ? context.getResources().getConfiguration().getLocales().get(0)
                : context.getResources().getConfiguration().locale;
    }

    /**
     * See {@link #getLocale(Context)}
     */
    public Locale getLocale() {
        return getLocale(context);
    }

    /**
     * Current layout direction of the device. This method returns {@link View#LAYOUT_DIRECTION_LTR}
     * (<code>0</code>) if the runtime SDK version is below JELLY_BEAN_MR1 (<code>android-17</code>)
     *
     * @param context context through which to access configuration
     * @return {@link View#LAYOUT_DIRECTION_LTR} if the layout direction is left-to-right
     * (or api &lt; android-17), {@link View#LAYOUT_DIRECTION_RTL} otherwise.
     */
    @TargetApi(JELLY_BEAN_MR1)
    public static int getLayoutDirection(final Context context) {
        return ApiLevel.atLeast(JELLY_BEAN_MR1)
                ? context.getResources().getConfiguration().getLayoutDirection()
                : 0;
    }

    /**
     * see {@link #getLayoutDirection(Context)}
     */
    public int getLayoutDirection() {
        return getLayoutDirection(context);
    }

    /**
     * Get the screen orientation
     *
     * @param context context through which to access configuration
     * @return current screen orientation, either {@link android.content.res.Configuration#ORIENTATION_LANDSCAPE} or {@link android.content.res.Configuration#ORIENTATION_PORTRAIT}
     */
    public static int getScreenOrientation(final Context context) {
        return context.getResources().getConfiguration().orientation;
    }

    /**
     * See {@link #getScreenOrientation(Context)}
     */
    public int getScreenOrientation() {
        return getScreenOrientation(context);
    }

    /**
     * Get the {@link DisplayMetrics} for the default display
     *
     * @param context Context through which to access configuration
     * @return {@link DisplayMetrics} for the default display
     */
    public static DisplayMetrics getDefaultDisplayMetrics(final Context context) {
        final WindowManager wm = getSystemService(context, Context.WINDOW_SERVICE);
        final DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * See {@link #getDefaultDisplayMetrics(Context)}
     */
    public DisplayMetrics getDefaultDisplayMetrics() {
        return getDefaultDisplayMetrics(context);
    }

    /**
     * Gets the screen size in pixels
     *
     * @param context Context through which to access configuration
     * @return the screen size in pixels as an array of <code>int</code> with the width at index {@link #SIZE_WIDTH} and height at {@link #SIZE_HEIGHT}
     */
    public static int[] getScreenSize(final Context context) {
        final DisplayMetrics dm = getDefaultDisplayMetrics(context);
        return new int[]{dm.widthPixels, dm.heightPixels};
    }

    /**
     * See {@link #getScreenSize(Context)}
     */
    public int[] getScreenSize() {
        return getScreenSize(context);
    }

    /**
     * Get the screen size in <i>device independent pixels</i> (dp)
     *
     * @param context Context through which to access configuration
     * @return the screen size in dp as an array of <code>int</code> with the width at index {@link #SIZE_WIDTH} and height at {@link #SIZE_HEIGHT}
     */
    public static int[] getScreenSizeDP(final Context context) {
        return ApiLevel.atLeast(HONEYCOMB_MR2)
                ? getScreenSizeDP_HoneycombMR2(context)
                : getScreenSizeDPLegacy(context);
    }


    /**
     * Gets the IMSI MCC (mobile country code) for the current device. Since this value cannot
     * change without a reboot, it is cached so there's no performance penalty for calling this
     * method repeatedly.
     *
     * @param context Context through which to access configuration
     * @return mcc
     */
    @Synchronized
    public static int getMobileCountryCode(final Context context) {
        if (null == MCC)
            MCC = context.getResources().getConfiguration().mcc;

        return MCC;
    }

    /**
     * See {@link #getMobileCountryCode(Context)}
     */
    @Synchronized
    public int getMobileCountryCode() {
        return getMobileCountryCode(context);
    }

    /**
     * Gets the IMSI MNC (mobile network code) for the current device. Since this value cannot
     * change without a reboot, it is cached so there's no performance penalty for calling this
     * method repeatedly.
     *
     * @param context Context through which to access configuration
     * @return mnc
     */
    @Synchronized
    public static int getMobileNetworkCode(final Context context) {
        if (null == MNC)
            MNC = context.getResources().getConfiguration().mnc;

        return MNC;
    }

    /**
     * See {@link #getMobileNetworkCode(Context)}
     */
    @Synchronized
    public int getMobileNetworkCode() {
        return getMobileNetworkCode(context);
    }

    /* ----------------- Private helpers ----------------- */
    private static int[] getScreenSizeDPLegacy(final Context context) {
        final UnitConverter converter = new UnitConverter(context);
        return new int[]{
                (int) converter.px2dp(converter.displayMetrics.widthPixels),
                (int) converter.px2dp(converter.displayMetrics.heightPixels)
        };
    }

    @TargetApi(HONEYCOMB_MR2)
    private static int[] getScreenSizeDP_HoneycombMR2(final Context context) {
        final Configuration config = context.getResources().getConfiguration();
        return new int[]{config.screenWidthDp, config.screenHeightDp};
    }
}
