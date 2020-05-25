package mfahmialkautsar.moviecatalogue.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tventities")
public class TvEntity {

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "tvId")
    private String tvId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "release")
    private String release;

    @ColumnInfo(name = "genre")
    private String genre;

    @ColumnInfo(name = "runningTime")
    private String runningTime;

    @ColumnInfo(name = "rating")
    private double rating;

    @ColumnInfo(name = "posterPath")
    private int posterPath;

    @ColumnInfo(name = "backdropPath")
    private int backdropPath;

    @ColumnInfo(name = "favorited")
    private boolean favorited = false;

    public TvEntity(@NonNull String tvId, String title, String overview, String release, String genre, String runningTime, double rating, int posterPath, int backdropPath, Boolean favorited) {
        this.tvId = tvId;
        this.title = title;
        this.overview = overview;
        this.release = release;
        this.genre = genre;
        this.runningTime = runningTime;
        this.rating = rating;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        if (favorited != null) {
            this.favorited = favorited;
        }
    }

    @NonNull
    public String getTvId() {
        return tvId;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease() {
        return release;
    }

    public String getGenre() {
        return genre;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public double getRating() {
        return rating;
    }

    public int getPosterPath() {
        return posterPath;
    }

    public int getBackdropPath() {
        return backdropPath;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }
}
