package mfahmialkautsar.moviecatalogue.ui.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import mfahmialkautsar.moviecatalogue.data.source.FilmRepository;
import mfahmialkautsar.moviecatalogue.data.source.local.entity.TvEntity;
import mfahmialkautsar.moviecatalogue.vo.Resource;

public class TvViewModel extends ViewModel {
    private FilmRepository filmRepository;

    public TvViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    LiveData<Resource<List<TvEntity>>> getTvs() {
        return filmRepository.getAllTvs();
    }
}
