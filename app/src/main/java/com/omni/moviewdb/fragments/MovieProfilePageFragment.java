package com.omni.moviewdb.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    private  Movie movie;

    @BindView(R.id.detail_img_cover)
    ImageView coverImage;

    @BindView(R.id.detail_img_movie)
    ImageView movieImage;

    @BindView(R.id.release_date)
    TextView releaseDate;

    @BindView(R.id.vote_avg)
    TextView voteAvg ;

    @BindView(R.id.overView)
    TextView overView ;

    @BindView(R.id.toolbar_details)
    Toolbar toolbar ;

    @BindView(R.id.app_bar)
    AppBarLayout appBar ;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLAyout ;

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

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();
        if(intent.hasExtra("movie")){
            movie = intent.getParcelableExtra("movie") ;
            toolbar.setTitle(movie.getOriginalTitle());
            toolbarLAyout.setTitle(movie.getOriginalTitle());
        }

        Glide.with(getActivity())
                .load(AppConfig.BaseIMAGEURL+"w500/"+movie.getBackdropPath())
                .into(coverImage);

        Glide.with(getActivity())
                .load(AppConfig.BaseIMAGEURL+AppConfig.IMAGE_SIZE+movie.getPosterPath())
                .into(movieImage);


        releaseDate.setText("Release Date: "+movie.getReleaseDate());
        voteAvg.setText("Vote Avgerage: "+String.valueOf(movie.getVoteAverage()));
        overView.setText(movie.getOverview());
        movie.getOriginalTitle();


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


    }



}
