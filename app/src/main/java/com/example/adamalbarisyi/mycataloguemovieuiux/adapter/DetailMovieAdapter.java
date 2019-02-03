package com.example.adamalbarisyi.mycataloguemovieuiux.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.adamalbarisyi.mycataloguemovieuiux.DetailMovieActivity;
import com.example.adamalbarisyi.mycataloguemovieuiux.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMovieAdapter extends RecyclerView.Adapter<DetailMovieAdapter.ViewHolder> {
    private Cursor list;
    private Context context;
    public static final String BASE_URL_IMG = "http://image.tmdb.org/t/p";

    public DetailMovieAdapter(Context context) {
        this.context = context;

    }

    public void setList(Cursor list) {
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_nowplaying_item, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final NowUpFavMovieItems mov = getItem(position);
        holder.tvTittle.setText(mov.getTitle());
        holder.tvRating.setText("Rating :" + mov.getRating());
        holder.tvPopularity.setText("Popularity : " + mov.getVote());
        holder.tvOverview.setText(mov.getOverview());
        holder.tvReleaseDate.setText(mov.getReleaseDate());
        Glide.with(holder.itemView.getContext())
                .load(BASE_URL_IMG + "/w154" + mov.getPosterPath())
                .into(holder.imgPoster);

        holder.btnFavorite.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void OnItemClicked(View view, int position) {
                Toast.makeText(holder.itemView.getContext(), "Favorite " + mov.getTitle(), Toast.LENGTH_SHORT).show();
            }
        }));
        holder.btnShare.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void OnItemClicked(View view, int position) {
                Toast.makeText(holder.itemView.getContext(), "Share " + mov.getTitle(), Toast.LENGTH_SHORT).show();
            }
        }));

        holder.cvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetail = new Intent(holder.itemView.getContext(), DetailMovieActivity.class);
                intentDetail.putExtra(DetailMovieActivity.MOVIE_ID, mov.getId());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_TITLE, mov.getTitle());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_ORITITLE, mov.getOriTitle());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_ORILANGUAGE, mov.getOriLanguage());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_VOTE, mov.getVote());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_RATING, mov.getRating());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_RELEASE_DATE, mov.getReleaseDate());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_OVERVIEW, mov.getOverview());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_POSTER, mov.getPosterPath());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_BACKDROP, mov.getBackDropPath());
                holder.itemView.getContext().startActivity(intentDetail);
            }
        });

    }


    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.getCount();
    }

    private NowUpFavMovieItems getItem(int position) {
        if (!list.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new NowUpFavMovieItems(list);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_title_now)
        TextView tvTittle;
        @BindView(R.id.tv_item_rating_now)
        TextView tvRating;
        @BindView(R.id.tv_item_popularity_now)
        TextView tvPopularity;
        @BindView(R.id.tv_item_overview_now)
        TextView tvOverview;
        @BindView(R.id.tv_item_release_date_now)
        TextView tvReleaseDate;
        @BindView(R.id.img_poster_movie_now)
        ImageView imgPoster;
        @BindView(R.id.btn_favorite)
        Button btnFavorite;
        @BindView(R.id.btn_share)
        Button btnShare;
        @BindView(R.id.cv_movie)
        RelativeLayout cvDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


