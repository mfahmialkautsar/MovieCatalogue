package ga.softogi.moviecatalogue.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.data.source.FilmRepository;
import ga.softogi.moviecatalogue.utils.FakeDataDummy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailFilmViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailFilmViewModel viewModel;
    private FilmRepository filmRepository = mock(FilmRepository.class);
    private FilmEntity dummyMovie = FakeDataDummy.generateDummyMovie().get(0);
    private FilmEntity dummyTv = FakeDataDummy.generateDummyTv().get(0);
    private String movieTitle = dummyMovie.getTitle();
    private String tvTitle = dummyTv.getTitle();

    @Before
    public void setUp() {
        viewModel = new DetailFilmViewModel(filmRepository);
        viewModel.setMovieTitle(movieTitle);
        viewModel.setTvTitle(tvTitle);
    }

    @Test
    public void getDetailMovie() {
        MutableLiveData<FilmEntity> movieEntities = new MutableLiveData<>();
        movieEntities.setValue(dummyMovie);

        when(filmRepository.getDetailMovie(movieTitle)).thenReturn(movieEntities);

        Observer<FilmEntity> observer = mock(Observer.class);

        viewModel.getMovie().observeForever(observer);

        verify(observer).onChanged(dummyMovie);
    }

    @Test
    public void getDetailTv() {
        MutableLiveData<FilmEntity> tvEntities = new MutableLiveData<>();
        tvEntities.setValue(dummyTv);

        when(filmRepository.getDetailTv(tvTitle)).thenReturn(tvEntities);

        Observer<FilmEntity> observer = mock(Observer.class);

        viewModel.getTv().observeForever(observer);

        verify(observer).onChanged(dummyTv);
    }
}