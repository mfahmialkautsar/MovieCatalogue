package ga.softogi.moviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ga.softogi.moviecatalogue.data.source.local.LocalRepository;
import ga.softogi.moviecatalogue.data.source.local.entity.MovieEntity;
import ga.softogi.moviecatalogue.data.source.local.entity.TvEntity;
import ga.softogi.moviecatalogue.data.source.remote.RemoteRepository;
import ga.softogi.moviecatalogue.data.source.remote.response.FilmResponse;
import ga.softogi.moviecatalogue.utils.FakeDataDummy;
import ga.softogi.moviecatalogue.utils.InstantAppExecutors;
import ga.softogi.moviecatalogue.utils.LiveDataTestUtil;
import ga.softogi.moviecatalogue.utils.PagedListUtil;
import ga.softogi.moviecatalogue.vo.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FilmRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LocalRepository local = mock(LocalRepository.class);
    private RemoteRepository remote = mock(RemoteRepository.class);
    private InstantAppExecutors instantAppExecutors = mock(InstantAppExecutors.class);
    private FakeFilmRepository filmRepository = new FakeFilmRepository(local, remote, instantAppExecutors);

    private ArrayList<FilmResponse> movieResponses = FakeDataDummy.generateRemoteDummyMovie();
    private List<MovieEntity> movieEntities = FakeDataDummy.generateDummyMovie();
    private String movieId = movieResponses.get(0).getId();

    private ArrayList<FilmResponse> tvResponse = FakeDataDummy.generateRemoteDummyTv();
    private List<TvEntity> tvEntities = FakeDataDummy.generateDummyTv();
    private String tvId = tvResponse.get(0).getId();

    @Test
    public void getAllMovies() {

        MutableLiveData<List<MovieEntity>> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(FakeDataDummy.generateDummyMovie());

        when(local.getAllMovies()).thenReturn(dummyMovie);

        Resource<List<MovieEntity>> result = LiveDataTestUtil.getValue(filmRepository.getAllMovies());

        verify(local).getAllMovies();
        assertNotNull(result.data);
        assertEquals(movieResponses.size(), result.data.size());
    }

    @Test
    public void getAllTvs() {

        MutableLiveData<List<TvEntity>> dummyTv = new MutableLiveData<>();
        dummyTv.setValue(FakeDataDummy.generateDummyTv());

        when(local.getAllTvs()).thenReturn(dummyTv);

        Resource<List<TvEntity>> result = LiveDataTestUtil.getValue(filmRepository.getAllTvs());

        verify(local).getAllTvs();
        assertNotNull(result.data);
        assertEquals(tvResponse.size(), result.data.size());
    }

    @Test
    public void getMovieById() {
        MutableLiveData<MovieEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(FakeDataDummy.generateDummyDetailMovie(FakeDataDummy.generateDummyMovie().get(0), false));

        when(local.getMovieById(movieId)).thenReturn(dummyEntity);

        Resource<MovieEntity> result = LiveDataTestUtil.getValue(filmRepository.getMovieById(movieId));

        verify(local).getMovieById(movieId);
        assertNotNull(result.data);
        assertNotNull(result.data.getTitle());
        assertEquals(movieResponses.get(0).getTitle(), result.data.getTitle());
    }

    @Test
    public void getTvById() {
        MutableLiveData<TvEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(FakeDataDummy.generateDummyDetailTv(FakeDataDummy.generateDummyTv().get(0), false));

        when(local.getTvById(tvId)).thenReturn(dummyEntity);

        Resource<TvEntity> result = LiveDataTestUtil.getValue(filmRepository.getTvById(tvId));

        verify(local).getTvById(tvId);
        assertNotNull(result.data);
        assertNotNull(result.data.getTitle());
        assertEquals(tvResponse.get(0).getTitle(), result.data.getTitle());
    }

    @Test
    public void getFavoritedMovies() {

        //noinspection unchecked
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(local.getFavoritedMoviesAsPaged()).thenReturn(dataSourceFactory);
        filmRepository.getFavoritedMovieAsPaged();
        Resource<PagedList<MovieEntity>> result = Resource.success(PagedListUtil.mockPagedList(movieEntities));

        verify(local).getFavoritedMoviesAsPaged();
        assertNotNull(result.data);
        assertEquals(movieEntities.size(), result.data.size());
    }

    @Test
    public void getFavoritedTvs() {

        //noinspection unchecked
        DataSource.Factory<Integer, TvEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(local.getFavoritedTvsAsPaged()).thenReturn(dataSourceFactory);
        filmRepository.getFavoritedTvAsPaged();
        Resource<PagedList<TvEntity>> result = Resource.success(PagedListUtil.mockPagedList(tvEntities));

        verify(local).getFavoritedTvsAsPaged();
        assertNotNull(result.data);
        assertEquals(tvEntities.size(), result.data.size());
    }
}