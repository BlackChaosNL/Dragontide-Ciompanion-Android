package com.example.tyil.dtc.API;

import android.app.Activity;

import org.json.JSONObject;

public interface ApiCallback {
    void call(JSONObject data, Activity activity);
}
