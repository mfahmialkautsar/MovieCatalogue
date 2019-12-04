package ga.softogi.moviecatalogue.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import java.util.List;

import ga.softogi.moviecatalogue.data.source.local.entity.MovieEntity;
import ga.softogi.moviecatalogue.data.source.local.entity.TvEntity;
import ga.softogi.moviecatalogue.data.source.local.room.FilmDao;

public class LocalRepository {

    private static LocalRepository INSTANCE;
    private final FilmDao mFilmDao;

    private LocalRepository(FilmDao mFilmDao) {
        this.mFilmDao = mFilmDao;
    }

    public static LocalRepository getInstance(FilmDao filmDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(filmDao);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieEntity>> getAllMovies() {
        return mFilmDao.getMovies();
    }

    public LiveData<List<TvEntity>> getAllTvs() {
        return mFilmDao.getTvs();
    }

    public LiveData<MovieEntity> getMovieById(final String id) {
        return mFilmDao.getMovieById(id);
    }

    public LiveData<TvEntity> getTvById(final String id) {
        return mFilmDao.getTvById(id);
    }

    public void insertMovies(List<MovieEntity> films) {
        mFilmDao.insertFilms(films);
    }

    public void insertTvs(List<TvEntity> tvs) {
        mFilmDao.insertTvs(tvs);
    }

    public DataSource.Factory<Integer, MovieEntity> getFavoritedMoviesAsPaged() {
        return mFilmDao.getFavoritedFilmsAsPaged();
    }

    public DataSource.Factory<Integer, TvEntity> getFavoritedTvsAsPaged() {
        return mFilmDao.getFavoritedTvsAsPaged();
    }

    public void setMovieFavorite(MovieEntity film, boolean newState) {
        film.setFavorited(newState);
        mFilmDao.updateFilm(film);
    }

    public void setTvFavorite(TvEntity tv, boolean newState) {
        tv.setFavorited(newState);
        mFilmDao.updateTv(tv);
    }
}
