package mfahmialkautsar.moviecatalogue.ui.favorite.movie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import mfahmialkautsar.moviecatalogue.R;
import mfahmialkautsar.moviecatalogue.data.source.local.entity.MovieEntity;
import mfahmialkautsar.moviecatalogue.ui.detail.DetailFilmActivity;
import mfahmialkautsar.moviecatalogue.utils.MovieShareCallback;

public class FavMoviePagedAdapter extends PagedListAdapter<MovieEntity, FavMoviePagedAdapter.FavMovieViewHolder> {

    private static final DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.getMovieId().equals(newItem.getMovieId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private MovieShareCallback callback;

    FavMoviePagedAdapter(MovieShareCallback callback) {
        super(DIFF_CALLBACK);

        this.callback = callback;
    }

    MovieEntity getMovieById(int swipedPosition) {
        return getItem(swipedPosition);
    }

    @NonNull
    @Override
    public FavMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_film, parent, false);
        return new FavMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavMovieViewHolder holder, int position) {
        final MovieEntity favMovie = getItem(position);
        if (favMovie != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault());
            String release = favMovie.getRelease();
            String genre = favMovie.getGenre();
            String runningTime = favMovie.getRunningTime();
            String theRelease;
            try {
                Date dateRelease = dateFormat.parse(release);
                SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
                theRelease = newDateFormat.format(Objects.requireNonNull(dateRelease));
            } catch (ParseException e) {
                theRelease = release;
                e.printStackTrace();
            }
            holder.tvTitle.setText(favMovie.getTitle());
            holder.tvDetail.setText(String.format("%s ‧ %s ‧ %s", theRelease, genre, runningTime));

            double rating = favMovie.getRating();
            String theRating;
            NumberFormat numberFormat = new DecimalFormat("#.0");
            if (Objects.equals(rating, 0.0)) {
                theRating = holder.itemView.getContext().getString(R.string.no_rating);
            } else {
                theRating = numberFormat.format(rating);
            }
            holder.tvRating.setText(String.format(" %s", theRating));

            holder.itemView.setOnClickListener(v -> {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailFilmActivity.class);
                intent.putExtra(DetailFilmActivity.EXTRA_FILM, favMovie.getMovieId());
                context.startActivity(intent);
            });
            holder.imgShare.setOnClickListener(view -> {
                MovieEntity movieEntity = new MovieEntity(favMovie.getMovieId(),
                        favMovie.getTitle(),
                        favMovie.getOverview(),
                        favMovie.getRelease(),
                        favMovie.getGenre(),
                        favMovie.getRunningTime(),
                        favMovie.getRating(),
                        favMovie.getPosterPath(),
                        favMovie.getBackdropPath(),
                        false);
                callback.onShareClick(movieEntity);
            });

            Glide.with(holder.itemView.getContext())
                    .load(favMovie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(holder.ivPoster);
        }
    }

    class FavMovieViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDetail;
        final TextView tvRating;
        final ImageView ivPoster;
        final ImageButton imgShare;

        FavMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDetail = itemView.findViewById(R.id.tv_detail);
            tvRating = itemView.findViewById(R.id.tv_rating);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            imgShare = itemView.findViewById(R.id.btn_share);
        }
    }
}
