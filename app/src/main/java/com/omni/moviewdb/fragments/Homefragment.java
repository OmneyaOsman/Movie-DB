package com.omni.moviewdb.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.omni.moviewdb.Api.ApiClient;
import com.omni.moviewdb.Api.ApiService;
import com.omni.moviewdb.DetailsActivity;
import com.omni.moviewdb.R;
import com.omni.moviewdb.adapter.ImageAdapter;
import com.omni.moviewdb.model.Movie;
import com.omni.moviewdb.model.MovieResponse;
import com.omni.moviewdb.utils.AppConfig;
import com.omni.moviewdb.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Homefragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener , ImageAdapter.OnItemClickListener {


    private Unbinder unbinder ;
    private  Call<MovieResponse> getMovies ;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView ;

    @BindView(R.id.progressBar)
    ProgressBar progressBar ;

    @BindView(R.id.movies_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout ;

    private List<Movie> movies  = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment ,container , false);
        unbinder = ButterKnife.bind(this , rootView);

        GridLayoutManager manager = new GridLayoutManager(getActivity() , 2 );


        recyclerView.setLayoutManager(manager);

        return rootView ;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


swipeRefreshLayout.setOnRefreshListener(this);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(isNetworkConnected()){

            ApiService apiService = ApiClient.getClient().create(ApiService.class);
            if(getSortKey().equals(getString(R.string.pref_sort_by_popular_value)))
                getMovies = apiService.getPopularMovies(AppConfig.API_KEY);
            else
                getMovies = apiService.getTopRatedMovies(AppConfig.API_KEY);

            senMoviesRequest(getMovies , 0);

        }else
        {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), R.string.check_network, Toast.LENGTH_SHORT).show();
        }
    }


    private String getSortKey() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sort = sharedPreferences.getString(
                getString(R.string.pref_sort_by_key),
                getString(R.string.pref_sort_by_default_value));

        return sort;
    }

    private void senMoviesRequest(Call<MovieResponse> getMovies , final int refresh){


        getMovies.enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {

                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {

                    ImageAdapter adapter = new ImageAdapter(getActivity(),
                            getPostersList((ArrayList<Movie>) response.body().getMovies()), Homefragment.this);
                    recyclerView.setAdapter(adapter);
                    movies = response.body().getMovies();

                }


                if(refresh==1)
                    swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {

                progressBar.setVisibility(View.GONE);


                if(refresh==1)
                    swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private ArrayList<String> getPostersList(ArrayList<Movie> movies) {
        ArrayList<String> posters = new ArrayList<>();

        for (Movie movie : movies) {

            posters.add(movie.getPosterPath());

        }

        return posters;
    }






    @Override
    public void onPause() {
        super.onPause();
        if (getMovies != null)
            getMovies.cancel();

    }

    @Override
    public void onRefresh() {

        if(isNetworkConnected()){


            ApiService apiService = ApiClient.getClient().create(ApiService.class);
            if(getSortKey().equals(getString(R.string.pref_sort_by_popular_value)))
                getMovies = apiService.getPopularMovies(AppConfig.API_KEY);
            else
                getMovies = apiService.getTopRatedMovies(AppConfig.API_KEY);

            senMoviesRequest(getMovies ,1);

        }else
        {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), R.string.check_network, Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    public void setOnItemClickListener(int position) {

        
        //// TODO: 07/10/2017  open activity with details of item 
        Toast.makeText(getActivity(), "Position is" +position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity() , DetailsActivity.class);
        intent.putExtra("movie",movies.get(position));
        startActivity(intent);

    }
}
