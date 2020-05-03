package com.example.popularmovies;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

public final class NetworkUtils {

    private static final String BASE_URL =
            "https://api.themoviedb.org/3/movie/popular";
    private static final String TR_URL =
            "https://api.themoviedb.org/3/movie/top_rated";
    private static final String API_KEY =
            "bdc0f84e500c0f3817c0b25347707025";

    /**
     * Builds the URL used to talk to the moviedb.org.
     */

    public static URL buildUrl(String[] selection) {

        switch (selection[0]) {
            case "popular":
                Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter("api_key", API_KEY)
                        .build();
                URL url = null;
                try {
                    url = new URL(builtUri.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                Log.v(TAG, "Built URI " + url);

                return url;
            case "topRated":
                builtUri = Uri.parse(TR_URL).buildUpon()
                        .appendQueryParameter("api_key", API_KEY)
                        .build();
                url = null;
                try {
                    url = new URL(builtUri.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                Log.v(TAG, "Built URI " + url);

                return url;
            default:
                builtUri = Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter("api_key", API_KEY)
                        .build();
                url = null;
                try {
                    url = new URL(builtUri.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                Log.v(TAG, "Built URI " + url);

                return url;
        }
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            Log.v(TAG, "Scanner " + scanner);

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
