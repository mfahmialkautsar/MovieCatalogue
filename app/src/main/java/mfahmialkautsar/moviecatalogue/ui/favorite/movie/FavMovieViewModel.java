package mfahmialkautsar.moviecatalogue.ui.favorite.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import mfahmialkautsar.moviecatalogue.data.source.FilmRepository;
import mfahmialkautsar.moviecatalogue.data.source.local.entity.MovieEntity;
import mfahmialkautsar.moviecatalogue.vo.Resource;

public class FavMovieViewModel extends ViewModel {
    private FilmRepository filmRepository;

    public FavMovieViewModel(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    LiveData<Resource<PagedList<MovieEntity>>> getFavMovie() {
        return filmRepository.getFavoritedMovieAsPaged();
    }

    void setFavorite(MovieEntity movieEntity) {
        final boolean newState = !movieEntity.isFavorited();
        filmRepository.setMovieFavorite(movieEntity, newState);
    }
}
