package com.example.android.volleydata.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
public class UpdateFragment extends Fragment {
    private EditText mUsernameBefore, mUsernameAfter, mPasswordAfter;
    private Button mFind, mUpdate;
    private VolleyRequest mRequestFind, mRequestUpdate;
    private ProgressBar mProgressFind, mProgressUpdate;
    private LinearLayout mUpdateLayout;
    private JSONParser mJSONParser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mJSONParser = new JSONParser(getContext());
        mUpdateLayout = (LinearLayout) view.findViewById(R.id.update_layout);

        mRequestFind = new VolleyRequest(new VolleyInterface() {
            @Override
            public void onPrepare() {
                switchButtonProgressFind(true);
                resetLayout();
            }

            @Override
            public void onSucces(JSONObject jsonObject) {
                switchButtonProgressFind(false);
                if (mJSONParser.isSuccess(jsonObject)) {
                    mUpdateLayout.setVisibility(LinearLayout.VISIBLE);
                } else {
                    resetLayout();
                }
            }

            @Override
            public void onFailed(VolleyError errorListener) {
                switchButtonProgressFind(false);
                resetLayout();
                Toast.makeText(getActivity(), "Error : " + errorListener, Toast.LENGTH_SHORT).show();
            }
        });

        mRequestUpdate = new VolleyRequest(new VolleyInterface() {
            @Override
            public void onPrepare() {
                switchButtonProgressUpdate(true);
            }

            @Override
            public void onSucces(JSONObject jsonObject) {
                switchButtonProgressUpdate(false);
                if (mJSONParser.isSuccess(jsonObject)) {
                    resetLayout();
                } else {

                }
            }

            @Override
            public void onFailed(VolleyError errorListener) {
                switchButtonProgressUpdate(false);
                Toast.makeText(getActivity(), "Error : " + errorListener, Toast.LENGTH_SHORT).show();
            }
        });
        mProgressFind = (ProgressBar) view.findViewById(R.id.progress_find);
        mProgressUpdate = (ProgressBar) view.findViewById(R.id.progress_update);
        mUsernameBefore = (EditText) view.findViewById(R.id.username);
        mUsernameAfter = (EditText) view.findViewById(R.id.username_update);
        mPasswordAfter = (EditText) view.findViewById(R.id.password_update);
        mFind = (Button) view.findViewById(R.id.find);
        mFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = new HashMap<>();
                map.put(Template.Query.TAG, Template.Query.FIND);
                map.put(Template.Query.USERNAME, mUsernameBefore.getText().toString());
                mRequestFind.sendPostRequest(EndpointAPI.JATI, map);
            }
        });

        mUpdate = (Button) view.findViewById(R.id.update);
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = new HashMap<>();
                map.put(Template.Query.TAG, Template.Query.UPDATE);
                map.put(Template.Query.USERNAME_BEFORE, mUsernameBefore.getText().toString());
                map.put(Template.Query.USERNAME, mUsernameAfter.getText().toString());
                map.put(Template.Query.PASSWORD, mPasswordAfter.getText().toString());
                mRequestUpdate.sendPostRequest(EndpointAPI.JATI, map);
            }
        });
        return view;
    }

    void resetLayout() {
        mUpdateLayout.setVisibility(LinearLayout.GONE);
        mUsernameAfter.setText("");
        mPasswordAfter.setText("");
    }

    void switchButtonProgressFind(boolean isLoading) {
        if (isLoading) {
            mFind.setVisibility(Button.GONE);
            mProgressFind.setVisibility(ProgressBar.VISIBLE);
        } else {
            mFind.setVisibility(Button.VISIBLE);
            mProgressFind.setVisibility(ProgressBar.GONE);
        }
    }

    void switchButtonProgressUpdate(boolean isLoading) {
        if (isLoading) {
            mUpdate.setVisibility(Button.GONE);
            mProgressUpdate.setVisibility(ProgressBar.VISIBLE);
        } else {
            mUpdate.setVisibility(Button.VISIBLE);
            mProgressUpdate.setVisibility(ProgressBar.GONE);
        }
    }
}
