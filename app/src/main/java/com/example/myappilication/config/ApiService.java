package com.example.myappilication.config;

import com.example.myappilication.rest.ResponseFilm;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("discover/movie")
    Call<ResponseFilm> getListMovies(
            @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<ResponseFilm> getQueryListMovies(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );
}
