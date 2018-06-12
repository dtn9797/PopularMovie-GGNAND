package com.example.duynguyen.movieapp.Model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.duynguyen.movieapp.Database.AppDatabase;

import java.util.List;

/**
 * Created by duynguyen on 6/10/18.
 */

public class FavoriteMovieViewModel extends AndroidViewModel {
    private LiveData<List<Movie>> favoriteMovies;
    private static final String TAG = FavoriteMovieViewModel.class.getSimpleName();

    public FavoriteMovieViewModel(Application application) {
        super(application);
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        favoriteMovies = AppDatabase.getInstance(this.getApplication()).movieDao().loadAllMovies();
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        return favoriteMovies;
    }
}
