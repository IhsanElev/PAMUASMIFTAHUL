package com.example.myappilication.rest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFilm {

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<ResultsItem> results;

    @SerializedName("total_results")
    private int totalResults;

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<ResultsItem> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
