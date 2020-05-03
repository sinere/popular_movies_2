package com.example.popularmovies;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class movieViewModel extends AndroidViewModel {
    private movieRepository mRepository;

    private LiveData<List<Movie>> movieList;

    public movieViewModel(Application application) {
        super(application);
        mRepository = new movieRepository(application);
        movieList = mRepository.getMovieList();
    }

    LiveData<List<Movie>> getMovieList() {
        return movieList;
    }

    public void insert(Movie movie) {
        mRepository.insert(movie);
    }
}
