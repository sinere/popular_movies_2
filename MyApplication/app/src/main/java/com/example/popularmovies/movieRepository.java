package com.example.popularmovies;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class movieRepository {
    private movieDao movieDao;
    private LiveData<List<Movie>> movieList;

    movieRepository(Application application) {
        movieRoomDatabase db = movieRoomDatabase.getDatabase(application);
        movieDao = db.movieDao();
        movieList = movieDao.getMovieList();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Movie>> getMovieList() {
        return movieList;
    }

    // Call this on a non-UI thread to prevent app from throwing an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Movie movie) {
        movieRoomDatabase.databaseWriteExecutor.execute(() -> {
            movieDao.insert(movie);
        });
    }
}
