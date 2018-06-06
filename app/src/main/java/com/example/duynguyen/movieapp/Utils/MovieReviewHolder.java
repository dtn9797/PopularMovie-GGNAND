package com.example.duynguyen.movieapp.Utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.duynguyen.movieapp.Model.Review;
import com.example.duynguyen.movieapp.R;

/**
 * Created by duynguyen on 6/5/18.
 */

public class MovieReviewHolder extends RecyclerView.ViewHolder {
    TextView authorTv;
    TextView detailTv;
    public MovieReviewHolder(View itemView) {
        super(itemView);

        authorTv = (TextView)itemView.findViewById(R.id.author_tv);
        detailTv = (TextView)itemView.findViewById(R.id.review_detail_tv);
    }

    public void setData (Review review){
        authorTv.setText(review.getAuthor());
        detailTv.setText(review.getDetail());
    }
}
