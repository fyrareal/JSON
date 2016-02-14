package com.example.android.volleydata.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.android.volleydata.Interface.VolleyInterface;
import com.example.android.volleydata.R;
import com.example.android.volleydata.Template.EndpointAPI;
import com.example.android.volleydata.Template.Template;
import com.example.android.volleydata.Utils.JSONParser;
import com.example.android.volleydata.Utils.VolleyRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by P-CODE on 1/31/2016.
 */
public class LoginFragment extends Fragment implements VolleyInterface {
    private EditText mUsername, mPassword;
    private Button mLogin;
    private VolleyRequest mRequest;
    private ProgressBar mProgress;
    private JSONParser mJSONParser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mJSONParser = new JSONParser(getContext());
        mRequest = new VolleyRequest(this);
        mProgress = (ProgressBar)view.findViewById(R.id.progress_login);
        mUsername = (EditText)view.findViewById(R.id.username);
        mPassword = (EditText)view.findViewById(R.id.password);
        mLogin = (Button)view.findViewById(R.id.login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> map = new HashMap<>();
                map.put(Template.Query.TAG, Template.Query.LOGIN);
                map.put(Template.Query.USERNAME, mUsername.getText().toString());
                map.put(Template.Query.PASSWORD, mPassword.getText().toString());
                mRequest.sendPostRequest(EndpointAPI.ANDEVINDO, map);
            }
        });

        return view;
    }

    void switchButtonProgress(boolean isLoading){
        if(isLoading){
            mLogin.setVisibility(Button.GONE);
            mProgress.setVisibility(ProgressBar.VISIBLE);
        }else{
            mLogin.setVisibility(Button.VISIBLE);
            mProgress.setVisibility(ProgressBar.GONE);
        }
    }


    @Override
    public void onPrepare() {
        switchButtonProgress(true);
    }

    @Override
    public void onSucces(JSONObject jsonObject) {
        switchButtonProgress(false);
        mJSONParser.isSuccess(jsonObject);

    }

    @Override
    public void onFailed(VolleyError errorListener) {
        switchButtonProgress(false);
        Toast.makeText(getActivity(), "Error : " + errorListener, Toast.LENGTH_SHORT).show();
    }
}
