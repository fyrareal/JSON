package com.example.android.volleydata.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.volleydata.Fragment.DeleteFragment;
import com.example.android.volleydata.Fragment.LoginFragment;
import com.example.android.volleydata.Fragment.SignUpFragment;
import com.example.android.volleydata.Fragment.UpdateFragment;

/**
 * Created by P-CODE on 1/31/2016.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private String[] title = {"Login", "SignUp", "Update", "Delete"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if (position == 0) {
            fragment = new LoginFragment();
        } else if (position == 1) {
            fragment = new SignUpFragment();
        } else if (position == 2) {
            fragment = new UpdateFragment();
        } else {
            fragment = new DeleteFragment();
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
