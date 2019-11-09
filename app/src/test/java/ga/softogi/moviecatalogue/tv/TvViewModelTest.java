package ga.softogi.moviecatalogue.tv;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.ui.tv.TvViewModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TvViewModelTest {
    private TvViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new TvViewModel();
    }

    @Test
    public void getTvs() {
        List<FilmEntity> tvEntities = viewModel.getTvs();
        assertNotNull(tvEntities);
        assertEquals(10, tvEntities.size());
    }
}