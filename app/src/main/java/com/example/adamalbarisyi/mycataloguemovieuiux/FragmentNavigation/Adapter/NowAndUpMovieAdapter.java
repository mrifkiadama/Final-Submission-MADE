package com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.CustomOnItemClickListener;
import com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.DetailMovieActivity;
import com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.NowUpMovieItems;
import com.example.adamalbarisyi.mycataloguemovieuiux.R;
import java.util.List;

public class NowAndUpMovieAdapter extends RecyclerView.Adapter<NowAndUpMovieAdapter.ViewHolder> {

    private List<NowUpMovieItems> mData;
    private Context context;
    LayoutInflater layoutInflater;

    public NowAndUpMovieAdapter(List<NowUpMovieItems> mData , Context context) {
        this.context = context;
        this.mData = mData;
        layoutInflater = LayoutInflater.from(context);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_nowplaying_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        final NowUpMovieItems movieItemses = mData.get(position);
        holder.tvTittle.setText(movieItemses.getTitle());
        holder.tvRating.setText("Rating :" + movieItemses.getRating());
        holder.tvPopularity.setText("Popularity : " +movieItemses.getVote());
        holder.tvOverview.setText(movieItemses.getOverview());
        holder.tvReleaseDate.setText(movieItemses.getReleaseDate());
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w154" + movieItemses.getPosterPath())
                .into(holder.imgPoster);

        holder.btnFavorite.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void OnItemClicked(View view, int position) {
                Toast.makeText(context, "Favorite " + movieItemses.getTitle(), Toast.LENGTH_SHORT).show();
            }
        }));
        holder.btnShare.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void OnItemClicked(View view, int position) {
                Toast.makeText(context, "Share " +movieItemses.getTitle(), Toast.LENGTH_SHORT).show();
            }
        }));

        holder.cvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NowUpMovieItems items =  mData.get(position);
                Intent intentDetail = new Intent(context,DetailMovieActivity.class);
                intentDetail.putExtra(DetailMovieActivity.MOVIE_TITLE, items.getTitle());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_ORITITLE, items.getOriTitle());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_ORILANGUAGE, items.getOriLanguage());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_VOTE, items.getVote());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_RATING, items.getRating());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_RELEASE_DATE, items.getReleaseDate());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_OVERVIEW, items.getOverview());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_POSTER,items.getPosterPath());
                intentDetail.putExtra(DetailMovieActivity.MOVIE_BACKDROP, items.getBackDropPath());
                context.startActivity(intentDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTittle;
        public TextView tvRating;
        public TextView tvPopularity;
        public TextView tvOverview;
        public TextView tvReleaseDate;
        public ImageView imgPoster;
        public Button btnFavorite;
        public Button btnShare;
        public RelativeLayout cvDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTittle = itemView.findViewById(R.id.tv_item_title_now);
            tvRating = itemView.findViewById(R.id.tv_item_rating_now);
            tvPopularity = itemView.findViewById(R.id.tv_item_popularity_now);
            tvOverview = itemView.findViewById(R.id.tv_item_overview_now);
            tvReleaseDate = itemView.findViewById(R.id.tv_item_release_date_now);
            imgPoster = itemView.findViewById(R.id.img_poster_movie_now);
            btnFavorite = itemView.findViewById(R.id.btn_favorite);
            btnShare = itemView.findViewById(R.id.btn_share);
            cvDetail = itemView.findViewById(R.id.cv_movie);
        }
    }
}



