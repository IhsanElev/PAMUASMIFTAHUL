package com.example.myappilication.config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {
    private static Retrofit retrofit;
    private static String URL_MOVIE = "https://api.themoviedb.org/3/";
    // change api-key in here
    public static final String API_KEY = "67c045cd5b389bb4c3f0c1293431f7de";

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_MOVIE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}