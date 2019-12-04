package ga.softogi.moviecatalogue.utils;

import ga.softogi.moviecatalogue.data.source.local.entity.TvEntity;

public interface TvShareCallback {
    void onShareClick(TvEntity tvEntity);
}
