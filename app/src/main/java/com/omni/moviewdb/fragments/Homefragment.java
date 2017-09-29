package com.omni.moviewdb.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omni.moviewdb.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Omni on 27/09/2017.
 */

public class Homefragment extends Fragment {


    private Unbinder unbinder ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment ,container , false);
        unbinder = ButterKnife.bind(this , rootView);
        return rootView ;
    }
}
