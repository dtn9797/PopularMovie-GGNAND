package com.example.duynguyen.movieapp.Utils;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.duynguyen.movieapp.Model.Movie;

import java.util.List;

/**
 * Created by duynguyen on 6/9/18.
 */
@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    LiveData<List<Movie>> loadAllTasks();

    @Insert
    void insertTask(Movie movie);
}
