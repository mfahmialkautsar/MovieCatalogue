package ga.softogi.moviecatalogue.adapter;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import ga.softogi.moviecatalogue.ContentShareCallback;
import ga.softogi.moviecatalogue.R;
import ga.softogi.moviecatalogue.data.FilmEntity;
import ga.softogi.moviecatalogue.ui.detail.DetailFilmActivity;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ContentViewContent> {
    private final Activity activity;
    private final ContentShareCallback shareCallback;
    private List<FilmEntity> mMovie = new ArrayList<>();

    public FilmAdapter(Activity activity, ContentShareCallback shareCallback) {
        this.activity = activity;
        this.shareCallback = shareCallback;
    }

    private List<FilmEntity> getListContent() {
        return mMovie;
    }

    public void setListContent(List<FilmEntity> listMovies) {
        if (listMovies == null) return;
        this.mMovie.clear();
        this.mMovie.addAll(listMovies);
    }

    @NonNull
    @Override
    public ContentViewContent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_film, parent, false);
        return new ContentViewContent(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewContent holder, final int position) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault());
        String release = getListContent().get(position).getRelease();
        String genre = getListContent().get(position).getGenre();
        String runningTime = getListContent().get(position).getRunningTime();
        String theRelease;
        try {
            Date dateRelease = dateFormat.parse(release);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
            theRelease = newDateFormat.format(Objects.requireNonNull(dateRelease));
        } catch (ParseException e) {
            theRelease = release;
            e.printStackTrace();
        }

        holder.tvTitle.setText(getListContent().get(position).getTitle());
        holder.tvDetail.setText(String.format("%s ‧ %s ‧ %s", theRelease, genre, runningTime));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailFilmActivity.class);
            intent.putExtra(DetailFilmActivity.EXTRA_FILM, getListContent().get(position).getTitle());
            activity.startActivity(intent);
        });
        holder.imgShare.setOnClickListener(view -> shareCallback.onShareClick(mMovie.get(holder.getAdapterPosition())));

        Glide.with(holder.itemView.getContext())
                .load(getListContent().get(position).getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return getListContent().size();
    }

    class ContentViewContent extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDetail;
        final ImageView ivPoster;
        final ImageButton imgShare;

        ContentViewContent(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDetail = itemView.findViewById(R.id.tv_detail);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            imgShare = itemView.findViewById(R.id.btn_share);
        }
    }
}
