package ga.softogi.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;

import java.util.List;

import ga.softogi.moviecatalogue.data.FilmEntity;

public interface FilmDataSource {

    LiveData<List<FilmEntity>> getAllMovies();

    LiveData<List<FilmEntity>> getAllTvs();

    LiveData<FilmEntity> getDetailMovie(String filmTitle);

    LiveData<FilmEntity> getDetailTv(String filmTitle);
}
