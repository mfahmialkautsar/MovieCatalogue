package mfahmialkautsar.moviecatalogue.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import mfahmialkautsar.moviecatalogue.data.source.FilmRepository;
import mfahmialkautsar.moviecatalogue.di.Injection;
import mfahmialkautsar.moviecatalogue.ui.detail.DetailFilmViewModel;
import mfahmialkautsar.moviecatalogue.ui.favorite.movie.FavMovieViewModel;
import mfahmialkautsar.moviecatalogue.ui.favorite.tv.FavTvViewModel;
import mfahmialkautsar.moviecatalogue.ui.movie.MovieViewModel;
import mfahmialkautsar.moviecatalogue.ui.tv.TvViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final FilmRepository mFilmRepository;

    private ViewModelFactory(FilmRepository mFilmRepository) {
        this.mFilmRepository = mFilmRepository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
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
        } else if (modelClass.isAssignableFrom(FavMovieViewModel.class)) {
            //noinspection unchecked
            return (T) new FavMovieViewModel(mFilmRepository);
        } else if (modelClass.isAssignableFrom(FavTvViewModel.class)) {
            //noinspection unchecked
            return (T) new FavTvViewModel(mFilmRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
