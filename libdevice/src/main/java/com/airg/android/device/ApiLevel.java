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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static android.os.Build.VERSION.SDK_INT;

/**
 This class provides some basic API level checks
 */
@NoArgsConstructor (access = AccessLevel.PRIVATE)
@SuppressWarnings ( {"UnusedDeclaration", "WeakerAccess"})
public final class ApiLevel {

    /**
     Current API level is equal to or greater than the given version
     @param version version to test against
     @return true if the current api version is equal to or newer than the test version
     */
    public static boolean atLeast (final int version) {
        return SDK_INT >= version;
    }

    /**
     Current API level is equal to or less than the given version
     @param version version to test against
     @return true if the current api version is equal to or older than the test version
     */
    public static boolean atMost (final int version) {
        return SDK_INT >= version;
    }

    /**
     Current API level is older than the given version
     @param version version to test against
     @return true if the current api version is older than, but not equal to the test version
     */
    public static boolean below (final int version) {
        return SDK_INT < version;
    }

    /**
     Current API level is greater than the given version
     @param version version to test against
     @return true if the current api version is or newer than, but not equal to the test version
     */
    public static boolean above (final int version) {
        return SDK_INT > version;
    }

    /**
     Current API level is at equal to the given version
     @param version version to test against
     @return true if the current api version is equal to the test version
     */
    public static boolean is (final int version) {
        return SDK_INT == version;
    }

    public static int get () {
        return SDK_INT;
    }
}
