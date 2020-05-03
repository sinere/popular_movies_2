package com.example.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class jsonUtils {

    public static List<Movie> parseMovieJson(String json) {

        try {
            JSONObject movieJson = new JSONObject(json);
            JSONArray resultJSONArray = movieJson.optJSONArray("results");
            List<Movie> movies = new ArrayList<>();
            if(resultJSONArray != null) {
                for (int i = 0; i < resultJSONArray.length(); i++) {
                    Movie movie = new Movie();
                    JSONObject movieObject = resultJSONArray.getJSONObject(i);
                    movie.setMovieName(movieObject.getString("original_title"));
                    movie.setMovieOverview(movieObject.getString("overview"));
                    movie.setRating(movieObject.getInt("vote_average"));
                    movie.setReleaseDate(movieObject.getString("release_date"));
                    movie.setPosterUrl("https://image.tmdb.org/t/p/w500" + movieObject.getString("poster_path"));
                    Log.e("CHECK URL", "image url = " + movie.getPosterUrl() );
                    movies.add(movie);
                }
            }
            return movies;
//            Log.e("CHECK NAME", "name = " + movie.getMovieName() );

        } catch (JSONException e) {
            System.out.println("Something went wrong.");
        }
        return null;
    }
}
