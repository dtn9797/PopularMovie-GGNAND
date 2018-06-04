package com.example.duynguyen.movieapp.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duynguyen.movieapp.Model.Trailer;
import com.example.duynguyen.movieapp.R;

import java.util.ArrayList;

/**
 * Created by duynguyen on 6/2/18.
 */

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerHolder> {
    ArrayList<Trailer> mItems = new ArrayList<>();
    Context mContext;
    protected static ItemListener mItemClickListener;

    public MovieTrailerAdapter(Context mContext, ItemListener mItemClickListener) {
        this.mContext = mContext;
        this.mItemClickListener = mItemClickListener;
    }

    public void setTrailersData (ArrayList<Trailer> items){
        mItems=items;
        notifyDataSetChanged();
    }

    @Override
    public MovieTrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_trailer_vh,parent,false);
        return new MovieTrailerHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieTrailerHolder holder, int position) {
        holder.setData(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public interface ItemListener {
        void onItemClick (Trailer item);
    }
}
