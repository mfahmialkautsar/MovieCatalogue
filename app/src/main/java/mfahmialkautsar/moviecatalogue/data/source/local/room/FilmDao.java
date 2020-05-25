package mfahmialkautsar.moviecatalogue.data.source.local.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import mfahmialkautsar.moviecatalogue.data.source.local.entity.MovieEntity;
import mfahmialkautsar.moviecatalogue.data.source.local.entity.TvEntity;

@Dao
public interface FilmDao {

    //Movie
    @WorkerThread
    @Query("SELECT * FROM movieentities")
    LiveData<List<MovieEntity>> getMovies();

    @WorkerThread
    @Query("SELECT * FROM movieentities WHERE favorited = 1")
    DataSource.Factory<Integer, MovieEntity> getFavoritedFilmsAsPaged();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertFilms(List<MovieEntity> films);

    @Update()
    int updateFilm(MovieEntity films);

    @Query("SELECT * FROM movieentities WHERE movieId = :filmId")
    LiveData<MovieEntity> getMovieById(String filmId);

    //TV
    @WorkerThread
    @Query("SELECT * FROM tventities")
    LiveData<List<TvEntity>> getTvs();

    @WorkerThread
    @Query("SELECT * FROM tventities WHERE favorited = 1")
    DataSource.Factory<Integer, TvEntity> getFavoritedTvsAsPaged();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTvs(List<TvEntity> films);

    @Update()
    int updateTv(TvEntity films);

    @Query("SELECT * FROM tventities WHERE tvId = :tvId")
    LiveData<TvEntity> getTvById(String tvId);
}
