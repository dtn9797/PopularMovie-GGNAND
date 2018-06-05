package com.example.duynguyen.movieapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duynguyen.movieapp.Model.Trailer;
import com.example.duynguyen.movieapp.Model.TrailerList;
import com.example.duynguyen.movieapp.Utils.MovieTrailerAdapter;
import com.example.duynguyen.movieapp.Utils.RetrofitClient;
import com.example.duynguyen.movieapp.Utils.TrailerClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedActivity extends AppCompatActivity implements MovieTrailerAdapter.ItemListener {

    public static final String ID_EXTRA = "id_extra";
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
    @BindView(R.id.trailers_rv)
    RecyclerView trailersRv;
    MovieTrailerAdapter mMovieTrailerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent == null){
            closeOnError();
        }

        String id = intent.getStringExtra(ID_EXTRA);
        String poster = intent.getStringExtra(POSTER_EXTRA);
        String title = intent.getStringExtra(TITLE_EXTRA);
        String overView = intent.getStringExtra(OVERVIEW_EXTRA);
        String releaseDate = intent.getStringExtra(RELEASE_DATE_EXTRA);
        String voteAverage = intent.getStringExtra(VOTE_AVERAGE_EXTRA);

        if (poster.isEmpty()||title.isEmpty()||overView.isEmpty()||releaseDate.isEmpty()||voteAverage.isEmpty()||id.isEmpty()){
            closeOnError();
            return;
        }

        Picasso.get().load("http://image.tmdb.org/t/p/w185"+poster).into(moviePosterIv);
        movieTitleTv.setText(title);
        overviewTv.setText(overView);
        releaseDateTv.setText(releaseDate);
        voteAverageTv.setText(voteAverage+"/10");
        //RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMovieTrailerAdapter =  new MovieTrailerAdapter(this,this);
        trailersRv.setLayoutManager(linearLayoutManager);
        trailersRv.setAdapter(mMovieTrailerAdapter);
        loadTrailers(id);
    }

    private void loadTrailers(final String movieId) {
        TrailerClient client =  new RetrofitClient("https://api.themoviedb.org").getClient().create(TrailerClient.class);
        Call<TrailerList> call =client.trailers(movieId,MainActivity.API_KEY);
        call.enqueue(new Callback<TrailerList>() {
            @Override
            public void onResponse(Call<TrailerList> call, Response<TrailerList> response) {
                ArrayList<Trailer> trailers = response.body().getTrailers();
                mMovieTrailerAdapter.setTrailersData(trailers);
            }

            @Override
            public void onFailure(Call<TrailerList> call, Throwable t) {
                //Show alert dialog
                Log.e("Error",t.getMessage());
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailedActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle(getString(R.string.connection_error_title));
                dialog.setMessage(getString(R.string.connection_error) );
                dialog.setPositiveButton(getString(R.string.reload_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        loadTrailers(movieId);
                    }
                });
                final AlertDialog alert = dialog.create();
                alert.show();
            }
        });
    }

    private void closeOnError () {
        finish();
        Toast.makeText(this, getString(R.string.close_on_intent_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Trailer item) {
        //Toast.makeText(getApplicationContext(),"Trailer is clicked",Toast.LENGTH_LONG).show();
        //open trailer
        Uri webpage = Uri.parse("http://www.youtube.com/watch?v="+item.getKey());
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
