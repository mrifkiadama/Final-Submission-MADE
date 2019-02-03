package com.example.adamalbarisyi.myfavoritemovie;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.adamalbarisyi.myfavoritemovie.adapter.FavoriteAdapter;
import com.example.adamalbarisyi.myfavoritemovie.db.FavoriteColumns;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.adamalbarisyi.myfavoritemovie.db.DatabaseContract.CONTENT_URI;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemClickListener {

    private FavoriteAdapter adapter;
    @BindView
   (R.id.rv_category) ListView listView;

    private final int LOAD_FAVORITE_ID = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new FavoriteAdapter(this,null,true);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        getSupportLoaderManager().initLoader(LOAD_FAVORITE_ID,null,this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(LOAD_FAVORITE_ID,null,this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i,  Bundle bundle) {
        return new CursorLoader(this,CONTENT_URI,null,null,null,null);
    }

    @Override
    public void onLoadFinished( Loader<Cursor> loader, Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset( Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(LOAD_FAVORITE_ID);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Cursor cursor = (Cursor) adapter.getItem(i);

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(FavoriteColumns._ID));
        Intent intent = new Intent(MainActivity.this,DetailMovieActivity.class);
        intent.setData(Uri.parse(CONTENT_URI + "/"+id));
        startActivity(intent);
    }
}
