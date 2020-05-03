package com.example.popularmovies;

import android.content.Intent;
import android.os.Bundle;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class detail extends AppCompatActivity {

    private movieRoomDatabase favDB;
    private Boolean isMovieFavorite;
    private ImageView mFavHeartButton;
    private movieRoomDatabase mFavoriteMovieDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView movieIv = findViewById(R.id.image_poster);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        favDB = movieRoomDatabase.getDatabase(getApplicationContext());

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("Movie");

        String movieName = movie.getMovieName();
        String posterURL = movie.getPosterUrl();
        String movieOverview = movie.getMovieOverview();
        String releaseDate = movie.getReleaseDate();
        Integer rating = movie.getRating();

        TextView textViewName = findViewById(R.id.movie_name);
        textViewName.setText(movieName);

        ImageView imageView = findViewById(R.id.image_poster);
        int resID = getResources().getIdentifier(posterURL, "drawable", getPackageName());
        imageView.setImageResource(resID);

        TextView textViewOverview = findViewById(R.id.movie_overview);
        textViewOverview.setText("Plot Synopsis:\n" + movieOverview);

        TextView textViewRelease = findViewById(R.id.release_date);
        textViewRelease.setText("Release Date: " + releaseDate);

        TextView textViewRating = findViewById(R.id.movie_rating);
        textViewRating.setText("Average Vote: " + rating.toString());

        Picasso.get()
                .load(movie.getPosterUrl())
                .into(movieIv);

        setTitle(movie.getMovieName());

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //Favorite "Heart" button and setting an OnClickListener on it.
        //When clicked it will save the movie ID, title, poster, release date, user rating and plot
        //into the FavoriteMovieDatabase
        mFavHeartButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                final favoriteMovieEntry mov = new favoriteMovieEntry(
                        movie.getMovieName(),
                        movie.getPosterUrl(),
                        movie.getReleaseDate(),
                        movie.getRating(),
                        movie.getMovieOverview()
                );
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (isMovieFavorite) {
                            //If movie is already a favorite then delete it from the database
                            mFavoriteMovieDB.movieDao().deleteMovies(movie);
                        } else {
                            //If movie is NOT already a favorite then insert it into the database
                            mFavoriteMovieDB.movieDao().insert(movie);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setFavorite(!isMovieFavorite);
                            }
                        });
                    }

                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Setting the favorites icon to be full if it is a favorited movie and empty if it is not a favorite.
    //Show a toast message when a movie is added or deleted from the favorites.
    private void setFavorite(Boolean favorite) {
        if (favorite) {
            isMovieFavorite = true;
            mFavHeartButton = findViewById(R.id.action_favorite);
            mFavHeartButton.setImageResource(R.drawable.ic_star);
            Toast.makeText(detail.this, getString(R.string.detail_activity_success_adding_a_movie),
                    Toast.LENGTH_SHORT).show();
        } else {
            isMovieFavorite = false;
            mFavHeartButton = findViewById(R.id.action_favorite);
            Toast.makeText(detail.this, getString(R.string.detail_activity_success_deleting_a_movie),
                    Toast.LENGTH_SHORT).show();
        }
    }

}
