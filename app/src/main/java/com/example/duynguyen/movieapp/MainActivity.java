package com.example.duynguyen.movieapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.duynguyen.movieapp.Database.AppDatabase;
import com.example.duynguyen.movieapp.Model.FavoriteMovieViewModel;
import com.example.duynguyen.movieapp.Model.Movie;
import com.example.duynguyen.movieapp.Model.MovieList;
import com.example.duynguyen.movieapp.Utils.AutoFitGridLayoutManager;
import com.example.duynguyen.movieapp.Utils.MovieClient;
import com.example.duynguyen.movieapp.Utils.MoviePosterAdapter;
import com.example.duynguyen.movieapp.Utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MoviePosterAdapter.ItemListener {

    private RecyclerView recyclerView;
    private String movieType = "normal type";
    MoviePosterAdapter mMoviePosterAdapter;
    private AppDatabase mDatabase;
    private List<Movie> mFavoriteMovies;

    private static final String TAG = MainActivity.class.getSimpleName();
    final String POPULAR_TYPE = "popular";
    final String TOP_RATED_TYPE = "top rated";
    final String FAVORITE_TYPE = "favorite";
    final String TYPE_EXTRA = "type_extra";
    public static final String API_KEY = BuildConfig.ApiKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mMoviePosterAdapter = new MoviePosterAdapter(this, this);
        recyclerView.setAdapter(mMoviePosterAdapter);
        AutoFitGridLayoutManager autoFitGridLayoutManager = new AutoFitGridLayoutManager(MainActivity.this, 500);
        recyclerView.setLayoutManager(autoFitGridLayoutManager);

        mDatabase = AppDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null) {
            movieType = savedInstanceState.getString(TYPE_EXTRA);
        }
        setupFavoriteViewModel();
        loadMovieData(movieType);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.popular_settings:
                movieType = POPULAR_TYPE;
                loadMovieData(movieType);
                return true;
            case R.id.top_rated_settings:
                movieType = TOP_RATED_TYPE;
                loadMovieData(movieType);
                return true;
            case R.id.favorite_settings:
                movieType = FAVORITE_TYPE;
                loadMovieData(movieType);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void loadMovieData(String type) {
        if (type.equals(FAVORITE_TYPE)) {
            setupFavoriteViewModel();
        }
        else {
            MovieClient client = new RetrofitClient().getClient().create(MovieClient.class);
            Call<MovieList> call;
            if (type.equals(TOP_RATED_TYPE)) {
                call = client.top_rated(API_KEY);
            } else {
                call = client.popular_movies(API_KEY);
            }
            call.enqueue(new Callback<MovieList>() {
                @Override
                public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                    ArrayList<Movie> movies = response.body().getResults();
                    mMoviePosterAdapter.setMoviesData(movies);
                }

                @Override
                public void onFailure(Call<MovieList> call, Throwable t) {
                    //Show alert dialog
                    Log.e("Error", "Error in retrofit");
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setCancelable(false);
                    dialog.setTitle(getString(R.string.connection_error_title));
                    dialog.setMessage(getString(R.string.connection_error));
                    dialog.setPositiveButton(getString(R.string.reload_button), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            loadMovieData(TOP_RATED_TYPE);
                        }
                    });
                    final AlertDialog alert = dialog.create();
                    alert.show();
                }
            });
        }
    }

    private void setupFavoriteViewModel() {
        FavoriteMovieViewModel mFavoriteMovieViewModel = ViewModelProviders.of(this).get(FavoriteMovieViewModel.class);
        mFavoriteMovieViewModel.getFavoriteMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> favoriteMovies) {
                mFavoriteMovies = favoriteMovies;
                if (movieType == FAVORITE_TYPE) {
                    Log.d(TAG, "Updating list of favorite movies from LiveData in ViewModel");
                    mMoviePosterAdapter.setMoviesData((ArrayList<Movie>) favoriteMovies);
                }
            }
        });
    }

    @Override
    public void onItemClick(Movie item) {
        //Toast.makeText(getApplicationContext(), item.getVoteAverage() + " is clicked", Toast.LENGTH_SHORT).show();
        launchDetailActivity(item);
    }

    private void launchDetailActivity(Movie item) {
        Intent intent = new Intent(this, DetailedActivity.class);
        intent.putExtra(DetailedActivity.ID_EXTRA, item.getId());
        intent.putExtra(DetailedActivity.POSTER_EXTRA, item.getPosterPath());
        intent.putExtra(DetailedActivity.TITLE_EXTRA, item.getTitle());
        intent.putExtra(DetailedActivity.OVERVIEW_EXTRA, item.getOverview());
        intent.putExtra(DetailedActivity.RELEASE_DATE_EXTRA, item.getReleaseDate());
        intent.putExtra(DetailedActivity.VOTE_AVERAGE_EXTRA, item.getVoteAverage());
        boolean isFavorite = (compareToFavMov(item));
        intent.putExtra(DetailedActivity.IS_FAVORITE_EXTRA, isFavorite );
        startActivity(intent);
    }

    private boolean compareToFavMov (Movie movie){
        for (int i = 0 ; i<mFavoriteMovies.size() ; i ++ ){
            if (movie.getId().equals(mFavoriteMovies.get(i).getId())){
                return  true;
            }
        }
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TYPE_EXTRA, movieType);
    }
}
