package com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.SearchPage.MovieItems;
import com.example.adamalbarisyi.mycataloguemovieuiux.R;

import java.util.ArrayList;

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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.movie_row_item, null);
            holder.tvTittle = convertView.findViewById(R.id.tv_item_title);
            holder.tvRating = convertView.findViewById(R.id.tv_item_rating);
            holder.tvPopularity = convertView.findViewById(R.id.tv_item_popularity);
            holder.tvOverview = convertView.findViewById(R.id.tv_item_overview);
            holder.tvReleaseDate = convertView.findViewById(R.id.tv_item_release_date);
            holder.imgPoster = convertView.findViewById(R.id.img_poster_movie);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
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

    private static class ViewHolder {
        TextView tvTittle;
        TextView tvRating;
        TextView tvPopularity;
        TextView tvOverview;
        TextView tvReleaseDate;
        ImageView imgPoster;
    }
}
