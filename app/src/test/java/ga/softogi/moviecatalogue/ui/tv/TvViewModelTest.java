package ga.softogi.moviecatalogue.ui.tv;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import ga.softogi.moviecatalogue.data.source.FilmRepository;
import ga.softogi.moviecatalogue.data.source.local.entity.TvEntity;
import ga.softogi.moviecatalogue.utils.FakeDataDummy;
import ga.softogi.moviecatalogue.vo.Resource;

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
        Resource<List<TvEntity>> dummyTv = Resource.success(FakeDataDummy.generateDummyTv());

        MutableLiveData<Resource<List<TvEntity>>> tvs = new MutableLiveData<>();
        tvs.setValue(dummyTv);

        when(filmRepository.getAllTvs()).thenReturn(tvs);

        //noinspection unchecked
        Observer<Resource<List<TvEntity>>> observer = mock(Observer.class);

        viewModel.getTvs().observeForever(observer);

        verify(observer).onChanged(dummyTv);
    }
}