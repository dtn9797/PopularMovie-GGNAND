package com.example.duynguyen.movieapp.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duynguyen.movieapp.Model.Review;
import com.example.duynguyen.movieapp.R;

import java.util.ArrayList;

/**
 * Created by duynguyen on 6/5/18.
 */

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewHolder> {
    Context mContext;
    ArrayList<Review> mItems = new ArrayList<>();

    public MovieReviewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<Review> mItems) {
        this.mItems = mItems;
        notifyDataSetChanged();
    }

    @Override
    public MovieReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_review_vh, parent, false);
        return new MovieReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieReviewHolder holder, int position) {
        holder.setData(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
