package ga.softogi.moviecatalogue.ui.favorite.tv;

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

import ga.softogi.moviecatalogue.R;
import ga.softogi.moviecatalogue.data.source.local.entity.TvEntity;
import ga.softogi.moviecatalogue.ui.detail.DetailFilmActivity;
import ga.softogi.moviecatalogue.utils.TvShareCallback;

public class FavTvPagedAdapter extends PagedListAdapter<TvEntity, FavTvPagedAdapter.FavTvViewHolder> {

    private static final DiffUtil.ItemCallback<TvEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvEntity oldItem, @NonNull TvEntity newItem) {
                    return oldItem.getTvId().equals(newItem.getTvId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TvEntity oldItem, @NonNull TvEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    private TvShareCallback callback;

    FavTvPagedAdapter(TvShareCallback callback) {
        super(DIFF_CALLBACK);

        this.callback = callback;
    }

    TvEntity getTvById(int swipedPosition) {
        return getItem(swipedPosition);
    }

    @NonNull
    @Override
    public FavTvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_film, parent, false);
        return new FavTvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavTvViewHolder holder, int position) {
        final TvEntity favTv = getItem(position);
        if (favTv != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault());
            String release = favTv.getRelease();
            String genre = favTv.getGenre();
            String runningTime = favTv.getRunningTime();
            String theRelease;
            try {
                Date dateRelease = dateFormat.parse(release);
                SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
                theRelease = newDateFormat.format(Objects.requireNonNull(dateRelease));
            } catch (ParseException e) {
                theRelease = release;
                e.printStackTrace();
            }
            holder.tvTitle.setText(favTv.getTitle());
            holder.tvDetail.setText(String.format("%s ‧ %s ‧ %s", theRelease, genre, runningTime));

            double rating = favTv.getRating();
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
                intent.putExtra(DetailFilmActivity.EXTRA_FILM, favTv.getTvId());
                context.startActivity(intent);
            });
            holder.imgShare.setOnClickListener(view -> {
                TvEntity tvEntity = new TvEntity(favTv.getTvId(),
                        favTv.getTitle(),
                        favTv.getOverview(),
                        favTv.getRelease(),
                        favTv.getGenre(),
                        favTv.getRunningTime(),
                        favTv.getRating(),
                        favTv.getPosterPath(),
                        favTv.getBackdropPath(),
                        false);
                callback.onShareClick(tvEntity);
            });

            Glide.with(holder.itemView.getContext())
                    .load(favTv.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(holder.ivPoster);
        }
    }

    class FavTvViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDetail;
        final TextView tvRating;
        final ImageView ivPoster;
        final ImageButton imgShare;

        FavTvViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDetail = itemView.findViewById(R.id.tv_detail);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            imgShare = itemView.findViewById(R.id.btn_share);
            tvRating = itemView.findViewById(R.id.tv_rating);
        }
    }
}
