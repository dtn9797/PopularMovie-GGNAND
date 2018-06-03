package com.example.duynguyen.movieapp.Utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.duynguyen.movieapp.Model.Trailer;
import com.example.duynguyen.movieapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by duynguyen on 6/2/18.
 */

public class MovieTrailerHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.trailer_relativeLayout)
    RelativeLayout trailerRelativeLayout;
    @BindView(R.id.trailer_iv)
    ImageView trailerIv;
    @BindView(R.id.trailer_name_tv)
    TextView nameTv;
    @BindView(R.id.trailer_type_tv)
    TextView typeTv;

    Trailer item;

    public MovieTrailerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
    }

    public void setData (Trailer item) {
        this.item = item;

        Picasso.get().load(item.getImagePath()).into(trailerIv);
        nameTv.setText(item.getName());
        typeTv.setText(item.getType());
    }
}
