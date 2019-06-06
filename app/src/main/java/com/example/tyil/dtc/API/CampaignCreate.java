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

public class CampaignCreate {
    public static void handle(
            String name,
            String description,
            String password,
            final Activity activity,
            final ApiCallback cb
    ) {
        String apiToken = GlobalSharedPreferences.getString(
                activity.getApplicationContext(),
                "apiToken"
        );

        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("description", description);
        params.put("password", password);

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + apiToken);

        DefaultRequestQueue.getInstance(activity.getApplicationContext()).addToRequestQueue(new WebRequest(
                params,
                Request.Method.POST,
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
