package ga.softogi.moviecatalogue.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import ga.softogi.moviecatalogue.data.source.FilmRepository;
import ga.softogi.moviecatalogue.data.source.local.entity.MovieEntity;
import ga.softogi.moviecatalogue.utils.FakeDataDummy;
import ga.softogi.moviecatalogue.vo.Resource;

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
        Resource<List<MovieEntity>> dummyMovie = Resource.success(FakeDataDummy.generateDummyMovie());

        MutableLiveData<Resource<List<MovieEntity>>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);

        when(filmRepository.getAllMovies()).thenReturn(movies);

        //noinspection unchecked
        Observer<Resource<List<MovieEntity>>> observer = mock(Observer.class);

        viewModel.getMovies().observeForever(observer);

        verify(observer).onChanged(dummyMovie);
    }
}