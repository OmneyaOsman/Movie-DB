package com.omni.moviewdb.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.omni.moviewdb.R;
import com.omni.moviewdb.model.Movie;
import com.omni.moviewdb.utils.AppConfig;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Omni on 03/10/2017.
 */

public class MovieProfilePageFragment extends Fragment {

    private Intent intent ;
    private  Movie movie;

    @BindView(R.id.detail_img_cover)
    ImageView coverImage;

    @BindView(R.id.detail_img_movie)
    ImageView movieImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_profile, container, false);

        ButterKnife.bind(this , rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        intent = getActivity().getIntent();
        if(intent.hasExtra("movie")){
            movie = intent.getParcelableExtra("movie") ;
            Toolbar toolbar = getActivity().findViewById(R.id.toolbar_details);
            toolbar.setTitle(movie.getOriginalTitle());
        }

        Glide.with(getActivity())
                .load(AppConfig.BaseIMAGEURL+"w500/"+movie.getBackdropPath())
                .into(coverImage);

        Glide.with(getActivity())
                .load(AppConfig.BaseIMAGEURL+AppConfig.IMAGE_SIZE+movie.getPosterPath())
                .into(movieImage);


    }
}
