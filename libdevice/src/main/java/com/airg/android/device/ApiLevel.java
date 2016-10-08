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
