package com.example.duynguyen.movieapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by duynguyen on 5/16/18.
 */

public class APIResponse {
    @SerializedName("results")
    private ArrayList<Movie> movies;


    public ArrayList<Movie> getResults() {
        return movies;
    }

    public void setResults(ArrayList<Movie> movies) {
        this.movies = movies;
    }


}
