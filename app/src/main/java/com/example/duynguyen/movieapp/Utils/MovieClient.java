package com.example.duynguyen.movieapp.Utils;

import com.example.duynguyen.movieapp.Model.MovieList;
import com.example.duynguyen.movieapp.Model.ReviewList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by duynguyen on 5/16/18.
 */

public interface MovieClient {
    @GET("/3/movie/popular")
    Call<MovieList> popular_movies (@Query("api_key") String apiKey );
    @GET("/3/movie/top_rated")
    Call<MovieList> top_rated (@Query("api_key") String apiKey );
    @GET("/3/movie/{id}/reviews")
    Call<ReviewList> get_review_list(@Path("id") String id, @Query("api_key") String apiKey );
}
