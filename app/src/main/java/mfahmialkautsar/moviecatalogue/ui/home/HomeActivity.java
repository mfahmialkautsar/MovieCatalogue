package mfahmialkautsar.moviecatalogue.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import mfahmialkautsar.moviecatalogue.R;
import mfahmialkautsar.moviecatalogue.ui.favorite.FavoriteActivity;
import mfahmialkautsar.moviecatalogue.utils.NightModePreference;
import mfahmialkautsar.moviecatalogue.utils.SectionsPagerAdapter;

public class HomeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private NightModePreference nightModePreference;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        nightModePreference = NightModePreference.getInstance(this);
        if (nightModePreference.getNightModeState()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        setNightMode(nightModePreference.getNightModeState());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Intent intent = new Intent(HomeActivity.this, FavoriteActivity.class);
                this.startActivity(intent);
                break;
            case R.id.action_theme:
                if (nightModePreference.getNightModeState()) {
                    nightModePreference.setNightModeState(false);
                } else {
                    nightModePreference.setNightModeState(true);
                }
                recreate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNightMode(boolean night) {
        if (menu == null) return;
        MenuItem nightMenu = menu.findItem(R.id.action_theme);
        if (night) {
            nightMenu.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_moon_white_24dp));
        } else {
            nightMenu.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_wb_sunny_white_24dp));
        }
    }
}