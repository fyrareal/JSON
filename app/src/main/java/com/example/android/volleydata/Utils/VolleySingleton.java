package com.example.android.volleydata.Utils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.android.volleydata.Application.MyApplication;

/**
 * Created by P-CODE on 1/31/2016.
 */
public class VolleySingleton {
    private static VolleySingleton sInstance = null;
    private RequestQueue mRequestQueue;

    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getContext());
    }

    public static VolleySingleton getInstance() {
        if(sInstance == null){
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
