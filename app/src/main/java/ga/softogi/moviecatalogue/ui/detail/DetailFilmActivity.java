package ga.softogi.moviecatalogue.ui.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.request.RequestOptions;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ga.softogi.moviecatalogue.R;
import ga.softogi.moviecatalogue.data.source.local.entity.MovieEntity;
import ga.softogi.moviecatalogue.data.source.local.entity.TvEntity;
import ga.softogi.moviecatalogue.utils.GlideApp;
import ga.softogi.moviecatalogue.viewmodel.ViewModelFactory;

public class DetailFilmActivity extends AppCompatActivity {

    public static final String EXTRA_FILM = "extra_film";
    private TextView tvTitle;
    private TextView tvOverview;
    private TextView tvRelease;
    private TextView tvGenre;
    private TextView tvRuntime;
    private ImageView ivPoster;
    private ImageView ivBackdrop;
    private double rating;
    private TextView tvRating;
    private List<ImageView> ivStar;
    private ProgressBar progressBar;
    private boolean isMovieId;
    private boolean isTvId;

    private DetailFilmViewModel viewModel;
    private Menu menu;

    @NonNull
    private static DetailFilmViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(DetailFilmViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewModel = obtainViewModel(this);
        tvTitle = findViewById(R.id.tv_title);
        tvOverview = findViewById(R.id.tv_overview);
        tvRelease = findViewById(R.id.tv_release);
        tvGenre = findViewById(R.id.tv_genre);
        tvRuntime = findViewById(R.id.tv_runtime);
        tvRating = findViewById(R.id.tv_rating);
        ivPoster = findViewById(R.id.iv_poster);
        ivBackdrop = findViewById(R.id.iv_backdrop);
        progressBar = findViewById(R.id.progress_bar);

        ImageView ivStar1 = findViewById(R.id.iv_star1);
        ImageView ivStar2 = findViewById(R.id.iv_star2);
        ImageView ivStar3 = findViewById(R.id.iv_star3);
        ImageView ivStar4 = findViewById(R.id.iv_star4);
        ImageView ivStar5 = findViewById(R.id.iv_star5);

        ivStar = new ArrayList<>();
        ivStar.add(ivStar1);
        ivStar.add(ivStar2);
        ivStar.add(ivStar3);
        ivStar.add(ivStar4);
        ivStar.add(ivStar5);

        String filmId = getIntent().getStringExtra(EXTRA_FILM);
        if (filmId != null) {
            isMovieId = Objects.equals(filmId, "m" + filmId.substring(1));
            isTvId = Objects.equals(filmId, "t" + filmId.substring(1));
            if (isMovieId) {
                viewModel.setMovieId(filmId);
            } else if (isTvId) {
                viewModel.setTvId(filmId);
            }
        }

        if (filmId != null && isMovieId) {
            //detail untuk liveMovie
            viewModel.liveMovie.observe(this, detailMovie -> {
                if (detailMovie != null) {
                    switch (detailMovie.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            Log.d("Movie", "LOADING");
                            break;
                        case SUCCESS:
                            if (detailMovie.data != null) {
                                progressBar.setVisibility(View.GONE);
                                populateMovie(detailMovie.data);
                                Log.d("Movie", "SUCCESS");

                            }
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Log.d("Movie", "ERROR");
                            break;
                    }
                }
            });
        } else if (filmId != null && isTvId) {
            //detail untuk liveTv
            viewModel.liveTv.observe(this, detailTv -> {
                if (detailTv != null) {
                    switch (detailTv.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            if (detailTv.data != null) {
                                progressBar.setVisibility(View.GONE);
                                populateTv(detailTv.data);
                            }
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            break;
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        if (isMovieId) {
            viewModel.liveMovie.observe(this, movie -> {
                if (movie != null) {

                    switch (movie.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            if (movie.data != null) {
                                progressBar.setVisibility(View.GONE);
                                boolean state = movie.data.isFavorited();
                                setFavoriteState(state);
                            }
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        } else if (isTvId) {
            viewModel.liveTv.observe(this, tv -> {
                if (tv != null) {

                    switch (tv.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            if (tv.data != null) {
                                progressBar.setVisibility(View.GONE);
                                boolean state = tv.data.isFavorited();
                                setFavoriteState(state);
                            }
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            if (isMovieId) {
                viewModel.setMovieFavorite();
            } else if (isTvId) {
                viewModel.setTvFavorite();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFavoriteState(boolean state) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.action_favorite);
        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_24dp));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_24dp));
        }
    }

    private void populateMovie(MovieEntity content) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.movie));
            getSupportActionBar().setSubtitle(content.getTitle());
        }
        tvTitle.setText(content.getTitle());
        tvOverview.setText(content.getOverview());
        tvRelease.setText(content.getRelease());
        tvGenre.setText(content.getGenre());
        tvRuntime.setText(content.getRunningTime());
        rating = content.getRating();

        NumberFormat numberFormat = new DecimalFormat("#.0");
        String theRating;
        if (Objects.equals(rating, 0.0)) {
            theRating = getString(R.string.no_rating);
        } else {
            theRating = numberFormat.format(rating);
        }

        int integerRating = (int) rating / 2;
        for (int i = 0; i < integerRating; i++) {
            ivStar.get(i).setImageResource(R.drawable.ic_star_full_24dp);
        }
        if (Math.round(rating) > integerRating) {
            ivStar.get(integerRating).setImageResource(R.drawable.ic_star_half_24dp);
        }

        tvRating.setText(theRating);

        GlideApp.with(getApplicationContext())
                .load(content.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(ivPoster);

        GlideApp.with(getApplicationContext())
                .load(content.getBackdropPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(ivBackdrop);
    }

    private void populateTv(TvEntity content) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.tv));
            getSupportActionBar().setSubtitle(content.getTitle());
        }
        tvTitle.setText(content.getTitle());
        tvOverview.setText(content.getOverview());
        tvRelease.setText(content.getRelease());
        tvGenre.setText(content.getGenre());
        tvRuntime.setText(content.getRunningTime());
        rating = content.getRating();

        NumberFormat numberFormat = new DecimalFormat("#.0");
        String theRating;
        if (Objects.equals(rating, 0.0)) {
            theRating = getString(R.string.no_rating);
        } else {
            theRating = numberFormat.format(rating);
        }

        int integerRating = (int) rating / 2;
        for (int i = 0; i < integerRating; i++) {
            ivStar.get(i).setImageResource(R.drawable.ic_star_full_24dp);
        }
        if (Math.round(rating) > integerRating) {
            ivStar.get(integerRating).setImageResource(R.drawable.ic_star_half_24dp);
        }

        tvRating.setText(theRating);

        GlideApp.with(getApplicationContext())
                .load(content.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(ivPoster);

        GlideApp.with(getApplicationContext())
                .load(content.getBackdropPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(ivBackdrop);
    }
}
