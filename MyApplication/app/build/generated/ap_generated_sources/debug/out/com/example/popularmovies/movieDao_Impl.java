package com.example.popularmovies;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class movieDao_Impl implements movieDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Movie> __insertionAdapterOfMovie;

  private final EntityDeletionOrUpdateAdapter<Movie> __deletionAdapterOfMovie;

  private final EntityDeletionOrUpdateAdapter<Movie> __updateAdapterOfMovie;

  public movieDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovie = new EntityInsertionAdapter<Movie>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `movie_table` (`movieName`,`posterUrl`,`movieOverview`,`releaseDate`,`popularity`,`rating`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Movie value) {
        if (value.movieName == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.movieName);
        }
        if (value.posterUrl == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.posterUrl);
        }
        if (value.movieOverview == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.movieOverview);
        }
        if (value.releaseDate == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.releaseDate);
        }
        if (value.popularity == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.popularity);
        }
        if (value.rating == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.rating);
        }
      }
    };
    this.__deletionAdapterOfMovie = new EntityDeletionOrUpdateAdapter<Movie>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `movie_table` WHERE `movieName` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Movie value) {
        if (value.movieName == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.movieName);
        }
      }
    };
    this.__updateAdapterOfMovie = new EntityDeletionOrUpdateAdapter<Movie>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `movie_table` SET `movieName` = ?,`posterUrl` = ?,`movieOverview` = ?,`releaseDate` = ?,`popularity` = ?,`rating` = ? WHERE `movieName` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Movie value) {
        if (value.movieName == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.movieName);
        }
        if (value.posterUrl == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.posterUrl);
        }
        if (value.movieOverview == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.movieOverview);
        }
        if (value.releaseDate == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.releaseDate);
        }
        if (value.popularity == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.popularity);
        }
        if (value.rating == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.rating);
        }
        if (value.movieName == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.movieName);
        }
      }
    };
  }

  @Override
  public void insert(final Movie movie) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMovie.insert(movie);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteMovies(final Movie... movies) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMovie.handleMultiple(movies);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateFavorite(final Movie movie) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMovie.handle(movie);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Movie>> getMovieList() {
    final String _sql = "SELECT * from movie_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"movie_table"}, false, new Callable<List<Movie>>() {
      @Override
      public List<Movie> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMovieName = CursorUtil.getColumnIndexOrThrow(_cursor, "movieName");
          final int _cursorIndexOfPosterUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "posterUrl");
          final int _cursorIndexOfMovieOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "movieOverview");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
          final int _cursorIndexOfPopularity = CursorUtil.getColumnIndexOrThrow(_cursor, "popularity");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final List<Movie> _result = new ArrayList<Movie>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Movie _item;
            _item = new Movie();
            _item.movieName = _cursor.getString(_cursorIndexOfMovieName);
            _item.posterUrl = _cursor.getString(_cursorIndexOfPosterUrl);
            _item.movieOverview = _cursor.getString(_cursorIndexOfMovieOverview);
            _item.releaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
            if (_cursor.isNull(_cursorIndexOfPopularity)) {
              _item.popularity = null;
            } else {
              _item.popularity = _cursor.getInt(_cursorIndexOfPopularity);
            }
            if (_cursor.isNull(_cursorIndexOfRating)) {
              _item.rating = null;
            } else {
              _item.rating = _cursor.getInt(_cursorIndexOfRating);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
