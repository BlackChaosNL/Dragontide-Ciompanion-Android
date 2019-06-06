package com.example.tyil.dtc.Helpers;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class DefaultRequestQueue {
    private static DefaultRequestQueue mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private DefaultRequestQueue(Context ctx){
        mCtx = ctx;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized DefaultRequestQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DefaultRequestQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
