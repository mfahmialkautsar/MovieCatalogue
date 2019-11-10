package ga.softogi.moviecatalogue.data.source.remote;

import java.util.List;

import ga.softogi.moviecatalogue.data.source.response.FilmResponse;
import ga.softogi.moviecatalogue.utils.DataDummy;

public class RemoteRepository {

    private static RemoteRepository INSTANCE = null;

    public static RemoteRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository();
        }
        return INSTANCE;
    }

    public void getAllMovies(LoadFilmsCallback callback) {
        callback.onAllFilmsReceived(DataDummy.generateDummyMovie());
    }

    public void getAllTvs(LoadFilmsCallback callback) {
        callback.onAllFilmsReceived(DataDummy.generateDummyTv());
    }

    public interface LoadFilmsCallback {
        void onAllFilmsReceived(List<FilmResponse> filmResponses);
    }
}
