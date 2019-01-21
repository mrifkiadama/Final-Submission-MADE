package com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.adamalbarisyi.mycataloguemovieuiux.R;
import com.google.gson.Gson;

public class DetailMovieActivity extends AppCompatActivity {

    ImageView imgBackdrop;
    ImageView imgPoster;
    TextView tvTitle;
    TextView tvOriTitle;
    TextView tvOriLanguage;
    TextView tvVote;
    TextView tvRating;
    TextView tvReleaseDate;
    TextView tvOverview;


    public static final String MOVIE_TITLE = "MOVIE_TITLE";
    public static final String MOVIE_ORITITLE = "MOVIE_ORITITLE";
    public static final String MOVIE_ORILANGUAGE = "MOVIE_ORILANGUAGE";
    public static final String MOVIE_VOTE = "MOVIE_VOTE";
    public static final String MOVIE_RATING = "MOVIE_RATING";
    public static final String MOVIE_RELEASE_DATE = "MOVIE_RELEASE_DATE";
    public static final String MOVIE_OVERVIEW = "MOVIE_OVERVIEW";
    public static final String MOVIE_POSTER = "MOVIE_POSTER";
    public static final String MOVIE_BACKDROP = "MOVIE_BACKDROP";
    private Gson gson = new Gson();
    private DetailMovieActivity detailMovieActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        imgBackdrop = findViewById(R.id.img_backdrop_movie);
        imgPoster = findViewById(R.id.img_poster_movie);
        tvTitle = findViewById(R.id.tv_title_movie);
        tvOriTitle = findViewById(R.id.tv_original_title);
        tvOriLanguage = findViewById(R.id.tv_original_language);
        tvVote = findViewById(R.id.tv_detail_vote);
        tvRating = findViewById(R.id.tv_detail_rating);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvOverview = findViewById(R.id.tv_overview);

        String tittle = getIntent().getStringExtra(MOVIE_TITLE);
        String oriTitle = getIntent().getStringExtra(MOVIE_ORITITLE);
        String orilanguage = getIntent().getStringExtra(MOVIE_ORILANGUAGE);
        String vote = getIntent().getStringExtra(MOVIE_VOTE);
        String rating = getIntent().getStringExtra(MOVIE_RATING);
        String releaseDate = getIntent().getStringExtra(MOVIE_RELEASE_DATE);
        String overview = getIntent().getStringExtra(MOVIE_OVERVIEW);
        String backdropPath = getIntent().getStringExtra(MOVIE_BACKDROP);
        String posterPath = getIntent().getStringExtra(MOVIE_POSTER);


        tvTitle.setText(tittle);
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

    }
}
