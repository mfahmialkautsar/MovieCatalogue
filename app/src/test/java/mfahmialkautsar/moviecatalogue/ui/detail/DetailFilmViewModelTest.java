package mfahmialkautsar.moviecatalogue.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import mfahmialkautsar.moviecatalogue.data.source.FilmRepository;
import mfahmialkautsar.moviecatalogue.data.source.local.entity.MovieEntity;
import mfahmialkautsar.moviecatalogue.data.source.local.entity.TvEntity;
import mfahmialkautsar.moviecatalogue.utils.FakeDataDummy;
import mfahmialkautsar.moviecatalogue.vo.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailFilmViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailFilmViewModel viewModel;
    private FilmRepository filmRepository = mock(FilmRepository.class);
    private MovieEntity dummyMovie = FakeDataDummy.generateDummyMovie().get(0);
    private TvEntity dummyTv = FakeDataDummy.generateDummyTv().get(0);
    private String movieId = dummyMovie.getMovieId();
    private String tvId = dummyTv.getTvId();

    @Before
    public void setUp() {
        viewModel = new DetailFilmViewModel(filmRepository);
        viewModel.setMovieId(movieId);
        viewModel.setTvId(tvId);
    }

    @Test
    public void getDetailMovie() {
        Resource<MovieEntity> resource = Resource.success(FakeDataDummy.generateDummyDetailMovie(dummyMovie, true));
        MutableLiveData<Resource<MovieEntity>> movieEntities = new MutableLiveData<>();
        movieEntities.setValue(resource);

        when(filmRepository.getMovieById(movieId)).thenReturn(movieEntities);

        //noinspection unchecked
        Observer<Resource<MovieEntity>> observer = mock(Observer.class);
        viewModel.liveMovie.observeForever(observer);

        verify(observer).onChanged(resource);
    }

    @Test
    public void getDetailTv() {
        Resource<TvEntity> resource = Resource.success(FakeDataDummy.generateDummyDetailTv(dummyTv, true));
        MutableLiveData<Resource<TvEntity>> tvEntities = new MutableLiveData<>();
        tvEntities.setValue(resource);

        when(filmRepository.getTvById(tvId)).thenReturn(tvEntities);

        //noinspection unchecked
        Observer<Resource<TvEntity>> observer = mock(Observer.class);
        viewModel.liveTv.observeForever(observer);

        verify(observer).onChanged(resource);
    }
}