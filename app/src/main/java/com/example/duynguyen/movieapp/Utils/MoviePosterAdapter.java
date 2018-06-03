package com.example.duynguyen.movieapp.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duynguyen.movieapp.Model.Movie;
import com.example.duynguyen.movieapp.R;

import java.util.ArrayList;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterHolder> {

    ArrayList<Movie> mItems=new ArrayList<Movie>();
    Context mContext;
    protected static ItemListener mItemClickListener;

    public MoviePosterAdapter(Context context, ItemListener itemClickListener){
        mContext = context;
        mItemClickListener = itemClickListener;
    }


//    public class MoviePosterHolder extends RecyclerView.MoviePosterHolder implements View.OnClickListener{
//        public RelativeLayout relativeLayout;
//        public ImageView imageView;
//        Movie item;
//
//
//        public MoviePosterHolder(View v) {
//            super(v);
//
//            v.setOnClickListener(this);
//            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);
//            imageView = (ImageView) v.findViewById(R.id.imageView);
//        }
//
//        public void setData (Movie item) {
//            this.item = item;
//
//            Picasso.get().load("http://image.tmdb.org/t/p/w185"+item.getPoster_path()).into(imageView);
//        }
//
//        @Override
//        public void onClick(View v) {
//            if (mItemClickListener!=null){
//                mItemClickListener.onItemClick(item);
//            }
//        }
//    }

    public void setMoviesData (ArrayList<Movie> movies){
        mItems= movies;
        notifyDataSetChanged();
    }

    @Override
    public MoviePosterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Context context = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_poster_vh, parent, false);
        return new MoviePosterHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviePosterHolder holder, int position) {
        holder.setData(mItems.get(position));
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public interface ItemListener {
        void onItemClick (Movie item);
    }
}
