package com.example.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private movieAdapterOnClickHandler mClickHandler;
    private List<Movie> mMovieList;
    private LayoutInflater mInflater;
    private Context mContext;

    public interface movieAdapterOnClickHandler {
        void onClick(Movie indivMovie);
    }

    public MoviesAdapter(Context context, movieAdapterOnClickHandler mClickHandler)
    {
        this.mContext = context;
        this.mMovieList = new ArrayList<>();
        this.mClickHandler = mClickHandler;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public ImageView imageView;
        public TextView mMovieTextView;

        public MovieViewHolder(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int index = getLayoutPosition();
            Movie indivMovie = mMovieList.get(index);
            mClickHandler.onClick(indivMovie);
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.movie_layout, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        Picasso.get()
                .load(movie.getPosterUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount()
    {
        return (mMovieList == null) ? 0 : mMovieList.size();
    }

    public void setMovieList(List<Movie> movieList)
    {
        this.mMovieList.clear();
        this.mMovieList.addAll(movieList);
        notifyDataSetChanged();
    }
}
