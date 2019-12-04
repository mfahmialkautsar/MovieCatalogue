package ga.softogi.moviecatalogue.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ga.softogi.moviecatalogue.data.source.FilmRepository;
import ga.softogi.moviecatalogue.data.source.local.entity.MovieEntity;
import ga.softogi.moviecatalogue.vo.Resource;

public class MovieViewModel extends ViewModel {
    private FilmRepository filmRepository;

    public MovieViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    LiveData<Resource<List<MovieEntity>>> getMovies() {
        return filmRepository.getAllMovies();
    }
}
