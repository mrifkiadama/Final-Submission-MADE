package com.example.adamalbarisyi.myfavoritemovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adamalbarisyi.myfavoritemovie.DetailMovieActivity;
import com.example.adamalbarisyi.myfavoritemovie.R;

import static android.provider.BaseColumns._ID;
import static com.example.adamalbarisyi.myfavoritemovie.db.DatabaseContract.getColumnInt;
import static com.example.adamalbarisyi.myfavoritemovie.db.DatabaseContract.getColumnString;
import static com.example.adamalbarisyi.myfavoritemovie.db.FavoriteColumns.COL_BACKDROP_PATH;
import static com.example.adamalbarisyi.myfavoritemovie.db.FavoriteColumns.COL_ID;
import static com.example.adamalbarisyi.myfavoritemovie.db.FavoriteColumns.COL_ORILANGUANGE;
import static com.example.adamalbarisyi.myfavoritemovie.db.FavoriteColumns.COL_ORITITLE;
import static com.example.adamalbarisyi.myfavoritemovie.db.FavoriteColumns.COL_OVERVIEW;
import static com.example.adamalbarisyi.myfavoritemovie.db.FavoriteColumns.COL_POSTER_PATH;
import static com.example.adamalbarisyi.myfavoritemovie.db.FavoriteColumns.COL_RATING;
import static com.example.adamalbarisyi.myfavoritemovie.db.FavoriteColumns.COL_RELEASE_DATE;
import static com.example.adamalbarisyi.myfavoritemovie.db.FavoriteColumns.COL_TITLE;
import static com.example.adamalbarisyi.myfavoritemovie.db.FavoriteColumns.COL_VOTE;

public class FavoriteAdapter extends CursorAdapter {

    public FavoriteAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_movie_item,parent,false);
        return view;
    }

    @Override
    public Cursor getCursor(){
        return super.getCursor();
    }

    @Override
    public void bindView(View view,final Context context,final Cursor cursor) {
         TextView tvTittle;
         TextView tvRating;
         TextView tvPopularity;
         TextView tvOverview;
         TextView tvReleaseDate;
         ImageView imgPoster;
         RelativeLayout cvDetail;

         if (cursor != null){
             tvTittle = view.findViewById(R.id.tv_item_title_now);
             tvRating = view.findViewById(R.id.tv_item_rating_now);
             tvPopularity = view.findViewById(R.id.tv_item_popularity_now);
             tvOverview = view.findViewById(R.id.tv_item_overview_now);
             tvReleaseDate = view.findViewById(R.id.tv_item_release_date_now);
             imgPoster = view.findViewById(R.id.img_poster_movie_now);
             cvDetail = view.findViewById(R.id.cv_movie);



             tvTittle.setText(getColumnString(cursor,COL_TITLE));
             tvRating.setText(getColumnString(cursor,COL_RATING));
             tvPopularity.setText(getColumnString(cursor,COL_VOTE));
             tvOverview.setText(getColumnString(cursor,COL_OVERVIEW));
             tvReleaseDate.setText(getColumnString(cursor,COL_RELEASE_DATE));

             Glide.with(view.getContext())
                     .load("http://image.tmdb.org/t/p/w154" + getColumnString(cursor,COL_POSTER_PATH))
                     .into(imgPoster);

             cvDetail.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {

                     Intent intentDetail = new Intent(context,DetailMovieActivity.class);
                     intentDetail.putExtra(DetailMovieActivity.MOVIE_ID, getColumnInt(cursor,_ID));
                     intentDetail.putExtra(DetailMovieActivity.MOVIE_TITLE, getColumnString(cursor,COL_TITLE));
                     intentDetail.putExtra(DetailMovieActivity.MOVIE_ORITITLE, getColumnString(cursor,COL_ORITITLE));
                     intentDetail.putExtra(DetailMovieActivity.MOVIE_ORILANGUAGE, getColumnString(cursor,COL_ORILANGUANGE));
                     intentDetail.putExtra(DetailMovieActivity.MOVIE_VOTE,getColumnString(cursor,COL_VOTE));
                     intentDetail.putExtra(DetailMovieActivity.MOVIE_RATING,getColumnString(cursor,COL_RATING));
                     intentDetail.putExtra(DetailMovieActivity.MOVIE_RELEASE_DATE,getColumnString(cursor,COL_RELEASE_DATE));
                     intentDetail.putExtra(DetailMovieActivity.MOVIE_OVERVIEW,getColumnString(cursor,COL_OVERVIEW));
                     intentDetail.putExtra(DetailMovieActivity.MOVIE_POSTER,getColumnString(cursor,COL_POSTER_PATH));
                     intentDetail.putExtra(DetailMovieActivity.MOVIE_BACKDROP,getColumnString(cursor,COL_BACKDROP_PATH));
                     context.startActivity(intentDetail);
                 }
             });

         }




    }
}
