package com.example.adamalbarisyi.mycataloguemovieuiux;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.adamalbarisyi.mycataloguemovieuiux.adapter.NowUpFavMovieItems;
import com.example.adamalbarisyi.mycataloguemovieuiux.db.FavoriteHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.adamalbarisyi.mycataloguemovieuiux.db.DatabaseContract.CONTENT_URI;
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

public class DetailMovieActivity extends AppCompatActivity {

    @BindView
            (R.id.img_backdrop_movie)
    ImageView imgBackdrop;
    @BindView(R.id.img_poster_movie)
    ImageView imgPoster;
    @BindView(R.id.iv_fav)
    ImageView imgFav;
    @BindView(R.id.tv_title_movie)
    TextView tvTitle;
    @BindView(R.id.tv_original_title)
    TextView tvOriTitle;
    @BindView(R.id.tv_original_language)
    TextView tvOriLanguage;
    @BindView(R.id.tv_detail_vote)
    TextView tvVote;
    @BindView(R.id.tv_detail_rating)
    TextView tvRating;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_overview)
    TextView tvOverview;


    public static String MOVIE_ID = "movie_id";
    public static String MOVIE_TITLE = "movie_title";
    public static String MOVIE_ORITITLE = "movie_original_title";
    public static String MOVIE_ORILANGUAGE = "movie_original_language";
    public static String MOVIE_VOTE = "movie_vote_count";
    public static String MOVIE_RATING = "movie_vote_average";
    public static String MOVIE_RELEASE_DATE = "movie_release_date";
    public static String MOVIE_OVERVIEW = "movie_overview";
    public static String MOVIE_POSTER = "movie_poster_path";
    public static String MOVIE_BACKDROP = "movie_backdrop_path";


    private Boolean isFavorite = false;
    private FavoriteHelper favoriteHelper;
    private NowUpFavMovieItems items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        ButterKnife.bind(this);

        int id = getIntent().getIntExtra(MOVIE_ID, 0);
        String title = getIntent().getStringExtra(MOVIE_TITLE);
        String oriTitle = getIntent().getStringExtra(MOVIE_ORITITLE);
        String orilanguage = getIntent().getStringExtra(MOVIE_ORILANGUAGE);
        String vote = getIntent().getStringExtra(MOVIE_VOTE);
        String rating = getIntent().getStringExtra(MOVIE_RATING);
        String releaseDate = getIntent().getStringExtra(MOVIE_RELEASE_DATE);
        String overview = getIntent().getStringExtra(MOVIE_OVERVIEW);
        String backdropPath = getIntent().getStringExtra(MOVIE_BACKDROP);
        String posterPath = getIntent().getStringExtra(MOVIE_POSTER);

        items = new NowUpFavMovieItems();
        items.setId(id);
        items.setTitle(title);
        items.setOriTitle(oriTitle);
        items.setOriLanguage(orilanguage);
        items.setVote(vote);
        items.setRating(rating);
        items.setReleaseDate(releaseDate);
        items.setOverview(overview);
        items.setBackDropPath(backdropPath);
        items.setPosterPath(posterPath);


        tvTitle.setText(title);
        tvOriTitle.setText(oriTitle);
        tvOriLanguage.setText(orilanguage);
        tvVote.setText(vote);
        tvRating.setText(rating);
        tvReleaseDate.setText(releaseDate);
        tvOverview.setText(overview);

        Glide.with(DetailMovieActivity.this)
                .load("http://image.tmdb.org/t/p/w185" + backdropPath)
                .into(imgBackdrop);
        Glide.with(DetailMovieActivity.this)
                .load("http://image.tmdb.org/t/p/w154" + posterPath)
                .into(imgPoster);

        loadData();
        imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite) {
                    deleteFav();
                } else {
                    saveFav();
                }

                isFavorite = !isFavorite;
                changeFavColor();
            }
        });
    }

    private void loadData() {
        favoriteHelper = new FavoriteHelper(this);
        favoriteHelper.open();
        Cursor cursor = getContentResolver().query(
                Uri.parse(CONTENT_URI + "/" + items.getId()),
                null,
                null,
                null,
                null
        );
        if (cursor != null) {
            if (cursor.moveToFirst()) isFavorite = true;
            cursor.close();
        }
        changeFavColor();
    }

    private void changeFavColor() {
        if (isFavorite) {
            imgFav.setImageResource(R.drawable.ic_favorite_1);
        } else {
            imgFav.setImageResource(R.drawable.ic_favorite_border);
        }
    }


    private void saveFav() {
        ContentValues values = new ContentValues();
        values.put(COL_ID, items.getId());
        values.put(COL_BACKDROP_PATH, items.getBackDropPath());
        values.put(COL_TITLE, items.getTitle());
        values.put(COL_ORITITLE, items.getOriTitle());
        values.put(COL_ORILANGUANGE, items.getOriLanguage());
        values.put(COL_VOTE, items.getVote());
        values.put(COL_RATING, items.getRating());
        values.put(COL_RELEASE_DATE, items.getReleaseDate());
        values.put(COL_OVERVIEW, items.getOverview());
        values.put(COL_POSTER_PATH, items.getPosterPath());

        getContentResolver().insert(CONTENT_URI, values);
        Toast.makeText(this, R.string.save, Toast.LENGTH_SHORT).show();
    }


    private void deleteFav() {
        getContentResolver().delete(
                Uri.parse(CONTENT_URI + "/" + items.getId()),
                null,
                null
        );
        Toast.makeText(this, R.string.remove, Toast.LENGTH_SHORT).show();
    }
}
