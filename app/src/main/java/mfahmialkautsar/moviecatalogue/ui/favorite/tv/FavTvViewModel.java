package mfahmialkautsar.moviecatalogue.ui.favorite.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import mfahmialkautsar.moviecatalogue.data.source.FilmRepository;
import mfahmialkautsar.moviecatalogue.data.source.local.entity.TvEntity;
import mfahmialkautsar.moviecatalogue.vo.Resource;

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
