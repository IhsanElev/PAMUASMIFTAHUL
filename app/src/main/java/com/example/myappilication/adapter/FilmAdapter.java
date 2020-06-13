package com.example.myappilication.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.myapplication.R;
import com.example.myappilication.rest.ResultsItem;
import com.example.myappilication.DetailActivity;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {
    private ArrayList<ResultsItem> data = new ArrayList<>();
    private static final String URL_IMG = "https://image.tmdb.org/t/p/w500";

    public ArrayList<ResultsItem> getData() {
        return data;
    }

    public void setData(ArrayList<ResultsItem> resultsItems) {
        if (resultsItems.size() > 0) {
            data.clear();
        }
        data.addAll(resultsItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FilmViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext())
                .load(URL_IMG + data.get(position).getPosterPath())
                .apply(new RequestOptions().override(150, 200))
                .into(holder.gambarFilm);
        holder.judulFilm.setText(data.get(position).getTitle());
        holder.tanggalFilm.setText(data.get(position).getReleaseDate());
        holder.deskripsiFilm.setText(data.get(position).getOverview());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.DATA, data.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        ImageView gambarFilm;
        TextView judulFilm, tanggalFilm, deskripsiFilm;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            gambarFilm = itemView.findViewById(R.id.gambarBackground);
            judulFilm = itemView.findViewById(R.id.judulFilm);
            tanggalFilm = itemView.findViewById(R.id.tanggalFilm);
            deskripsiFilm = itemView.findViewById(R.id.deskripsiFilm);
        }
    }
}
