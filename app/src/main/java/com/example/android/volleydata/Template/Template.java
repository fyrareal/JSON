package com.example.android.volleydata.Template;

/**
 * Created by P-CODE on 1/31/2016.
 */
public interface Template {
    interface VolleyRetryPolicy{
        int SOCKET_TIMEOUT = 3000;
        int RETRIES = 1;
    }

    interface Query{
        String TAG = "tag";
        String USERNAME_BEFORE = "usernameBefore";
        String USERNAME = "username";
        String PASSWORD = "password";

        String LOGIN = "login";
        String SIGNUP = "signUp";
        String FIND = "find";
        String UPDATE = "update";
        String DELETE = "delete";
    }
}
