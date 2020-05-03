package com.example.popularmovies;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface movieDao {
        @Insert
        void insert(Movie movie);

        @Delete
        void deleteMovies(Movie... movies);

        @Query("SELECT * from movie_table")
        LiveData<List<Movie>> getMovieList();

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateFavorite(Movie movie);
}
