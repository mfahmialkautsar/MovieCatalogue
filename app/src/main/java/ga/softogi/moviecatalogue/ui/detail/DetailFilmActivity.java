package ga.softogi.moviecatalogue.ui.detail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.request.RequestOptions;

import ga.softogi.moviecatalogue.R;
import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.utils.GlideApp;

public class DetailFilmActivity extends AppCompatActivity {

    public static final String EXTRA_FILM = "extra_film";
    private TextView tvTitle;
    private TextView tvOverview;
    private TextView tvRelease;
    private TextView tvGenre;
    private TextView tvRuntime;
    private ImageView ivPoster;
    private ImageView ivBackdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DetailFilmViewModel viewModel = ViewModelProviders.of(this).get(DetailFilmViewModel.class);
        tvTitle = findViewById(R.id.tv_title);
        tvOverview = findViewById(R.id.tv_overview);
        tvRelease = findViewById(R.id.tv_release);
        tvGenre = findViewById(R.id.tv_genre);
        tvRuntime = findViewById(R.id.tv_runtime);
        ivPoster = findViewById(R.id.iv_poster);
        ivBackdrop = findViewById(R.id.iv_backdrop);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String filmTitle = extras.getString(EXTRA_FILM);
            if (filmTitle != null) {
                viewModel.setFilmTitle(filmTitle);
            }
        }

        if (viewModel.getFilm() != null) {
            populateContent(viewModel.getFilm());
        }
    }

    private void populateContent(FilmEntity content) {
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
