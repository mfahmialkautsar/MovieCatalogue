package ga.softogi.moviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.data.source.remote.RemoteRepository;
import ga.softogi.moviecatalogue.data.source.response.FilmResponse;
import ga.softogi.moviecatalogue.utils.FakeDataDummy;
import ga.softogi.moviecatalogue.utils.LiveDataTestUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FilmRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remote = Mockito.mock(RemoteRepository.class);
    private FakeFilmRepository filmRepository = new FakeFilmRepository(remote);

    private ArrayList<FilmResponse> movieResponses = FakeDataDummy.generateRemoteDummyMovie();
    private String movieTitle = movieResponses.get(0).getTitle();

    private ArrayList<FilmResponse> tvResponse = FakeDataDummy.generateRemoteDummyTv();
    private String tvTitle = tvResponse.get(0).getTitle();

    @Test
    public void getAllMovies() {

        doAnswer(invocation -> {
            ((RemoteRepository.LoadFilmsCallback) invocation.getArguments()[0])
                    .onAllFilmsReceived(movieResponses);
            return null;
        }).when(remote).getAllMovies(any(RemoteRepository.LoadFilmsCallback.class));

        List<FilmEntity> result = LiveDataTestUtil.getValue(filmRepository.getAllMovies());

        verify(remote, times(1)).getAllMovies(any(RemoteRepository.LoadFilmsCallback.class));

        assertNotNull(result);
        assertEquals(movieResponses.size(), result.size());
    }

    @Test
    public void getAllTvs() {

        doAnswer(invocation -> {
            ((RemoteRepository.LoadFilmsCallback) invocation.getArguments()[0])
                    .onAllFilmsReceived(tvResponse);
            return null;
        }).when(remote).getAllTvs(any(RemoteRepository.LoadFilmsCallback.class));

        List<FilmEntity> result = LiveDataTestUtil.getValue(filmRepository.getAllTvs());

        verify(remote, times(1)).getAllTvs(any(RemoteRepository.LoadFilmsCallback.class));

        assertNotNull(result);
        assertEquals(tvResponse.size(), result.size());
    }

    @Test
    public void getDetailMovie() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadFilmsCallback) invocation.getArguments()[0])
                    .onAllFilmsReceived(movieResponses);
            return null;
        }).when(remote).getAllMovies(any(RemoteRepository.LoadFilmsCallback.class));

        FilmEntity result = LiveDataTestUtil.getValue(filmRepository.getDetailMovie(movieTitle));

        verify(remote, times(1)).getAllMovies(any(RemoteRepository.LoadFilmsCallback.class));
        assertNotNull(result);
        assertNotNull(result.getTitle());
        assertEquals(movieResponses.get(0).getTitle(), result.getTitle());
    }

    @Test
    public void getDetailTv() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadFilmsCallback) invocation.getArguments()[0])
                    .onAllFilmsReceived(tvResponse);
            return null;
        }).when(remote).getAllTvs(any(RemoteRepository.LoadFilmsCallback.class));

        FilmEntity result = LiveDataTestUtil.getValue(filmRepository.getDetailTv(tvTitle));

        verify(remote, times(1)).getAllTvs(any(RemoteRepository.LoadFilmsCallback.class));
        assertNotNull(result);
        assertNotNull(result.getTitle());
        assertEquals(tvResponse.get(0).getTitle(), result.getTitle());
    }
}