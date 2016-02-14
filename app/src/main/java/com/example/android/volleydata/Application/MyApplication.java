package com.example.android.volleydata.Application;

import android.app.Application;
import android.content.Context;

/**
 * Created by P-CODE on 1/31/2016.
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    public static Context getContext() {
        return myApplication.getApplicationContext();
    }
}
