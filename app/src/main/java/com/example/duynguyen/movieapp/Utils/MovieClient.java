package com.example.duynguyen.movieapp.Utils;

import com.example.duynguyen.movieapp.Model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by duynguyen on 5/16/18.
 */

public interface MovieClient {
    @GET("/3/movie/popular")
    Call<APIResponse> popular_movies (@Query("api_key") String apiKey );
    @GET("/3/movie/top_rated")
    Call<APIResponse> top_rated (@Query("api_key") String apiKey );
}
