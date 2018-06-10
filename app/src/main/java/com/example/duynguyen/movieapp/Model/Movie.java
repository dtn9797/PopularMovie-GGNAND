package com.example.duynguyen.movieapp.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by duynguyen on 5/15/18.
 */
@Entity(tableName = "movie")
public class Movie {
    @PrimaryKey
    @SerializedName("id")
    private String id;
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

    public Movie(String id, String posterPath, String voteAverage, String overview, String releaseDate, String title) {
        this.id = id;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.title = title;
    }

    public String getId() {
        return id;
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
