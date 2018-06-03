package com.example.duynguyen.movieapp.Utils;

import com.example.duynguyen.movieapp.Model.Trailer;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by duynguyen on 6/2/18.
 */

public interface TrailerClient {
    @GET("/3/movie/{id}/videos?api_key=84d34117b17f3abebe9d04a0325e21c6")
    ArrayList<Trailer> trailers(@Path("id") String id, @Query("api_key") String apiKey);
}
