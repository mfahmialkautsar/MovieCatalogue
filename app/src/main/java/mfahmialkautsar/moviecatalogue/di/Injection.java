package mfahmialkautsar.moviecatalogue.di;

import android.app.Application;

import mfahmialkautsar.moviecatalogue.data.source.FilmRepository;
import mfahmialkautsar.moviecatalogue.data.source.local.LocalRepository;
import mfahmialkautsar.moviecatalogue.data.source.local.room.FilmDatabase;
import mfahmialkautsar.moviecatalogue.data.source.remote.RemoteRepository;
import mfahmialkautsar.moviecatalogue.utils.AppExecutors;

public class Injection {
    public static FilmRepository provideRepository(Application application) {

        FilmDatabase database = FilmDatabase.getInstance(application);

        LocalRepository localRepository = LocalRepository.getInstance(database.filmDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance();
        AppExecutors appExecutors = new AppExecutors();

        return FilmRepository.getInstance(localRepository, remoteRepository, appExecutors);
    }

}
