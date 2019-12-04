package ga.softogi.moviecatalogue.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class FilmResponse implements Parcelable {
    public static final Parcelable.Creator<FilmResponse> CREATOR = new Parcelable.Creator<FilmResponse>() {
        @Override
        public FilmResponse createFromParcel(Parcel source) {
            return new FilmResponse(source);
        }

        @Override
        public FilmResponse[] newArray(int size) {
            return new FilmResponse[size];
        }
    };
    private String id;
    private String title;
    private String overview;
    private String release;
    private String genre;
    private String runningTime;
    private double rating;
    private int posterPath;
    private int backdropPath;

    public FilmResponse(String id, String title, String overview, String release, String genre, String runningTime, double rating, int posterPath, int backdropPath) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.release = release;
        this.genre = genre;
        this.runningTime = runningTime;
        this.rating = rating;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }

    private FilmResponse(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.overview = in.readString();
        this.release = in.readString();
        this.genre = in.readString();
        this.runningTime = in.readString();
        this.rating = in.readDouble();
        this.posterPath = in.readInt();
        this.backdropPath = in.readInt();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPosterPath() {
        return posterPath;
    }

    public int getBackdropPath() {
        return backdropPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.release);
        dest.writeString(this.genre);
        dest.writeString(this.runningTime);
        dest.writeDouble(this.rating);
        dest.writeInt(this.posterPath);
        dest.writeInt(this.backdropPath);
    }
}
