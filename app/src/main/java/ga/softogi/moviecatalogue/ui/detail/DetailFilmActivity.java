package ga.softogi.moviecatalogue.ui.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.request.RequestOptions;

import ga.softogi.moviecatalogue.R;
import ga.softogi.moviecatalogue.data.FilmEntity;
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
    private ProgressBar progressBar;

    @NonNull
    private static DetailFilmViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance();
        return ViewModelProviders.of(activity, factory).get(DetailFilmViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DetailFilmViewModel viewModel = obtainViewModel(this);
        tvTitle = findViewById(R.id.tv_title);
        tvOverview = findViewById(R.id.tv_overview);
        tvRelease = findViewById(R.id.tv_release);
        tvGenre = findViewById(R.id.tv_genre);
        tvRuntime = findViewById(R.id.tv_runtime);
        ivPoster = findViewById(R.id.iv_poster);
        ivBackdrop = findViewById(R.id.iv_backdrop);
        progressBar = findViewById(R.id.progress_bar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String filmTitle = extras.getString(EXTRA_FILM);
            if (filmTitle != null) {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.setMovieTitle(filmTitle);
                viewModel.setTvTitle(filmTitle);
            }
        }

        //detail untuk movie
        viewModel.getMovie().observe(this, detailMovie -> {
            if (detailMovie != null) {
                progressBar.setVisibility(View.GONE);
                populateFilm(detailMovie);
            }
        });

        //detail untuk tv
        viewModel.getTv().observe(this, detailTv -> {
            if (detailTv != null) {
                progressBar.setVisibility(View.GONE);
                populateFilm(detailTv);
            }
        });
    }

    private void populateFilm(FilmEntity content) {
        tvTitle.setText(content.getTitle());
        tvOverview.setText(content.getOverview());
        tvRelease.setText(content.getRelease());
        tvGenre.setText(content.getGenre());
        tvRuntime.setText(content.getRunningTime());

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
