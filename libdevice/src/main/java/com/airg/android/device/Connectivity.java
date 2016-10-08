package com.airg.android.device;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import lombok.Getter;

/**
 This class provides common network connectivity tasks. You can use this class either as an instance, or just the
 provided static methods. Static methods are recommended for single or infrequent queries. For multiple or frequent
 querying of the network state, an instance is recommended.
 */

@SuppressWarnings ( {"UnusedDeclaration", "WeakerAccess"})
public class Connectivity {

    @Getter private final ConnectivityManager connectivityManager;

    /**
     Constructor

     @param context
     active context
     */
    public Connectivity (final Context context) {
        connectivityManager = getConnectivityManager (context);
    }

    /**
     Get a handle to the {@link ConnectivityManager} system service.

     @param context
     active context

     @return a handle to the ConnectivityManager
     */
    public static ConnectivityManager getConnectivityManager (final Context context) {
        return Device.getSystemService (context, Context.CONNECTIVITY_SERVICE);
    }

    /**
     Get the details of the currently active network

     @param context
     active context

     @return current active {@link NetworkInfo} or null if no active connection
     */
    public static NetworkInfo activeNetwork (final Context context) {
        return getConnectivityManager (context).getActiveNetworkInfo ();
    }

    /**
     Get the details of the currently active network

     @return current active {@link NetworkInfo} or null if no active connection
     */
    public NetworkInfo activeNetwork () {
        return connectivityManager.getActiveNetworkInfo ();
    }

    /**
     Queries whether the device is currently online

     @param context
     active context

     @return true if the current active network is connected and false if no active network or the active network is
     not connected
     */
    public static boolean online (final Context context) {
        return online (activeNetwork (context));
    }

    /**
     Queries whether the device is currently online

     @return true if the current active network is connected and false if no active network or the active network is
     not connected
     */
    public boolean online () {
        return online (activeNetwork ());
    }

    /**
     Queries whether the device is currently using Wifi connectivity

     @param context
     active context

     @return true if the active network is wifi, false if no active network or not wifi.
     */
    public static boolean isWifi (final Context context) {
        return isWifi (activeNetwork (context));
    }

    /**
     Queries whether the device is currently using Wifi connectivity

     @return true if the active network is wifi, false if no active network or not wifi.
     */
    public boolean isWifi () {
        return isWifi (activeNetwork ());
    }

    /**
     Queries whether the device is currently using mobile connectivity

     @param context
     active context

     @return true if the active network is mobile, false if no active network or not mobile.
     */
    public static boolean isMobile (final Context context) {
        return isMobile (activeNetwork (context));
    }

    /**
     Queries whether the device is currently using mobile connectivity

     @return true if the active network is mobile, false if no active network or not mobile.
     */
    public boolean isMobile () {
        return isMobile (activeNetwork ());
    }

    // --------- private helpers

    private static boolean online (final NetworkInfo ni) {
        return null != ni && ni.isConnected ();
    }

    private static boolean isWifi (final NetworkInfo ni) {
        return is (ni, ConnectivityManager.TYPE_WIFI);
    }

    private static boolean isMobile (final NetworkInfo ni) {
        return is (ni, ConnectivityManager.TYPE_MOBILE);
    }

    private static boolean is (final NetworkInfo ni, final int type) {
        return null != ni && ni.getType () == type;
    }
}
