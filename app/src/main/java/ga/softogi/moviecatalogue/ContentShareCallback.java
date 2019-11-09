package ga.softogi.moviecatalogue;

import ga.softogi.moviecatalogue.data.FilmEntity;

public interface ContentShareCallback {
    void onShareClick(FilmEntity filmEntity);
}
