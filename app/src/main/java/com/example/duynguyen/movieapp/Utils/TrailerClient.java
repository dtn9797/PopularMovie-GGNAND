package com.example.duynguyen.movieapp.Utils;

import com.example.duynguyen.movieapp.Model.Trailer;
import com.example.duynguyen.movieapp.Model.TrailerList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by duynguyen on 6/2/18.
 */

public interface TrailerClient {
    @GET("/3/movie/{id}/videos")
    Call<TrailerList> trailers(@Path("id") String id, @Query("api_key") String apiKey);
}
