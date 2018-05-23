package com.example.duynguyen.movieapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duynguyen.movieapp.Model.APIResponse;
import com.example.duynguyen.movieapp.Model.Movie;
import com.example.duynguyen.movieapp.Utils.AutoFitGridLayoutManager;
import com.example.duynguyen.movieapp.Utils.MovieClient;
import com.example.duynguyen.movieapp.Utils.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedActivity extends AppCompatActivity  {

    public static final String POSTER_EXTRA = "poster_extra";
    public static final String TITLE_EXTRA ="title_exra";
    public static final String OVERVIEW_EXTRA ="overview_extra";
    public static final String RELEASE_DATE_EXTRA ="resease_date_extra";
    public static final String VOTE_AVERAGE_EXTRA ="rating_extra";

    @BindView(R.id.movie_poster_iv)
    ImageView moviePosterIv;
    @BindView(R.id.movie_title_tv)
    TextView movieTitleTv;
    @BindView(R.id.overview_tv)
    TextView overviewTv;
    @BindView(R.id.release_date_tv)
    TextView releaseDateTv;
    @BindView(R.id.vote_average)
    TextView voteAverageTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent == null){
            closeOnError();
        }

        String poster = intent.getStringExtra(POSTER_EXTRA);
        String title = intent.getStringExtra(TITLE_EXTRA);
        String overView = intent.getStringExtra(OVERVIEW_EXTRA);
        String releaseDate = intent.getStringExtra(RELEASE_DATE_EXTRA);
        String voteAverage = intent.getStringExtra(VOTE_AVERAGE_EXTRA);

        if (poster.isEmpty()||title.isEmpty()||overView.isEmpty()||releaseDate.isEmpty()||voteAverage.isEmpty()){
            closeOnError();
            return;
        }

        Picasso.get().load("http://image.tmdb.org/t/p/w185"+poster).into(moviePosterIv);
        movieTitleTv.setText(title);
        overviewTv.setText(overView);
        releaseDateTv.setText(releaseDate);
        voteAverageTv.setText(voteAverage+"/10");
    }
    private void closeOnError () {
        finish();
        Toast.makeText(this, "Data is not available", Toast.LENGTH_SHORT).show();
    }

    //private void
}
