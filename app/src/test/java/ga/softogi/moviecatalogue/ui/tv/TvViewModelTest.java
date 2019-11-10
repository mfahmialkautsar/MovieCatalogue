package ga.softogi.moviecatalogue.ui.tv;

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

public class TvViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TvViewModel viewModel;
    private FilmRepository filmRepository = mock(FilmRepository.class);

    @Before
    public void setUp() {
        viewModel = new TvViewModel(filmRepository);
    }

    @Test
    public void getTvs() {
        ArrayList<FilmEntity> dummyTv = FakeDataDummy.generateDummyTv();

        MutableLiveData<List<FilmEntity>> tvs = new MutableLiveData<>();
        tvs.setValue(dummyTv);

        when(filmRepository.getAllTvs()).thenReturn(tvs);

        Observer<List<FilmEntity>> observer = mock(Observer.class);

        viewModel.getTvs().observeForever(observer);

        verify(observer).onChanged(dummyTv);
    }
}