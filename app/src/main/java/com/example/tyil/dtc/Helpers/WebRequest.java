package com.example.tyil.dtc.Helpers;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebRequest {
    private JsonObjectRequest jobject;
    private HashMap<String, String> headers;
    private ICommand command;
    public WebRequest(HashMap<String, String> params,
                      int method,
                      String url,
                      HashMap<String, String> headers,
                      ICommand command){
        Log.i("WebRequest", url);
        this.command = command;
        this.headers = headers;
        this.jobject = new JsonObjectRequest(
                method,
                url,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        getCommand().Success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.w(error.getClass()+"", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getRequestHeaders();
            }
        };
    }

    private ICommand getCommand() {
        return this.command;
    }

    private Map<String, String> getRequestHeaders() {
        return this.headers;
    }

    public JsonObjectRequest getRequest(){
        return jobject;
    }

}
