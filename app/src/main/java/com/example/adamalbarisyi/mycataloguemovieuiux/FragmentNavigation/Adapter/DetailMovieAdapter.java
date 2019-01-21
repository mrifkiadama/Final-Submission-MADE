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

public class DetailMovieAdapter extends BaseAdapter {

    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;
    public static final String BASE_URL_IMG = "http://image.tmdb.org/t/p";

    public DetailMovieAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItems items) {
        mData.add(items);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new DetailMovieAdapter.ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_detail_movie,null);
            holder.imgBackdrop = convertView.findViewById(R.id.img_backdrop_movie);
            holder.imgPoster = convertView.findViewById(R.id.img_poster_movie);
            holder.tvTitle = convertView.findViewById(R.id.tv_title_movie);
            holder.tvOriTitle = convertView.findViewById(R.id.tv_original_title);
            holder.tvOriLanguage = convertView.findViewById(R.id.tv_original_language);
            holder.tvVote = convertView.findViewById(R.id.tv_detail_vote);
            holder.tvRating = convertView.findViewById(R.id.tv_detail_rating);
            holder.tvReleaseDate = convertView.findViewById(R.id.tv_release_date);
            holder.tvOverview = convertView.findViewById(R.id.tv_overview);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.tvOriTitle.setText(mData.get(position).getOriTitle());
        holder.tvOriLanguage.setText(mData.get(position).getOriLanguage());
        holder.tvVote.setText("Vote" + mData.get(position).getVote());
        holder.tvRating.setText("Rating "+ mData.get(position).getRating());
        holder.tvReleaseDate.setText(mData.get(position).getReleaseDate());
        holder.tvOverview.setText(mData.get(position).getOverview());
        Glide.with(context)
                .load(BASE_URL_IMG + "/w500" + mData.get(position).getBackDropPath())
                .into(holder.imgBackdrop);

        Glide.with(context)
                .load(BASE_URL_IMG + "/w154" + mData.get(position).getPosterPath())
                .into(holder.imgPoster);
        return convertView;
    }

    private static class ViewHolder {
        ImageView imgBackdrop;
        ImageView imgPoster;
        TextView tvTitle;
        TextView tvOriTitle;
        TextView tvOriLanguage;
        TextView tvVote;
        TextView tvRating;
        TextView tvReleaseDate;
        TextView tvOverview;
    }
}
