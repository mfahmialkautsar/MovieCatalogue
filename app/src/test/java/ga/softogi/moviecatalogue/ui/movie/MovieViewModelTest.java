package ga.softogi.moviecatalogue.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.data.source.FilmRepository;
import ga.softogi.moviecatalogue.utils.FakeDataDummy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieViewModel viewModel;
    private FilmRepository filmRepository = mock(FilmRepository.class);

    @Before
    public void setUp() {
        viewModel = new MovieViewModel(filmRepository);
    }

    @Test
    public void getMovies() {
        ArrayList<FilmEntity> dummyMovie = FakeDataDummy.generateDummyMovie();

        MutableLiveData<List<FilmEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);

        when(filmRepository.getAllMovies()).thenReturn(movies);

        Observer<List<FilmEntity>> observer = mock(Observer.class);

        viewModel.getMovies().observeForever(observer);

        verify(observer).onChanged(dummyMovie);
    }
}