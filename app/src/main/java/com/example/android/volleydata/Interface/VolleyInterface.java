package com.example.android.volleydata.Interface;


import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by P-CODE on 1/31/2016.
 */
public interface VolleyInterface {
    void onPrepare();
    void onSucces(JSONObject jsonObject);
    void onFailed(VolleyError errorListener);
}
