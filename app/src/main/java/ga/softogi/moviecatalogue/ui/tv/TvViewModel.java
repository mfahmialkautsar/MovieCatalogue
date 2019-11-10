package ga.softogi.moviecatalogue.ui.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.data.source.FilmRepository;

public class TvViewModel extends ViewModel {
    private FilmRepository filmRepository;

    public TvViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    LiveData<List<FilmEntity>> getTvs() {
        return filmRepository.getAllTvs();
    }
}
