package ga.softogi.moviecatalogue.di;

import android.app.Application;

import ga.softogi.moviecatalogue.data.source.FilmRepository;
import ga.softogi.moviecatalogue.data.source.local.LocalRepository;
import ga.softogi.moviecatalogue.data.source.local.room.FilmDatabase;
import ga.softogi.moviecatalogue.data.source.remote.RemoteRepository;
import ga.softogi.moviecatalogue.utils.AppExecutors;

public class Injection {
    public static FilmRepository provideRepository(Application application) {

        FilmDatabase database = FilmDatabase.getInstance(application);

        LocalRepository localRepository = LocalRepository.getInstance(database.filmDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance();
        AppExecutors appExecutors = new AppExecutors();

        return FilmRepository.getInstance(localRepository, remoteRepository, appExecutors);
    }

}
