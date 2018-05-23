package com.example.duynguyen.movieapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by duynguyen on 5/15/18.
 */

public class Movie {
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("original_title")
    private String title;

    public Movie (String url){
        posterPath = url;
    }

    public String getPoster_path() {
        return posterPath;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }
}
