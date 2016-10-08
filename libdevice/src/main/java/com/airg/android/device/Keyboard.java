package com.airg.android.device;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
This class provides some common soft-keyboard functionality.
 */
@SuppressWarnings ( {"UnusedDeclaration", "WeakerAccess"})
@NoArgsConstructor (access = AccessLevel.PRIVATE)
public class Keyboard {
    /**
     Get a handle to the {@link InputMethodManager} system service.
     @param context current context
     @return an {@link InputMethodManager} associated with the provided context
     */
    public static InputMethodManager getInputMethodManager (final Context context) {
        return Device.getSystemService (context, Context.INPUT_METHOD_SERVICE);
    }

    /**
     Show the soft keyboard
     @param context current context
     @param view View to attach soft keyboard to
     */
    public static void show (final Context context, final View view) {
        getInputMethodManager (context).showSoftInputFromInputMethod (view.getWindowToken (), 0);
    }

    /**
     Hide the soft keyboard
     @param context current context
     @param view View to hide keyboard from
     */
    public static void hide (final Context context, final View view) {
        getInputMethodManager (context).hideSoftInputFromWindow (view.getWindowToken (), 0);
    }

    /**
     Toggle the Keyboard
     @param context current context
     @param view View on which to toggle soft keyboard
     */
    public static void toggle (final Context context, final View view) {
        getInputMethodManager (context).toggleSoftInputFromWindow (view.getWindowToken (), 0, 0);
    }

    /**
     Show the input method picker
     @param context current context
     */
    private static void showInputMethodPicker (final Context context) {
        getInputMethodManager (context).showInputMethodPicker ();
    }
}
