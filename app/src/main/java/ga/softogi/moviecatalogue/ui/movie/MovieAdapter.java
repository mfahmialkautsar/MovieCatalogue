package ga.softogi.moviecatalogue.ui.movie;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import ga.softogi.moviecatalogue.R;
import ga.softogi.moviecatalogue.data.source.local.entity.MovieEntity;
import ga.softogi.moviecatalogue.ui.detail.DetailFilmActivity;
import ga.softogi.moviecatalogue.utils.MovieShareCallback;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final Activity activity;
    private final MovieShareCallback shareCallback;
    private List<MovieEntity> mMovie = new ArrayList<>();

    MovieAdapter(Activity activity, MovieShareCallback shareCallback) {
        this.activity = activity;
        this.shareCallback = shareCallback;
    }

    private List<MovieEntity> getListMovie() {
        return mMovie;
    }

    void setListTv(List<MovieEntity> listMovies) {
        if (listMovies == null) return;
        this.mMovie.clear();
        this.mMovie.addAll(listMovies);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_film, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        MovieEntity movie = getListMovie().get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault());
        String release = movie.getRelease();
        String genre = movie.getGenre();
        String runningTime = movie.getRunningTime();
        String theRelease;
        try {
            Date dateRelease = dateFormat.parse(release);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
            theRelease = newDateFormat.format(Objects.requireNonNull(dateRelease));
        } catch (ParseException e) {
            theRelease = release;
            e.printStackTrace();
        }

        holder.tvTitle.setText(movie.getTitle());
        holder.tvDetail.setText(String.format("%s ‧ %s ‧ %s", theRelease, genre, runningTime));

        double rating = movie.getRating();
        String theRating;
        NumberFormat numberFormat = new DecimalFormat("#.0");
        if (Objects.equals(rating, 0.0)) {
            theRating = holder.itemView.getContext().getString(R.string.no_rating);
        } else {
            theRating = numberFormat.format(rating);
        }
        holder.tvRating.setText(String.format(" %s", theRating));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailFilmActivity.class);
            intent.putExtra(DetailFilmActivity.EXTRA_FILM, movie.getMovieId());
            activity.startActivity(intent);
        });

        holder.imgShare.setOnClickListener(view -> shareCallback.onShareClick(mMovie.get(holder.getAdapterPosition())));

        Glide.with(holder.itemView.getContext())
                .load(movie.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDetail;
        final TextView tvRating;
        final ImageView ivPoster;
        final ImageButton imgShare;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDetail = itemView.findViewById(R.id.tv_detail);
            tvRating = itemView.findViewById(R.id.tv_rating);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            imgShare = itemView.findViewById(R.id.btn_share);
        }
    }
}
