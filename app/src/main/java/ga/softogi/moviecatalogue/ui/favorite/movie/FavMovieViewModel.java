package ga.softogi.moviecatalogue.ui.favorite.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import ga.softogi.moviecatalogue.data.source.FilmRepository;
import ga.softogi.moviecatalogue.data.source.local.entity.MovieEntity;
import ga.softogi.moviecatalogue.vo.Resource;

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
