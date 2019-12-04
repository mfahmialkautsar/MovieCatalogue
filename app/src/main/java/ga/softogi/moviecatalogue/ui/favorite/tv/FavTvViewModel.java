package ga.softogi.moviecatalogue.ui.favorite.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import ga.softogi.moviecatalogue.data.source.FilmRepository;
import ga.softogi.moviecatalogue.data.source.local.entity.TvEntity;
import ga.softogi.moviecatalogue.vo.Resource;

public class FavTvViewModel extends ViewModel {
    private FilmRepository filmRepository;

    public FavTvViewModel(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    LiveData<Resource<PagedList<TvEntity>>> getFavTv() {
        return filmRepository.getFavoritedTvAsPaged();
    }

    void setFavorite(TvEntity tvEntity) {
        final boolean newState = !tvEntity.isFavorited();
        filmRepository.setTvFavorite(tvEntity, newState);
    }
}
