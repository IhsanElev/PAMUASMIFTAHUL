package com.example.myappilication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;


import com.example.myappilication.adapter.FilmAdapter;
import com.example.myappilication.config.ApiConfig;
import com.example.myappilication.config.ApiService;
import com.example.myappilication.rest.ResponseFilm;
import com.example.myappilication.rest.ResultsItem;
import com.example.myapplication.R;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private FilmAdapter adapter = new FilmAdapter();
    private ApiService apiService;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        getMovies(MainActivity.this);
        setTitle("Katalog Film");
    }

    // get data
    private void getMovies(final Context context) {
        apiService = ApiConfig.getInstance().create(ApiService.class);
        apiService.getListMovies(ApiConfig.API_KEY).enqueue(new Callback<ResponseFilm>() {
            @Override
            public void onResponse(Call<ResponseFilm> call, Response<ResponseFilm> response) {
                ResponseFilm responseFilm = response.body();
                if (responseFilm != null) {
                    List<ResultsItem> resultsItems = responseFilm.getResults();
                    adapter.setData((ArrayList<ResultsItem>) resultsItems);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<ResponseFilm> call, Throwable t) {
                showMessage(MainActivity.this, t.getMessage());
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void getQueryMovies(String query) {
        apiService.getQueryListMovies(ApiConfig.API_KEY, query).enqueue(new Callback<ResponseFilm>() {
            @Override
            public void onResponse(Call<ResponseFilm> call, Response<ResponseFilm> response) {
                ResponseFilm responseFilm = response.body();
                if (responseFilm != null) {
                    List<ResultsItem> resultsItems = responseFilm.getResults();
                    adapter.setData((ArrayList<ResultsItem>) resultsItems);
                }
            }

            @Override
            public void onFailure(Call<ResponseFilm> call, Throwable t) {
                showMessage(MainActivity.this, t.getMessage());
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("Cari...");
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            getMovies(this);
        } else {
            getQueryMovies(newText);
        }
        return true;
    }

    private static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

