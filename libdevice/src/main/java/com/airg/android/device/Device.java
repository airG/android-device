package com.airg.android.device;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

import static android.os.Build.VERSION_CODES.M;
import static android.os.Build.VERSION_CODES.N;

/**
 This class provides common answers about the device. This class can be used both as an instance or static methods.
 Instance methods just relay to static methods so any performance difference between the methods should be negligible.
 */
@SuppressWarnings ( {"UnusedDeclaration", "WeakerAccess"})
public class Device {
    /**
     Number of available CPUs
     */
    public static final int CPU_COUNT = Runtime.getRuntime ().availableProcessors ();

    /**
     No available version found
     */
    public static final int VERSION_NONE = -1;

    private final Context context;
    public final  String  hardwareId;

    public Device (final Context c) {
        context = c;
        hardwareId = hardwareId (context);
    }

    /**
     Get the unique Android ID value. Please do not use this method to obtain an advertising id.

     @param context
     to access the device settings

     @return The android ID
     */
    @SuppressLint ("HardwareIds") public static String hardwareId (final Context context) {
        return Settings.Secure.getString (context.getContentResolver (), Settings.Secure.ANDROID_ID);
    }

    /**
     Obtain a handle to a system service

     @param context
     active context
     @param name
     name of the desired service
     @param <T>
     Type of the service (e.g. {@link android.net.ConnectivityManager}, {@link android.view.WindowManager},
     etc.).

     @return The requested system service or null if no such service exists.
     */
    public static <T> T getSystemService (final Context context, final String name) {
        //noinspection unchecked
        return (T) context.getSystemService (name);
    }

    /**
     Obtain a handle to a system service

     @param name
     name of the desired service
     @param <T>
     Type of the service (e.g. {@link android.net.ConnectivityManager}, {@link android.view.WindowManager},
     etc.).

     @return The requested system service or null if no such service exists.
     */
    public <T> T getSystemService (final String name) {
        return getSystemService (context, name);
    }

    /**
     Obtain a handle to a system service. This method requires an API level of at least  23 (Marshmallow)

     @param context
     active context
     @param tClass
     service class type
     @param <T>
     Type of the service (e.g. {@link android.net.ConnectivityManager}, {@link android.view.WindowManager},
     etc.).

     @return The matching system service or null if no such service exists.
     */
    @RequiresApi (api = M)
    public static <T> T getSystemService (final Context context, final Class<T> tClass) {
        return context.getSystemService (tClass);
    }

    /**
     Obtain a handle to a system service. This method requires an API level of at least  23 (Marshmallow)

     @param tClass
     service class type
     @param <T>
     Type of the service (e.g. {@link android.net.ConnectivityManager}, {@link android.view.WindowManager},
     etc.).

     @return The matching system service or null if no such service exists.
     */
    @RequiresApi (api = M)
    public <T> T getSystemService (final Class<T> tClass) {
        return getSystemService (context, tClass);
    }

    /**
     Queries for a specific system feature

     @param context
     active context
     @param feature
     name of the desired feature (e.g. {@link PackageManager#FEATURE_BLUETOOTH},
     {@link PackageManager#FEATURE_CAMERA_FLASH}, etc. ).

     @return true if the feature is supported and false otherwise
     */
    public static boolean hasSystemFeature (final Context context, final String feature) {
        return context.getPackageManager ().hasSystemFeature (feature);
    }

    /**
     Queries for a specific system feature

     @param feature
     name of the desired feature (e.g. {@link PackageManager#FEATURE_BLUETOOTH},
     {@link PackageManager#FEATURE_CAMERA_FLASH}, etc. ).

     @return true if the feature is supported and false otherwise
     */
    public boolean hasSystemFeature (final String feature) {
        return hasSystemFeature (context, feature);
    }

    /**
     Queries for a specific version of a system feature. This method is only available on Android Nougat (24) and above.

     @param context
     active context
     @param feature
     name of the desired feature (e.g. {@link PackageManager#FEATURE_BLUETOOTH},
     {@link PackageManager#FEATURE_CAMERA_FLASH}, etc. ).
     @param version
     required version

     @return true if the feature is supported and the available version is greater than or equal to requested version;
     false otherwise.
     */
    @RequiresApi (api = N)
    public static boolean hasSystemFeature (final Context context, final String feature, final int version) {
        return context.getPackageManager ().hasSystemFeature (feature, version);
    }

