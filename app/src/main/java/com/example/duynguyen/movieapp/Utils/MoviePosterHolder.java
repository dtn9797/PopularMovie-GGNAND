package com.example.duynguyen.movieapp.Utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.duynguyen.movieapp.Model.Movie;
import com.example.duynguyen.movieapp.R;
import com.squareup.picasso.Picasso;

public class MoviePosterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public RelativeLayout relativeLayout;
    public ImageView imageView;
    Movie item;


    public MoviePosterHolder(View v) {
        super(v);

        v.setOnClickListener(this);
        relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);
        imageView = (ImageView) v.findViewById(R.id.imageView);
    }

    public void setData (Movie item) {
        this.item = item;

        Picasso.get().load("http://image.tmdb.org/t/p/w185"+item.getPoster_path()).into(imageView);
    }

    @Override
    public void onClick(View v) {
        if (MoviePosterAdapter.mItemClickListener!=null){
            MoviePosterAdapter.mItemClickListener.onItemClick(item);
        }
    }
}