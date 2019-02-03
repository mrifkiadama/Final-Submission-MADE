package com.example.adamalbarisyi.mycataloguemovieuiux.searchPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adamalbarisyi.mycataloguemovieuiux.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItems item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    @Override
    public MovieItems getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.movie_row_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.tvTittle.setText(mData.get(position).getTitle());
        holder.tvRating.setText("Rating :" + mData.get(position).getRating());
        holder.tvPopularity.setText("Popularity : " + mData.get(position).getVote());
        holder.tvOverview.setText(mData.get(position).getOverview());
        holder.tvReleaseDate.setText(mData.get(position).getReleaseDate());
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w154" + mData.get(position).getPosterPath())
                .into(holder.imgPoster);


        return convertView;
    }

     static class ViewHolder {
        @BindView
                (R.id.tv_item_title)
        TextView tvTittle;
        @BindView(R.id.tv_item_rating)
        TextView tvRating;
        @BindView(R.id.tv_item_popularity)
        TextView tvPopularity;
        @BindView(R.id.tv_item_overview)
        TextView tvOverview;
        @BindView(R.id.tv_item_release_date)
        TextView tvReleaseDate;
        @BindView(R.id.img_poster_movie)
        ImageView imgPoster;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
