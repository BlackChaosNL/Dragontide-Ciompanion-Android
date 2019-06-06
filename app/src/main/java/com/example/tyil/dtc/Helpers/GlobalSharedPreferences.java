package com.example.tyil.dtc.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Runtime configuration for the application. Functions mainly as a plain ol' key-value store. It is
 * built upon Android's SharedPreferences.
 */
public class GlobalSharedPreferences {
    /**
     * The name of the preference file to use.
     */
    private final static String preferenceFile = "dragontide-app-preferences";

    /**
     * Set a string value in the configuration.
     *
     * @param ctx   The context. This is needed for the underlying SharedPreferences to work correctly.
     * @param key   The key to set.
     * @param value The value to set the key to.
     */
    public static void setString(Context ctx, String key, String value) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE).edit();

        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Get a string value from the configuration.
     *
     * @param ctx      The context. This is needed for the underlying SharedPreferences to work correctly.
     * @param key      The key to retrieve the value of.
     * @param fallback The value to use if the given key has no set value available.
     * @return The value of the configuration key.
     */
    public static String getString(Context ctx, String key, String fallback) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE);

        return sharedPreferences.getString(key, fallback);
    }

    /**
     * Get a string value from the configuration. If the given key does not exist, an empty String
     * will be returned.
     *
     * @param ctx      The context. This is needed for the underlying SharedPreferences to work correctly.
     * @param key      The key to retrieve the value of.
     * @return The value of the configuration key, or "" if the key is not set.
     */
    public static String getString(Context ctx, String key) {
        return getString(ctx, key, "");
    }
}
