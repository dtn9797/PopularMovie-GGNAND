package com.example.duynguyen.movieapp.Utils;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class MovieTrailerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    RelativeLayout trailerRelativeLayout;
    ImageView trailerIv;
    TextView nameTv;
    TextView typeTv;

    Trailer item;

    public MovieTrailerHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        trailerRelativeLayout=(RelativeLayout)itemView.findViewById(R.id.trailer_relativeLayout);
        trailerIv=(ImageView)itemView.findViewById(R.id.trailer_iv);
        nameTv=(TextView)itemView.findViewById(R.id.trailer_name_tv);
        typeTv=(TextView)itemView.findViewById(R.id.trailer_type_tv);
    }

    public void setData (Trailer item) {
        this.item = item;
        Log.d("ImagePath data",item.getImagePath());
        Picasso.get().load(item.getImagePath()).into(trailerIv);
        nameTv.setText(item.getName());
        typeTv.setText(item.getType());
    }

    @Override
    public void onClick(View v) {
        if (MovieTrailerAdapter.mItemClickListener!=null) {
            MovieTrailerAdapter.mItemClickListener.onItemClick(item);
        }
    }
}
