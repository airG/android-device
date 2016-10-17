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

import android.text.TextUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by mahramf.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE) // no instances
@SuppressWarnings({"UnusedDeclaration", "WeakerAccess"})
public final class Assert {

    /**
     * Asserts that the provided instance is not <code>null</code>
     *
     * @param o instance to check
     * @throws NullPointerException if provided instance is <code>null</code>
     */
    public static void assertNotNull(final Object o) {
        if (null != o) return;

        throw new NullPointerException("NotNull assertion failed");
    }

    /**
     * Asserts that the provided instance is <code>null</code>
     *
     * @param o instance to check
     * @throws IllegalStateException if the instance is not <code>null</code>
     */
    public static void assertIsNull(final Object o) {
        if (null == o) return;

        throw new IllegalStateException("IsNull assertion failed");
    }

    /**
     * Asserts that the provided {@link CharSequence} is empty or <code>null</code>
     *
     * @param cs string to check
     * @throws IllegalStateException if provided string is not empty
     */
    public static void assertIsEmpty(final CharSequence cs) {
        if (TextUtils.isEmpty(cs)) return;
        throw new IllegalStateException("IsEmpty assertion failed");
    }

    /**
     * Asserts that the provided {@link CharSequence} is neither empty or <code>null</code>
     *
     * @param cs string to check
     * @throws IllegalStateException if provided string is empty or <code>null</code>
     */
    public static void assertNotEmpty(final CharSequence cs) {
        if (!TextUtils.isEmpty(cs)) return;

        throw new IllegalStateException("NotEmpty assertion failed");
    }

    /**
     * Asserts that the provided instances are equal (reference or value). This method relies on
     * the object's implementation of {@link Object#equals(Object)} to work.
     *
     * @param left  first object
     * @param right second object
     * @throws IllegalStateException if the instances are not equal
     */
    public static void assertEqual(final Object left, final Object right) {
        // either reference equal or value equal
        if (left == right ||
                (null != left && null != right && left.equals(right)))
            return;

        throw new IllegalStateException("Equal assertion failed");
    }

    /**
     * Asserts that the provided instances are not equal (reference and value). This method relies
     * on the object's implementation of {@link Object#equals(Object)} to work.
     *
     * @param left  first object
     * @param right second object
     * @throws IllegalStateException if the instances are equal
     */
    public static void assertNotEqual(final Object left, final Object right) {
        if (left != right &&
                (null == left || null == right || !left.equals(right)))
            return;

        throw new IllegalStateException("NotEqual assertion failed");
    }

    /**
     * Asserts that the provided instances are reference equal (same instance)
     *
     * @param left  first object
     * @param right second object
     * @throws IllegalStateException if the instances are not the same instance
     */
    public static void assertReferenceEqual(final Object left, final Object right) {
        if (left == right) return;
        throw new IllegalStateException("ReferenceEqual assertion failed");
    }

    /**
     * Asserts that the provided instances are not reference equal (different instances).
     * The provided instances can be equal in value without failing this assertion.
     *
     * @param left  first object
     * @param right second object
     * @throws IllegalStateException if the instances are the same instance
     */
    public static void assertReferenceNotEqual(final Object left, final Object right) {
        if (left != right) return;
        throw new IllegalStateException("ReferenceNotEqual assertion failed");
    }
}
