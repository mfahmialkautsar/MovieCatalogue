package ga.softogi.moviecatalogue.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class NightModePreference {
    private static volatile NightModePreference INSTANCE = null;
    private SharedPreferences preferences;

    private NightModePreference(Context context) {
        preferences = context.getSharedPreferences("night_mode", Context.MODE_PRIVATE);
    }

    public static NightModePreference getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (NightModePreference.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NightModePreference(context);
                }
            }
        }
        return INSTANCE;
    }

    // mengambil state night mode
    public Boolean getNightModeState() {
        return preferences.getBoolean("NightMode", false);
    }

    // menyimpan state night mode
    public void setNightModeState(Boolean state) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("NightMode", state);
        editor.apply();
    }
}
