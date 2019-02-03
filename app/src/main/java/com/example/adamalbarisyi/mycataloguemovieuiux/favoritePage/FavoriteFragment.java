package com.example.adamalbarisyi.mycataloguemovieuiux.favoritePage;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.adamalbarisyi.mycataloguemovieuiux.R;
import com.example.adamalbarisyi.mycataloguemovieuiux.adapter.DetailMovieAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.adamalbarisyi.mycataloguemovieuiux.db.DatabaseContract.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private Context context;
    private Cursor list;
    private DetailMovieAdapter adapter;
    @BindView
  (R.id.rv_Movie_Fav)  RecyclerView rcMovie;
    @BindView(R.id.progressBar_Fav) ProgressBar progressBar;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);
       context = view.getContext();

       adapter = new DetailMovieAdapter(context);
       adapter.setList(list);
       rcMovie.setLayoutManager(new LinearLayoutManager(context));
       rcMovie.setAdapter(adapter);

       new loadDataAsync().execute();
       showToast("Fav Frag onCreate");

       return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showToast("Fav Frag onDestroy");
    }

    @Override
    public void onResume() {
        super.onResume();
        new loadDataAsync().execute();
        showToast("Fav Frag onResume");
    }

    private class loadDataAsync extends AsyncTask<Void,Void,Cursor>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            showToast("Fav Frag onPreExe");
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return context.getContentResolver().query(
                    CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            progressBar.setVisibility(View.GONE);
            showToast("Fav Frag onPostExe");
            list = cursor;

            adapter.setList(list);
            adapter.notifyDataSetChanged();

            if (list.getCount() == 0){
                showToast("Tidak ada data saat ini");
            }else{
                showToast("Total list: "+list.getCount());
            }
        }
    }


    private void showToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}


