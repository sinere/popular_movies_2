package com.example.popularmovies;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class favoriteMovieEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String movieName;
    private String posterURL;
    private String movieOverview;
    private String releaseDate;
    private Integer rating;

    @Ignore
    public favoriteMovieEntry(String movieName, String posterURL, String releaseDate,
                              Integer rating, String movieOverview) {
        this.movieName = movieName;
        this.posterURL = posterURL;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.movieOverview = movieOverview;
    }

    public favoriteMovieEntry(int id, String movieName, String posterURL, String releaseDate,
                              Integer rating, String movieOverview) {
        this.id = id;
        this.movieName = movieName;
        this.posterURL = posterURL;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.movieOverview = movieOverview;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

}
