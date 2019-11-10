package ga.softogi.moviecatalogue.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ga.softogi.moviecatalogue.data.source.FilmRepository;
import ga.softogi.moviecatalogue.data.source.remote.RemoteRepository;
import ga.softogi.moviecatalogue.ui.detail.DetailFilmViewModel;
import ga.softogi.moviecatalogue.ui.movie.MovieViewModel;
import ga.softogi.moviecatalogue.ui.tv.TvViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final FilmRepository mFilmRepository;

    private ViewModelFactory(FilmRepository mFilmRepository) {
        this.mFilmRepository = mFilmRepository;
    }

    public static ViewModelFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(FilmRepository.getInstance(RemoteRepository.getInstance()));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            //noinspection unchecked
            return (T) new MovieViewModel(mFilmRepository);
        } else if (modelClass.isAssignableFrom(TvViewModel.class)) {
            //noinspection unchecked
            return (T) new TvViewModel(mFilmRepository);
        } else if (modelClass.isAssignableFrom(DetailFilmViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailFilmViewModel(mFilmRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel Class: " + modelClass.getName());
    }
}
