package com.example.adamalbarisyi.mycataloguemovieuiux.searchPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.adamalbarisyi.mycataloguemovieuiux.DetailMovieActivity;
import com.example.adamalbarisyi.mycataloguemovieuiux.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {

    @BindView
   (R.id.lv_movie) ListView lvMovie;
    MovieAdapter adapter;
    @BindView(R.id.edt_movie_search) EditText editMovieSearch;
    @BindView(R.id.btn_search) Button btnSearch;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        adapter = new MovieAdapter(getActivity());
        adapter.notifyDataSetChanged();

        btnSearch.setOnClickListener(myListener);

        lvMovie.setAdapter(adapter);
        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {

                    MovieItems  items = (MovieItems) parent.getItemAtPosition(position);
                    Intent intentDetail = new Intent(getActivity(),DetailMovieActivity.class);
                    intentDetail.putExtra(DetailMovieActivity.MOVIE_ID,items.getId());
                    intentDetail.putExtra(DetailMovieActivity.MOVIE_TITLE, items.getTitle());
                    intentDetail.putExtra(DetailMovieActivity.MOVIE_ORITITLE, items.getOriTitle());
                    intentDetail.putExtra(DetailMovieActivity.MOVIE_ORILANGUAGE, items.getOriLanguage());
                    intentDetail.putExtra(DetailMovieActivity.MOVIE_VOTE, items.getVote());
                    intentDetail.putExtra(DetailMovieActivity.MOVIE_RATING, items.getRating());
                    intentDetail.putExtra(DetailMovieActivity.MOVIE_RELEASE_DATE, items.getReleaseDate());
                    intentDetail.putExtra(DetailMovieActivity.MOVIE_OVERVIEW, items.getOverview());
                    intentDetail.putExtra(DetailMovieActivity.MOVIE_POSTER,items.getPosterPath());
                    intentDetail.putExtra(DetailMovieActivity.MOVIE_BACKDROP, items.getBackDropPath());
                    startActivity(intentDetail);
                }

        });
        String movie = editMovieSearch.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE,movie);
        getLoaderManager().initLoader(0,bundle,this);
        return view;
    }


    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id,  Bundle args) {
        String movieSearch = " ";
        if (args != null){
            movieSearch = args.getString(EXTRAS_MOVIE);
        }
        return new MyAsyncTaskLoader( getActivity(),movieSearch);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset( Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String movie = editMovieSearch.getText().toString();
            if (TextUtils.isEmpty(movie))
                return;
            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE,movie);
            getLoaderManager().restartLoader(0,bundle,SearchFragment.this);
        }
    };
}
