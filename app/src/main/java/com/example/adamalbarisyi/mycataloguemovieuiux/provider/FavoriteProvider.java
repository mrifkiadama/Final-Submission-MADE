package com.example.adamalbarisyi.mycataloguemovieuiux.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteHelper;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.DatabaseContract.AUTHORITY;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.DatabaseContract.CONTENT_URI;
import static com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteColumns.TABLE_NAME;

public class FavoriteProvider extends ContentProvider {

    private static final int FAVORITE = 100;
    private static final int FAVORITE_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME, FAVORITE);

        sUriMatcher.addURI(AUTHORITY,
                TABLE_NAME + "/#",
                FAVORITE_ID);
    }

    private FavoriteHelper favoriteHelper;

    @Override
    public boolean onCreate() {
        favoriteHelper = new FavoriteHelper(getContext());
        favoriteHelper.open();
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE:
                cursor = favoriteHelper.queryProvider();
                break;

            case FAVORITE_ID:
                cursor = favoriteHelper.queryByIdProvider(uri.getLastPathSegment());
                break;

            default:
                cursor = null;
                break;
        }

        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return cursor;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long added;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE:
                added = favoriteHelper.insertProvider(contentValues);
                break;

            default:
                added = 0;
                break;
        }

        if (added > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE_ID:
                deleted = favoriteHelper.deleteProvider(uri.getLastPathSegment());
                break;

            default:
                deleted = 0;
                break;
        }

        if (deleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        int updated;
        switch (sUriMatcher.match(uri)) {
            case FAVORITE_ID:
                updated = favoriteHelper.updateProvider(uri.getLastPathSegment(), contentValues);
                break;

            default:
                updated = 0;
                break;
        }

        if (updated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return updated;
    }
}
