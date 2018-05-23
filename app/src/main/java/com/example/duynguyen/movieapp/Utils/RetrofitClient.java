package com.example.duynguyen.movieapp.Utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL= "https://api.themoviedb.org";


public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
