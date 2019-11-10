package ga.softogi.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.data.source.remote.RemoteRepository;
import ga.softogi.moviecatalogue.data.source.response.FilmResponse;

public class FakeFilmRepository implements FilmDataSource {

    private final RemoteRepository remoteRepository;

    FakeFilmRepository(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    public LiveData<List<FilmEntity>> getAllMovies() {
        MutableLiveData<List<FilmEntity>> filmResults = new MutableLiveData<>();

        remoteRepository.getAllMovies(filmResponses -> {
            ArrayList<FilmEntity> movieList = new ArrayList<>();
            for (int i = 0; i < filmResponses.size(); i++) {
                FilmResponse response = filmResponses.get(i);
                FilmEntity movie = new FilmEntity(response.getTitle(),
                        response.getOverview(),
                        response.getRelease(),
                        response.getGenre(),
                        response.getRunningTime(),
                        response.getPosterPath(),
                        response.getBackdropPath());

                movieList.add(movie);
            }
            filmResults.setValue(movieList);
        });
        return filmResults;
    }

    @Override
    public LiveData<List<FilmEntity>> getAllTvs() {
        MutableLiveData<List<FilmEntity>> filmResults = new MutableLiveData<>();

        remoteRepository.getAllTvs(filmResponses -> {
            ArrayList<FilmEntity> movieList = new ArrayList<>();
            for (int i = 0; i < filmResponses.size(); i++) {
                FilmResponse response = filmResponses.get(i);
                FilmEntity movie = new FilmEntity(response.getTitle(),
                        response.getOverview(),
                        response.getRelease(),
                        response.getGenre(),
                        response.getRunningTime(),
                        response.getPosterPath(),
                        response.getBackdropPath());

                movieList.add(movie);
            }
            filmResults.setValue(movieList);
        });
        return filmResults;
    }

    @Override
    public LiveData<FilmEntity> getDetailMovie(final String movieTitle) {
        MutableLiveData<FilmEntity> tvResults = new MutableLiveData<>();

        remoteRepository.getAllMovies(filmResponses -> {
            for (int i = 0; i < filmResponses.size(); i++) {
                FilmResponse response = filmResponses.get(i);
                if (response.getTitle().equals(movieTitle)) {
                    FilmEntity movie = new FilmEntity(response.getTitle(),
                            response.getOverview(),
                            response.getRelease(),
                            response.getGenre(),
                            response.getRunningTime(),
                            response.getPosterPath(),
                            response.getBackdropPath());

                    tvResults.postValue(movie);
                }
            }
        });
        return tvResults;
    }

    @Override
    public LiveData<FilmEntity> getDetailTv(final String tvTitle) {
        MutableLiveData<FilmEntity> movieResults = new MutableLiveData<>();

        remoteRepository.getAllTvs(filmResponses -> {
            for (int i = 0; i < filmResponses.size(); i++) {
                FilmResponse response = filmResponses.get(i);
                if (response.getTitle().equals(tvTitle)) {
                    FilmEntity tv = new FilmEntity(response.getTitle(),
                            response.getOverview(),
                            response.getRelease(),
                            response.getGenre(),
                            response.getRunningTime(),
                            response.getPosterPath(),
                            response.getBackdropPath());

                    movieResults.postValue(tv);
                }
            }
        });
        return movieResults;
    }
}
