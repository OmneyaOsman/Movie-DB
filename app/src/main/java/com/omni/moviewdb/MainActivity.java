package com.omni.moviewdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.omni.moviewdb.Api.ApiClient;
import com.omni.moviewdb.Api.ApiService;
import com.omni.moviewdb.model.MovieResponse;
import com.omni.moviewdb.utils.AppConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService  apiService = ApiClient.getClient().create(ApiService.class);

        Call<MovieResponse> getMovies = apiService.getPopularMovies(AppConfig.API_KEY);
        getMovies.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Toast.makeText(MainActivity.this, "get it", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }
}
