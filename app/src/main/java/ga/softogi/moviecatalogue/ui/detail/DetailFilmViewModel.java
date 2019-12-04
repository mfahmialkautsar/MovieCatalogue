package ga.softogi.moviecatalogue.ui.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import ga.softogi.moviecatalogue.data.source.FilmRepository;
import ga.softogi.moviecatalogue.data.source.local.entity.MovieEntity;
import ga.softogi.moviecatalogue.data.source.local.entity.TvEntity;
import ga.softogi.moviecatalogue.vo.Resource;

public class DetailFilmViewModel extends ViewModel {
    private FilmRepository filmRepository;
    private MutableLiveData<String> movieId = new MutableLiveData<>();
    LiveData<Resource<MovieEntity>> liveMovie = Transformations.switchMap(movieId,
            id -> filmRepository.getMovieById(id));

    private MutableLiveData<String> tvId = new MutableLiveData<>();
    LiveData<Resource<TvEntity>> liveTv = Transformations.switchMap(tvId,
            id -> filmRepository.getTvById(id));

    public DetailFilmViewModel(FilmRepository mFilmRepository) {
        this.filmRepository = mFilmRepository;
    }

    //set id liveMovie untuk diambil di method getMovie()
    void setMovieId(String movieId) {
        this.movieId.setValue(movieId);
    }

    //set id liveTv show untuk diambil di method getTv()
    void setTvId(String tvId) {
        this.tvId.setValue(tvId);
    }

    void setMovieFavorite() {
        if (liveMovie.getValue() != null) {
            MovieEntity movie = liveMovie.getValue().data;

            if (movie != null) {
                Log.d("FAV", "MOVIE");
                final boolean newState = !movie.isFavorited();
                filmRepository.setMovieFavorite(movie, newState);
            }
        }
    }

    void setTvFavorite() {
        if (liveTv.getValue() != null) {
            TvEntity tv = liveTv.getValue().data;

            if (tv != null) {
                Log.d("FAV", "TV");
                final boolean newState = !tv.isFavorited();
                filmRepository.setTvFavorite(tv, newState);
            }
        }
    }
}
