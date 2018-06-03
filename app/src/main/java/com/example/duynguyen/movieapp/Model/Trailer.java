package com.example.duynguyen.movieapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by duynguyen on 6/2/18.
 */

public class Trailer {
    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;


    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    public String getImagePath(){
        return "http://img.youtube.com/vi/"+key+"/mqdefault.jpg";
    }
}
