package com.example.tyil.dtc.Helpers;

/**
 * This class deals with the static app configuration. If you want to store runtime configuration,
 * use the GlobalSharedPreferences helper class instead.
 */
public class StaticAppConfig {
    /**
     * The base URL for the API.
     */
    public static String apiBaseUrl = "https://dragontide.herokuapp.com";

    /**
     * Global permission flag.
     */
    public static int PERMISSION_REQUEST_CODE = 1;
}
