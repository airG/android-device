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
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class provides some common soft-keyboard functionality.
 */
@SuppressWarnings({"UnusedDeclaration", "WeakerAccess"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Keyboard {
    /**
     * Get a handle to the {@link InputMethodManager} system service.
     *
     * @param context current context
     * @return an {@link InputMethodManager} associated with the provided context
     */
    public static InputMethodManager getInputMethodManager(final Context context) {
        return Device.getSystemService(context, Context.INPUT_METHOD_SERVICE);
    }

    /**
     * Show the soft keyboard.
     * This method calls the {@link #show(Context, View, int)} method with the {@link InputMethodManager#SHOW_FORCED} flag.
     */
    public static void show(final Context context, final View view) {
        show(context, view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * Show the soft keyboard
     *
     * @param context current context
     * @param view    View to attach soft keyboard to
     * @param flags   {@link InputMethodManager} flags to use
     */
    public static void show(final Context context, final View view, final int flags) {
        getInputMethodManager(context).showSoftInputFromInputMethod(view.getWindowToken(), flags);
    }

    /**
     * Hide the soft keyboard. This method uses the {@link #hide(Context, View, int)} with the
     * {@link InputMethodManager#HIDE_IMPLICIT_ONLY} flag.
     */
    public static void hide(final Context context, final View view) {
        hide(context, view, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * Hide the soft keyboard
     *
     * @param context current context
     * @param view    View to hide keyboard from
     * @param flags   {@link InputMethodManager} flags to use
     */
    public static void hide(final Context context, final View view, final int flags) {
        getInputMethodManager(context).hideSoftInputFromWindow(view.getWindowToken(), flags);
    }

    /**
     * Toggle the Keyboard. This method uses the {@link #toggle(Context, View, int, int)} with the
     * {@link InputMethodManager#SHOW_FORCED} and {@link InputMethodManager#HIDE_IMPLICIT_ONLY} flags.
     */
    public static void toggle(final Context context, final View view) {
        toggle(context, view, InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * Toggle the Keyboard
     *
     * @param context   current context
     * @param view      View on which to toggle soft keyboard
     * @param showFlags {@link InputMethodManager} flags to use for show
     * @param hideFlags {@link InputMethodManager} flags to use for hide
     */
    public static void toggle(final Context context, final View view, final int showFlags, final int hideFlags) {
        getInputMethodManager(context).toggleSoftInputFromWindow(view.getWindowToken(), showFlags, hideFlags);
    }

    /**
     * Show the input method picker
     *
     * @param context current context
     */
    private static void showInputMethodPicker(final Context context) {
        getInputMethodManager(context).showInputMethodPicker();
    }
}
