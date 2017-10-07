package com.omni.moviewdb.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Omni on 04/10/2017.
 */

public abstract class BaseFragment extends Fragment {

    //todo create Method to check for network connectivity


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public boolean isNetworkConnected() {
        boolean isConnected = false;

        try {

            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            isConnected = networkInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnected;
    }
}
