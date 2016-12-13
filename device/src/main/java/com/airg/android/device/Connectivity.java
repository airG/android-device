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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import lombok.Getter;

/**
 * This class provides common network connectivity tasks. You can use this class either as an instance, or just the
 * provided static methods. Static methods are recommended for single or infrequent queries. For multiple or frequent
 * querying of the network state, an instance is recommended.
 */

@SuppressWarnings({"UnusedDeclaration", "WeakerAccess"})
public class Connectivity {

    @Getter
    private final ConnectivityManager connectivityManager;

    /**
     * Constructor
     *
     * @param context active context
     */
    public Connectivity(final Context context) {
        connectivityManager = getConnectivityManager(context);
    }

    /**
     * Get a handle to the {@link ConnectivityManager} system service.
     *
     * @param context active context
     * @return a handle to the ConnectivityManager
     */
    public static ConnectivityManager getConnectivityManager(final Context context) {
        return Device.getSystemService(context, Context.CONNECTIVITY_SERVICE);
    }

    /**
     * Get the details of the currently active network
     *
     * @param context active context
     * @return current active {@link NetworkInfo} or null if no active connection
     */
    public static NetworkInfo activeNetwork(final Context context) {
        return getConnectivityManager(context).getActiveNetworkInfo();
    }

    /**
     * Get the details of the currently active network
     *
     * @return current active {@link NetworkInfo} or null if no active connection
     */
    public NetworkInfo activeNetwork() {
        return connectivityManager.getActiveNetworkInfo();
    }

    /**
     * Subscribe to network change events. Be sure to call {@link #unsubscribe(Context, BroadcastReceiver)} when done.
     *
     * @param context a {@link Context} on which to register the receiver
     * @param receiver receiver to subscribe to network change events
     */
    public void subscribe(@NonNull final Context context, @NonNull final BroadcastReceiver receiver) {
        context.registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    /**
     *
     * @param context a {@link Context} on which to register the receiver
     * @param receiver receiver to unsubscribe from network change events
     */
    public void unsubscribe(@NonNull final Context context, @NonNull final BroadcastReceiver receiver) {
        context.unregisterReceiver(receiver);
    }

    /**
     * Queries whether the device is currently online
     *
     * @param context active context
     * @return true if the current active network is connected and false if no active network or the active network is
     * not connected
     */
    public static boolean online(final Context context) {
        return online(activeNetwork(context));
    }

    /**
     * Queries whether the device is currently online
     *
     * @return true if the current active network is connected and false if no active network or the active network is
     * not connected
     */
    public boolean online() {
        return online(activeNetwork());
    }

    /**
     * Queries whether the device is currently using Wifi connectivity
     *
     * @param context active context
     * @return true if the active network is wifi, false if no active network or not wifi.
     */
    public static boolean isWifi(final Context context) {
        return isWifi(activeNetwork(context));
    }

    /**
     * Queries whether the device is currently using Wifi connectivity
     *
     * @return true if the active network is wifi, false if no active network or not wifi.
     */
    public boolean isWifi() {
        return isWifi(activeNetwork());
    }

    /**
     * Queries whether the device is currently using mobile connectivity
     *
     * @param context active context
     * @return true if the active network is mobile, false if no active network or not mobile.
     */
    public static boolean isMobile(final Context context) {
        return isMobile(activeNetwork(context));
    }

    /**
     * Queries whether the device is currently using mobile connectivity
     *
     * @return true if the active network is mobile, false if no active network or not mobile.
     */
    public boolean isMobile() {
        return isMobile(activeNetwork());
    }

    // --------- private helpers

    private static boolean online(final NetworkInfo ni) {
        return null != ni && ni.isConnected();
    }

    private static boolean isWifi(final NetworkInfo ni) {
        return is(ni, ConnectivityManager.TYPE_WIFI);
    }

    private static boolean isMobile(final NetworkInfo ni) {
        return is(ni, ConnectivityManager.TYPE_MOBILE);
    }

    private static boolean is(final NetworkInfo ni, final int type) {
        return null != ni && ni.getType() == type;
    }
}
