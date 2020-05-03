package com.example.popularmovies;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Utility functions to handle TMDb JSON data.
 */

public final class TmdbJsonUtils {

    public static String getSimpleMovieStringsFromJson(Context context, String forecastJsonStr)
            throws JSONException {

        /* Movie information. Each movie's info is an element of the "list" array */
        final String TMD_LIST = "list";

        final String TMD_MESSAGE_CODE = "cod";

        /* String array to hold each movie's String */
        String[] parsedMovieData = null;

        JSONObject movieJson = new JSONObject(forecastJsonStr);

        /* Is there an error? */
        if (movieJson.has(TMD_MESSAGE_CODE)) {
            int errorCode = movieJson.getInt(TMD_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    return null;
                default:
                    /* Server probably down */
                    return null;
            }
        }

        JSONArray movieArray = movieJson.getJSONArray(TMD_LIST);

        parsedMovieData = new String[movieArray.length()];

        for (int i = 0; i < movieArray.length(); i++) {
            String movieName;
            String movieOverview;

            /* Get the JSON object representing the movie list*/
            JSONObject movieArrayJSONObject = movieArray.getJSONObject(i);


        }

        return TMD_LIST;
    }
}
