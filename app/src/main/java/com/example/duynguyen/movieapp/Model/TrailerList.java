package com.example.duynguyen.movieapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by duynguyen on 6/3/18.
 */

public class TrailerList {
    @SerializedName("results")
    private ArrayList<Trailer> trailers;

    public ArrayList<Trailer> getTrailers() {
        return trailers;
    }
}
