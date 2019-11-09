package ga.softogi.moviecatalogue.data;

public class FilmEntity {
    private String title;
    private String overview;
    private String release;
    private String genre;
    private String runningTime;
    private int posterPath;
    private int backdropPath;

    public FilmEntity(String title, String overview, String release, String genre, String runningTime, int posterPath, int backdropPath) {
        this.title = title;
        this.overview = overview;
        this.release = release;
        this.genre = genre;
        this.runningTime = runningTime;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public int getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(int posterPath) {
        this.posterPath = posterPath;
    }

    public int getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(int backdropPath) {
        this.backdropPath = backdropPath;
    }
}
