package com.example.tyil.dtc.API;

import android.app.Activity;

import com.android.volley.Request;
import com.example.tyil.dtc.Helpers.DefaultRequestQueue;
import com.example.tyil.dtc.Helpers.GlobalSharedPreferences;
import com.example.tyil.dtc.Helpers.ICommand;
import com.example.tyil.dtc.Helpers.StaticAppConfig;
import com.example.tyil.dtc.Helpers.WebRequest;

import org.json.JSONObject;

import java.util.HashMap;

public class CampaignIndex {
    public static void handle(
            final Activity activity,
            final ApiCallback cb
    ) {
        String apiToken = GlobalSharedPreferences.getString(
                activity.getApplicationContext(),
                "apiToken"
        );

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + apiToken);

        DefaultRequestQueue.getInstance(activity.getApplicationContext()).addToRequestQueue(new WebRequest(
                new HashMap<String, String>(),
                Request.Method.GET,
                StaticAppConfig.apiBaseUrl + "/campaign",
                headers,
                new ICommand() {
                    @Override
                    public void Success(Object object) {
                        cb.call((JSONObject) object, activity);
                    }
                }).getRequest()
        );
    }
}
