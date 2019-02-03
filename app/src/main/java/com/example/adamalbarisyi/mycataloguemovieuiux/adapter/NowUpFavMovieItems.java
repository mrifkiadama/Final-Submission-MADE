package com.example.adamalbarisyi.mycataloguemovieuiux.adapter;


import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static android.provider.BaseColumns._ID;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.DatabaseContract.getColumnInt;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.DatabaseContract.getColumnString;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteColumns.COL_BACKDROP_PATH;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteColumns.COL_ID;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteColumns.COL_ORILANGUANGE;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteColumns.COL_ORITITLE;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteColumns.COL_OVERVIEW;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteColumns.COL_POSTER_PATH;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteColumns.COL_RATING;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteColumns.COL_RELEASE_DATE;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteColumns.COL_TITLE;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteColumns.COL_VOTE;

public class NowUpFavMovieItems implements Parcelable {
    private int id;
    private String backDropPath;
    private String Title;
    private String oriTitle;
    private String oriLanguage;
    private String Vote;
    private String Rating;
    private String ReleaseDate;
    private String Overview;
    private String posterPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getOriTitle() {
        return oriTitle;
    }

    public void setOriTitle(String oriTitle) {
        this.oriTitle = oriTitle;
    }

    public String getOriLanguage() {
        return oriLanguage;
    }

    public void setOriLanguage(String oriLanguage) {
        this.oriLanguage = oriLanguage;
    }

    public String getVote() {
        return Vote;
    }

    public void setVote(String vote) {
        this.Vote = vote;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        this.Rating = rating;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.ReleaseDate = releaseDate;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        this.Overview = overview;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.backDropPath);
        dest.writeString(this.Title);
        dest.writeString(this.oriTitle);
        dest.writeString(this.oriLanguage);
        dest.writeString(this.Vote);
        dest.writeString(this.Rating);
        dest.writeString(this.ReleaseDate);
        dest.writeString(this.Overview);
        dest.writeString(this.posterPath);
    }

    public NowUpFavMovieItems() {

    }

    public NowUpFavMovieItems(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.backDropPath = getColumnString(cursor, COL_BACKDROP_PATH);
        this.Title = getColumnString(cursor, COL_TITLE);
        this.oriTitle = getColumnString(cursor, COL_ORITITLE);
        this.oriLanguage = getColumnString(cursor, COL_ORILANGUANGE);
        this.Vote = getColumnString(cursor, COL_VOTE);
        this.Rating = getColumnString(cursor, COL_RATING);
        this.ReleaseDate = getColumnString(cursor, COL_RELEASE_DATE);
        this.Overview = getColumnString(cursor, COL_OVERVIEW);
        this.posterPath = getColumnString(cursor, COL_POSTER_PATH);
    }

    protected NowUpFavMovieItems(Parcel in) {
        this.id = in.readInt();
        this.backDropPath = in.readString();
        this.Title = in.readString();
        this.oriTitle = in.readString();
        this.oriLanguage = in.readString();
        this.Vote = in.readString();
        this.Rating = in.readString();
        this.ReleaseDate = in.readString();
        this.Overview = in.readString();
        this.posterPath = in.readString();
    }

    public static final Creator<NowUpFavMovieItems> CREATOR = new Creator<NowUpFavMovieItems>() {
        @Override
        public NowUpFavMovieItems createFromParcel(Parcel source) {
            return new NowUpFavMovieItems(source);
        }

        @Override
        public NowUpFavMovieItems[] newArray(int size) {
            return new NowUpFavMovieItems[size];
        }
    };
}
