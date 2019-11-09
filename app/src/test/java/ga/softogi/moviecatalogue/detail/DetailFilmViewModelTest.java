package ga.softogi.moviecatalogue.detail;

import org.junit.Before;
import org.junit.Test;

import ga.softogi.moviecatalogue.R;
import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.ui.detail.DetailFilmViewModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DetailFilmViewModelTest {
    private DetailFilmViewModel viewModel;
    private FilmEntity dummyFilm;

    @Before
    public void setUp() {
        viewModel = new DetailFilmViewModel();
        dummyFilm = new FilmEntity("Fantastic Beasts: The Crimes of Grindelwald",
                "The second installment of the \"Fantastic Beasts\" series featuring the adventures of Magizoologist Newt Scamander.",
                "November 14, 2018",
                "Drama/Fantasy",
                "2h 13m",
                R.drawable.poster_crimes,
                R.drawable.backdrop_fbeast);
    }

    @Test
    public void getContent() {
        viewModel.setFilmTitle(dummyFilm.getTitle());
        FilmEntity filmEntity = viewModel.getFilm();
        assertNotNull(filmEntity);
        assertEquals(dummyFilm.getTitle(), filmEntity.getTitle());
        assertEquals(dummyFilm.getOverview(), filmEntity.getOverview());
        assertEquals(dummyFilm.getRelease(), filmEntity.getRelease());
        assertEquals(dummyFilm.getGenre(), filmEntity.getGenre());
        assertEquals(dummyFilm.getRunningTime(), filmEntity.getRunningTime());
        assertEquals(dummyFilm.getPosterPath(), filmEntity.getPosterPath());
        assertEquals(dummyFilm.getBackdropPath(), filmEntity.getBackdropPath());
    }
}