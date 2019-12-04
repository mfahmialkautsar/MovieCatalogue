package ga.softogi.moviecatalogue.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movieentities")
public class MovieEntity {

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "movieId")
    private String movieId;

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

    @ColumnInfo(name = "posterPath")
    private int posterPath;

    @ColumnInfo(name = "backdropPath")
    private int backdropPath;

    @ColumnInfo(name = "rating")
    private double rating;

    @ColumnInfo(name = "favorited")
    private boolean favorited = false;

    public MovieEntity(@NonNull String movieId, String title, String overview, String release, String genre, String runningTime, double rating, int posterPath, int backdropPath, Boolean favorited) {
        this.movieId = movieId;
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
    public String getMovieId() {
        return movieId;
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
