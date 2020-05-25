package mfahmialkautsar.moviecatalogue.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.ArrayList;
import java.util.List;

import mfahmialkautsar.moviecatalogue.data.source.local.LocalRepository;
import mfahmialkautsar.moviecatalogue.data.source.local.entity.MovieEntity;
import mfahmialkautsar.moviecatalogue.data.source.local.entity.TvEntity;
import mfahmialkautsar.moviecatalogue.data.source.remote.ApiResponse;
import mfahmialkautsar.moviecatalogue.data.source.remote.RemoteRepository;
import mfahmialkautsar.moviecatalogue.data.source.remote.response.FilmResponse;
import mfahmialkautsar.moviecatalogue.utils.AppExecutors;
import mfahmialkautsar.moviecatalogue.vo.Resource;

public class FilmRepository implements FilmDataSource {

    private static volatile FilmRepository INSTANCE = null;
    private final LocalRepository localRepository;
    private final AppExecutors appExecutors;

    private final RemoteRepository remoteRepository;

    private FilmRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository, AppExecutors appExecutors) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.appExecutors = appExecutors;
    }

    public static FilmRepository getInstance(LocalRepository localRepository, RemoteRepository remoteData, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (FilmRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FilmRepository(localRepository, remoteData, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getAllMovies() {
        return new NetworkBoundResource<List<MovieEntity>, List<FilmResponse>>(appExecutors) {
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {
                return localRepository.getAllMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<FilmResponse>>> createCall() {
                return remoteRepository.getAllMoviesAsLiveData();
            }

            @Override
            protected void saveCallResult(List<FilmResponse> filmResponses) {
                List<MovieEntity> filmEntities = new ArrayList<>();

                for (FilmResponse filmResponse : filmResponses) {

                    filmEntities.add(new MovieEntity(filmResponse.getId(),
                            filmResponse.getTitle(),
                            filmResponse.getOverview(),
                            filmResponse.getRelease(),
                            filmResponse.getGenre(),
                            filmResponse.getRunningTime(),
                            filmResponse.getRating(),
                            filmResponse.getPosterPath(),
                            filmResponse.getBackdropPath(),
                            null));
                }

                localRepository.insertMovies(filmEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TvEntity>>> getAllTvs() {
        return new NetworkBoundResource<List<TvEntity>, List<FilmResponse>>(appExecutors) {
            @Override
            protected LiveData<List<TvEntity>> loadFromDb() {
                return localRepository.getAllTvs();
            }

            @Override
            protected Boolean shouldFetch(List<TvEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<FilmResponse>>> createCall() {
                return remoteRepository.getAllTvsAsLiveData();
            }

            @Override
            protected void saveCallResult(List<FilmResponse> tvResponses) {
                List<TvEntity> tvEntities = new ArrayList<>();

                for (FilmResponse tvResponse : tvResponses) {
                    tvEntities.add(new TvEntity(tvResponse.getId(),
                            tvResponse.getTitle(),
                            tvResponse.getOverview(),
                            tvResponse.getRelease(),
                            tvResponse.getGenre(),
                            tvResponse.getRunningTime(),
                            tvResponse.getRating(),
                            tvResponse.getPosterPath(),
                            tvResponse.getBackdropPath(),
                            null));
                }

                localRepository.insertTvs(tvEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getMovieById(final String movieId) {
        return new NetworkBoundResource<MovieEntity, FilmResponse>(appExecutors) {
            @Override
            protected LiveData<MovieEntity> loadFromDb() {
                return localRepository.getMovieById(movieId);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity movie) {
                return (movie == null);
            }

            @Override
            protected LiveData<ApiResponse<FilmResponse>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(FilmResponse data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvEntity>> getTvById(final String tvId) {
        return new NetworkBoundResource<TvEntity, FilmResponse>(appExecutors) {
            @Override
            protected LiveData<TvEntity> loadFromDb() {
                return localRepository.getTvById(tvId);
            }

            @Override
            protected Boolean shouldFetch(TvEntity tv) {
                return (tv == null);
            }

            @Override
            protected LiveData<ApiResponse<FilmResponse>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(FilmResponse data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getFavoritedMovieAsPaged() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<FilmResponse>>(appExecutors) {
            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDb() {
                return new LivePagedListBuilder<>(localRepository.getFavoritedMoviesAsPaged(), 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<FilmResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<FilmResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TvEntity>>> getFavoritedTvAsPaged() {
        return new NetworkBoundResource<PagedList<TvEntity>, List<FilmResponse>>(appExecutors) {
            @Override
            protected LiveData<PagedList<TvEntity>> loadFromDb() {
                return new LivePagedListBuilder<>(localRepository.getFavoritedTvsAsPaged(), 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<FilmResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<FilmResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public void setMovieFavorite(MovieEntity movie, boolean state) {

        Runnable runnable = () -> localRepository.setMovieFavorite(movie, state);

        appExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void setTvFavorite(TvEntity tv, boolean state) {

        Runnable runnable = () -> localRepository.setTvFavorite(tv, state);

        appExecutors.getDiskIO().execute(runnable);
    }
}
