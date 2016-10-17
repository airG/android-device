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

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;
import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static com.airg.android.device.Device.hasAnySystemFeatures;
import static com.airg.android.device.Device.hasSystemFeature;

/**
 This class provides some common camera-related queries.
 */
@SuppressWarnings ( {"UnusedDeclaration", "WeakerAccess"})
@NoArgsConstructor (access = AccessLevel.PRIVATE)
public class Cameras {
    /**
     Does the current device has any camera(s)?

     @param context
     active context

     @return true if the device has a camera (front or rear) and false otherwise
     */
    @TargetApi (JELLY_BEAN_MR1)
    public static boolean hasAny (final Context context) {
        return ApiLevel.atLeast (JELLY_BEAN_MR1)
               ? hasSystemFeature (context, PackageManager.FEATURE_CAMERA_ANY)
               : hasAnySystemFeatures (context, PackageManager.FEATURE_CAMERA, PackageManager.FEATURE_CAMERA_FRONT);
    }

    /**
     Does the current device have a front facing (aka 'Selfie') camera?

     @param context
     active context

     @return true if the device has a front facing camera and false otherwise
     */
    public static boolean hasFront (final Context context) {
        return hasSystemFeature (context, PackageManager.FEATURE_CAMERA_FRONT);
    }

    /**
     Does this device's camera have a flash
     @param context active context
     @return true if the camera has a flash and false otherwise
     */
    public static boolean hasFlash (final Context context) {
        return hasSystemFeature (context, PackageManager.FEATURE_CAMERA_FLASH);
    }

    /**
     Does the camera have auto-focus?
     @param context active context
     @return true if the camera is able to auto-focus and false otherwise.
     */
    public static boolean hasAutoFocus (final Context context) {
        return hasSystemFeature (context, PackageManager.FEATURE_CAMERA_AUTOFOCUS);
    }

    /**
     Does this device support raw (unprocessed sensor output) photos
     @param context active context
     @return true if the device supports raw formats, false otherwise.
     */
    @TargetApi (LOLLIPOP)
    public static boolean supportsRaw (final Context context) {
        return ApiLevel.atLeast (LOLLIPOP) &&
               hasSystemFeature (context, PackageManager.FEATURE_CAMERA_CAPABILITY_RAW);
    }
}
