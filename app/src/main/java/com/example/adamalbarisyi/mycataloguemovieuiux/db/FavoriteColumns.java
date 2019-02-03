package com.example.adamalbarisyi.mycataloguemovieuiux.db;

import android.provider.BaseColumns;

public class FavoriteColumns implements BaseColumns {
    public static String TABLE_NAME = "favorite_movie";

    public static String COL_ID = "_id";
    public static String COL_BACKDROP_PATH = "backdrop_path";
    public static String COL_TITLE = "title";
    public static String COL_ORITITLE = "original_title";
    public static String COL_ORILANGUANGE = "original_language";
    public static String COL_VOTE = "vote_count";
    public static String COL_RATING = "vote_average";
    public static String COL_RELEASE_DATE = "release_date";
    public static String COL_OVERVIEW = "overview";
    public static String COL_POSTER_PATH = "poster_path";
}
