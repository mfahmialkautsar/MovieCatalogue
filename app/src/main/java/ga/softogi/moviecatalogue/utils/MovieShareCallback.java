package ga.softogi.moviecatalogue.utils;

import ga.softogi.moviecatalogue.data.source.local.entity.MovieEntity;

public interface MovieShareCallback {
    void onShareClick(MovieEntity movieEntity);
}
