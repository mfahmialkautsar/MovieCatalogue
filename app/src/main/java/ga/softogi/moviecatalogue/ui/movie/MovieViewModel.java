package ga.softogi.moviecatalogue.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.data.source.FilmRepository;

public class MovieViewModel extends ViewModel {
    private FilmRepository filmRepository;

    public MovieViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    LiveData<List<FilmEntity>> getMovies() {
        return filmRepository.getAllMovies();
    }
}
