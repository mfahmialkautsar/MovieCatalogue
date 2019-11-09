package ga.softogi.moviecatalogue.ui.detail;

import androidx.lifecycle.ViewModel;

import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.utils.DataDummy;

public class DetailFilmViewModel extends ViewModel {
    private FilmEntity mFilm;
    private String filmTitle;

    public FilmEntity getFilm() {
        //mengambil movie
        for (int i = 0; i < DataDummy.generateDummyMovie().size(); i++) {
            FilmEntity filmEntity = DataDummy.generateDummyMovie().get(i);
            if (filmEntity.getTitle().equals(filmTitle)) {
                mFilm = filmEntity;
            }
        }

        //mengambil tv show
        for (int i = 0; i < DataDummy.generateDummyTv().size(); i++) {
            FilmEntity filmEntity = DataDummy.generateDummyTv().get(i);
            if (filmEntity.getTitle().equals(filmTitle)) {
                mFilm = filmEntity;
            }
        }
        return mFilm;
    }

    //set judul tv show untuk diambil di method getFilm()
    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }
}
