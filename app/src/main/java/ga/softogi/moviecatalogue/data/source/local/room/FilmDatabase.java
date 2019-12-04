package ga.softogi.moviecatalogue.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ga.softogi.moviecatalogue.data.source.local.entity.MovieEntity;
import ga.softogi.moviecatalogue.data.source.local.entity.TvEntity;

@Database(entities = {MovieEntity.class, TvEntity.class},
        version = 1,
        exportSchema = false)
public abstract class FilmDatabase extends RoomDatabase {

    private static final Object sLock = new Object();
    private static FilmDatabase INSTANCE;

    public static FilmDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        FilmDatabase.class, "Film.db")
                        .build();
            }
        }
        return INSTANCE;
    }

    public abstract FilmDao filmDao();
}
