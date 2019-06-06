package com.example.tyil.dtc.API;

import android.app.Activity;

import com.android.volley.Request;
import com.example.tyil.dtc.Helpers.DefaultRequestQueue;
import com.example.tyil.dtc.Helpers.ICommand;
import com.example.tyil.dtc.Helpers.StaticAppConfig;
import com.example.tyil.dtc.Helpers.WebRequest;

import org.json.JSONObject;

import java.util.HashMap;

public class UserAuth {
    public static void handle(
            String email,
            String password,
            final Activity activity,
            final ApiCallback cb
    ) {
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        DefaultRequestQueue.getInstance(activity.getApplicationContext()).addToRequestQueue(new WebRequest(params,
                Request.Method.POST,
                StaticAppConfig.apiBaseUrl + "/auth/login",
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
