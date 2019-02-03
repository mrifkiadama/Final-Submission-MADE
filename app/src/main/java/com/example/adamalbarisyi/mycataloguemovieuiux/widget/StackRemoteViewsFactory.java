package com.example.adamalbarisyi.mycataloguemovieuiux.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.adamalbarisyi.mycataloguemovieuiux.R;
import com.example.adamalbarisyi.mycataloguemovieuiux.adapter.NowUpFavMovieItems;
import com.example.adamalbarisyi.mycataloguemovieuiux.searchPage.MovieItems;

import java.lang.annotation.Target;
import java.util.concurrent.ExecutionException;

import static com.example.adamalbarisyi.mycataloguemovieuiux.db.DatabaseContract.CONTENT_URI;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

   private Context mContext;
   private int mAppWidgetId;
   private Cursor mCursor;

    public StackRemoteViewsFactory(Context mContext, Intent intent) {
        this.mContext = mContext;
        this.mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
        appWidgetManager.notifyAppWidgetViewDataChanged(mAppWidgetId, R.id.stack_view);
    }

    @Override
    public void onDataSetChanged() {
        final long identityToken = Binder.clearCallingIdentity();
        mCursor = mContext.getContentResolver().query(
                CONTENT_URI,
                null,
                null,
                null,
                null
        );
        Binder.restoreCallingIdentity(identityToken);

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mCursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        NowUpFavMovieItems nowUpFavMovieItems = getItem(position);
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),R.layout.widget_item);
        Bitmap bitmap = null;

        try {
            bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load("http://image.tmdb.org/t/p/w154/" +nowUpFavMovieItems.getPosterPath())
                    .apply(new RequestOptions().fitCenter())
                    .submit()
                    .get();
            remoteViews.setImageViewBitmap(R.id.img_imageView_item,bitmap);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }


        Bundle bundle = new Bundle();
        bundle.putInt(FavoriteMovieWidget.EXTRA_ITEM,position);
        Intent fiilIntent = new Intent();
        fiilIntent.putExtras(bundle);

        remoteViews.setOnClickFillInIntent(R.id.img_imageView_item,fiilIntent);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public NowUpFavMovieItems getItem(int position){
        if (!mCursor.moveToPosition(position)){
            throw new IllegalStateException("Invalid position!");
        }
        return new NowUpFavMovieItems(mCursor);
    }
}
