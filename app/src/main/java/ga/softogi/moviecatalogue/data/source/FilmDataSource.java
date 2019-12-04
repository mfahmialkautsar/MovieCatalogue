package ga.softogi.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import java.util.List;

import ga.softogi.moviecatalogue.data.source.local.entity.MovieEntity;
import ga.softogi.moviecatalogue.data.source.local.entity.TvEntity;
import ga.softogi.moviecatalogue.vo.Resource;

public interface FilmDataSource {

    LiveData<Resource<List<MovieEntity>>> getAllMovies();

    LiveData<Resource<List<TvEntity>>> getAllTvs();

    LiveData<Resource<MovieEntity>> getMovieById(String id);

    LiveData<Resource<TvEntity>> getTvById(String id);

    LiveData<Resource<PagedList<MovieEntity>>> getFavoritedMovieAsPaged();

    LiveData<Resource<PagedList<TvEntity>>> getFavoritedTvAsPaged();

    void setMovieFavorite(MovieEntity film, boolean state);

    void setTvFavorite(TvEntity film, boolean state);
}
