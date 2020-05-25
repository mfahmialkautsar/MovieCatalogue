package mfahmialkautsar.moviecatalogue.data.source.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mfahmialkautsar.moviecatalogue.DataDummy;
import mfahmialkautsar.moviecatalogue.data.source.remote.response.FilmResponse;

public class RemoteRepository {

    private static RemoteRepository INSTANCE = null;

    public static RemoteRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository();
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<FilmResponse>>> getAllMoviesAsLiveData() {
        MutableLiveData<ApiResponse<List<FilmResponse>>> resultMovie = new MutableLiveData<>();

        resultMovie.setValue(ApiResponse.success(DataDummy.generateDummyMovie()));
        return resultMovie;
    }

    public LiveData<ApiResponse<List<FilmResponse>>> getAllTvsAsLiveData() {
        MutableLiveData<ApiResponse<List<FilmResponse>>> resultTv = new MutableLiveData<>();

        resultTv.setValue(ApiResponse.success(DataDummy.generateDummyTv()));
        return resultTv;
    }

    public LiveData<ApiResponse<FilmResponse>> getMovieById(String id) {
        MutableLiveData<ApiResponse<FilmResponse>> result = new MutableLiveData<>();

        result.setValue(ApiResponse.success(DataDummy.generateDummyMovie().get(Integer.parseInt(id.substring(1)) - 1)));
        return result;
    }
}
