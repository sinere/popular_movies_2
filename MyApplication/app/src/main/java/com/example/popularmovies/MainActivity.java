package com.example.popularmovies;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.example.popularmovies.jsonUtils.parseMovieJson;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.movieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;
    private static MoviesAdapter.movieAdapterOnClickHandler mClickHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_mainGrid);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MoviesAdapter(this, (MoviesAdapter.movieAdapterOnClickHandler) this);
        mRecyclerView.setAdapter(mAdapter);
        new apiQueryTask().execute("popular");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.sort_popular:
                String selection = "popular";
                new apiQueryTask().execute(selection);
                return true;
            case R.id.sort_rating:
                selection = "topRated";
                new apiQueryTask().execute(selection);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(Movie indivMovie) {
        Intent intent = new Intent(this, detail.class);
        intent.putExtra("Movie", indivMovie);
        startActivity(intent);
    }

    class apiQueryTask extends AsyncTask<String, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(String... params){

            URL movieRequestUrl = NetworkUtils.buildUrl(params);

            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);
                Log.e(getClass().getName(),"json response = " + jsonMovieResponse);
                return parseMovieJson(jsonMovieResponse);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            mAdapter.setMovieList(movies);
        }
    }
}
