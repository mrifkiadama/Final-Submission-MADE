package com.example.adamalbarisyi.mycataloguemovieuiux.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "db_catalogue_movie";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            FavoriteColumns.TABLE_NAME,
            FavoriteColumns.COL_ID,
            FavoriteColumns.COL_TITLE,
            FavoriteColumns.COL_ORITITLE,
            FavoriteColumns.COL_ORILANGUANGE,
            FavoriteColumns.COL_VOTE,
            FavoriteColumns.COL_RATING,
            FavoriteColumns.COL_RELEASE_DATE,
            FavoriteColumns.COL_OVERVIEW,
            FavoriteColumns.COL_POSTER_PATH,
            FavoriteColumns.COL_BACKDROP_PATH
    );

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteColumns.TABLE_NAME);
        onCreate(db);
    }
}
