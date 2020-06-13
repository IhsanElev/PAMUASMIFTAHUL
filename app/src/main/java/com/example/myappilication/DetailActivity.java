package com.example.myappilication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.myappilication.rest.ResultsItem;
import com.example.myapplication.R;

public class DetailActivity extends AppCompatActivity {
    private ImageView gambarFilm;
    private TextView judulFilm, tanggalFilm, deskripsiFilm;
    public static final String DATA = "data";
    private ResultsItem dataIntent;
    private static final String URL_IMG = "https://image.tmdb.org/t/p/w500";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        gambarFilm = findViewById(R.id.gambarFilm);
        judulFilm = findViewById(R.id.judulFilm);
        tanggalFilm = findViewById(R.id.tanggalFilm);
        deskripsiFilm = findViewById(R.id.deskripsiFilm);

        dataIntent = getIntent().getParcelableExtra(DATA);
        if (dataIntent != null) {
            Glide.with(DetailActivity.this)
                    .load(URL_IMG + dataIntent.getPosterPath())
                    .into(gambarFilm);
            judulFilm.setText(dataIntent.getTitle());
            tanggalFilm.setText(dataIntent.getReleaseDate());
            deskripsiFilm.setText(dataIntent.getOverview());

            setTitle(dataIntent.getTitle());
        }
    }
}

