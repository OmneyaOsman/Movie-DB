package com.omni.moviewdb.Api;

import com.omni.moviewdb.model.MovieResponse;
import com.omni.moviewdb.utils.AppConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Omni on 27/09/2017.
 */

public interface ApiService {

//    @FormUrlEncoded
    @GET(AppConfig.POPULAR_ENDPOINT)
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);
}
