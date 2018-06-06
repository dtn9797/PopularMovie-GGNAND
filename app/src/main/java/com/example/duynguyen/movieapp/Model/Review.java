package com.example.duynguyen.movieapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by duynguyen on 6/5/18.
 */

public class Review {
    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String detail;

    public String getAuthor() {
        return author;
    }

    public String getDetail() {
        return detail;
    }
}
