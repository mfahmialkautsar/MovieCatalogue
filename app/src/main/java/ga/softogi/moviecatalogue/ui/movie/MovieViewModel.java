package ga.softogi.moviecatalogue.ui.movie;

import androidx.lifecycle.ViewModel;

import java.util.List;

import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.utils.DataDummy;

public class MovieViewModel extends ViewModel {

    public List<FilmEntity> getMovies() {
        return DataDummy.generateDummyMovie();
    }
}