    /**
     Queries for a specific version of a system feature. This method is only available on Android Nougat (24) and above.

     @param feature
     name of the desired feature (e.g. {@link PackageManager#FEATURE_BLUETOOTH},
     {@link PackageManager#FEATURE_CAMERA_FLASH}, etc. ).
     @param version
     required version

     @return true if the feature is supported and the available version is greater than or equal to requested version;
     false otherwise.
     */
    @RequiresApi (api = N)
    public boolean hasSystemFeature (final String feature, final int version) {
        return hasSystemFeature (context, feature, version);
    }

    /**
     Query the available version of the specified feature.
     Note: Feature versions are not available prior to api 24 (Nougat). This method returns {@link #VERSION_NONE} under
     API levels lower than 24.

     @param context
     active context
     @param feature
     feature name to query

     @return Available feature version, if the feature is supported; {@link #VERSION_NONE} otherwise.
     */
    @TargetApi (N)
    public static int getSystemFeatureVersion (final Context context, final String feature) {
        if (ApiLevel.below (N)) // versions are not supported here
            return VERSION_NONE;

        final FeatureInfo[] features = context.getPackageManager ().getSystemAvailableFeatures ();

        for (final FeatureInfo fi : features)
            if (fi.name.equals (feature))
                return fi.version;

        return VERSION_NONE;
    }

    /**
     Query the available version of the specified feature.
     Note: Feature versions are not available prior to api 24 (Nougat). This method returns {@link #VERSION_NONE} under
     API levels lower than 24.

     @param feature
     feature name to query

     @return Available feature version, if the feature is supported; {@link #VERSION_NONE} otherwise.
     */
    @TargetApi (N)
    public int getSystemFeatureVersion (final String feature) {
        return getSystemFeatureVersion (context, feature);
    }

    /**
     Does the device support <i>all</i> of the specified features

     @param context
     active context
     @param features
     list of features to query

     @return true if and only if <i>all</i> of the specified features are supported

     @throws IllegalArgumentException
     if the <code>features</code> parameter is <code>null</code> or empty
     */
    public static boolean hasAllSystemFeatures (final Context context, final String... features) {
        if (null == features || features.length == 0)
            throw new IllegalArgumentException ("No features specified");

        final PackageManager pm = context.getPackageManager ();

        for (final String feature : features)
            if (!pm.hasSystemFeature (feature))
                return false;

        return true;
    }

    /**
     Does the device support <i>all</i> of the specified features

     @param features
     list of features to query

     @return true if and only if <i>all</i> of the specified features are supported

     @throws IllegalArgumentException
     if the <code>features</code> parameter is <code>null</code> or empty
     */
    public boolean hasAllSystemFeatures (final String... features) {
        return hasAllSystemFeatures (context, features);
    }

    /**
     Does the device support <i>any</i> of the specified features

     @param context
     active context
     @param features
     list of features to query

     @return true if and only if <i>any</i> of the specified features are supported (i.e. at least one of the queried
     features is supported)

     @throws IllegalArgumentException
     if the <code>features</code> parameter is <code>null</code> or empty
     */
    public static boolean hasAnySystemFeatures (final Context context, final String... features) {
        if (null == features || features.length == 0)
            throw new IllegalArgumentException ("No features specified");

        final PackageManager pm = context.getPackageManager ();

        for (final String feature : features)
            if (pm.hasSystemFeature (feature))
                return true;

        return true;
    }

    /**
     Does the device support <i>any</i> of the specified features

     @param features
     list of features to query

     @return true if and only if <i>any</i> of the specified features are supported (i.e. at least one of the queried
     features is supported)

     @throws IllegalArgumentException
     if the <code>features</code> parameter is <code>null</code> or empty
     */
    public boolean hasAnySystemFeatures (final String... features) {
        return hasAnySystemFeatures (context, features);
    }
}
