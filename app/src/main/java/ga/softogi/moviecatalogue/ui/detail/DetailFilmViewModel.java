package ga.softogi.moviecatalogue.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.data.source.FilmRepository;

public class DetailFilmViewModel extends ViewModel {
    private FilmEntity mFilm;
    private String movieTitle;
    private String tvTitle;
    private FilmRepository filmRepository;

    public DetailFilmViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    public LiveData<FilmEntity> getMovie() {
        //mengambil movie
        return filmRepository.getDetailMovie(movieTitle);
    }

    public LiveData<FilmEntity> getTv() {
        //mengambil tv
        return filmRepository.getDetailTv(tvTitle);
    }

    //set judul movie untuk diambil di method getMovie()
    void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    //set judul tv show untuk diambil di method getTv()
    void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }
}
