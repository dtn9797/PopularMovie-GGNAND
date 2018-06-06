package com.example.duynguyen.movieapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by duynguyen on 6/5/18.
 */

public class ReviewList {
    @SerializedName("results")
    private ArrayList<Review> reviews;

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}
