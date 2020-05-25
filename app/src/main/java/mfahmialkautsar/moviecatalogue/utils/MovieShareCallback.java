package mfahmialkautsar.moviecatalogue.utils;

import mfahmialkautsar.moviecatalogue.data.source.local.entity.MovieEntity;

public interface MovieShareCallback {
    void onShareClick(MovieEntity movieEntity);
}
