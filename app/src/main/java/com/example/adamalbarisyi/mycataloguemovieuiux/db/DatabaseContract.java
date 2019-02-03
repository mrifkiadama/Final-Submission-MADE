package com.example.adamalbarisyi.mycataloguemovieuiux.db;

import android.database.Cursor;
import android.net.Uri;

import static com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteColumns.TABLE_NAME;

public class DatabaseContract {

    public static final String AUTHORITY = "com.example.adamalbarisyi.mycataloguemovieuiux";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }
}
