package ga.softogi.moviecatalogue.ui.tv;

import androidx.lifecycle.ViewModel;

import java.util.List;

import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.utils.DataDummy;

public class TvViewModel extends ViewModel {

    public List<FilmEntity> getTvs() {
        return DataDummy.generateDummyTv();
    }
}
