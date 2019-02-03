package com.example.adamalbarisyi.myfavoritemovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieActivity extends AppCompatActivity {
    @BindView(R.id.img_backdrop_movie)
    ImageView imgBackdrop;
    @BindView(R.id.img_poster_movie)
    ImageView imgPoster;
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
    }
}
