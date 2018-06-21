package project.udacity.awillis.moviesv3;

import android.os.Parcel;
import android.os.Parcelable;

public class MoviePOJO implements Parcelable {

    private boolean video;
    private int id;
    private int vote_count;
    private String vote_average;
    private String poster;
    private String title;
    private String release;
    private String original_language;
    private String original_title;
    private boolean adult;
    private String description;


    public MoviePOJO(boolean video, int id, int vote_count, String vote_average, String poster, String title, String release, String original_language, String original_title, boolean adult, String description) {
        this.video = video;
        this.id = id;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.poster = poster;
        this.title = title;
        this.release = release;
        this.original_language = original_language;
        this.original_title = original_title;
        this.adult = adult;
        this.description = description;
    }

    public MoviePOJO(String title, String originalTitle, int voteCount, String voteAverage, String releaseDate, String overview, int popularity, String posterPath, String backdrop, boolean video, String originalLanguage, int genreId, boolean adult) {
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeInt(this.id);
        dest.writeInt(this.vote_count);
        dest.writeString(this.vote_average);
        dest.writeString(this.poster);
        dest.writeString(this.title);
        dest.writeString(this.release);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.description);
    }

    protected MoviePOJO(Parcel in) {
        this.video = in.readByte() != 0;
        this.id = in.readInt();
        this.vote_count = in.readInt();
        this.vote_average = in.readString();
        this.poster = in.readString();
        this.title = in.readString();
        this.release = in.readString();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.adult = in.readByte() != 0;
        this.description = in.readString();
    }

    public static final Creator<MoviePOJO> CREATOR = new Creator<MoviePOJO>() {
        @Override
        public MoviePOJO createFromParcel(Parcel source) {
            return new MoviePOJO(source);
        }

        @Override
        public MoviePOJO[] newArray(int size) {
            return new MoviePOJO[size];
        }
    };
}
