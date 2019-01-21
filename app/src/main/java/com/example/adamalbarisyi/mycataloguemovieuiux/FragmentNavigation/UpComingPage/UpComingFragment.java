package com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.UpComingPage;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adamalbarisyi.mycataloguemovieuiux.BuildConfig;
import com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.Adapter.NowAndUpMovieAdapter;
import com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.NowUpMovieItems;
import com.example.adamalbarisyi.mycataloguemovieuiux.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingFragment extends Fragment {

    private RecyclerView rvMovie;
    private RecyclerView.Adapter adapter;
    private List<NowUpMovieItems> mData;

    private static final String API_URL = BuildConfig.MovieUrl + "upcoming?api_key=" + BuildConfig.ApiKey + "&language=en-US";


    public UpComingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);
        rvMovie = (RecyclerView) view.findViewById(R.id.rc_movie);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        mData = new ArrayList<>();

        loadUrlData();

        return view;
    }

    private void loadUrlData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMax(100);
        progressDialog.setMessage("Loading Data.....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        NowUpMovieItems items = new NowUpMovieItems();
                        JSONObject data = list.getJSONObject(i);

                        items.setTitle(data.getString("title"));
                        items.setRating(data.getString("vote_average"));
                        items.setVote(data.getString("vote_count"));
                        items.setOverview(data.getString("overview"));
                        items.setReleaseDate(data.getString("release_date"));
                        items.setPosterPath(data.getString("poster_path"));
                        items.setBackDropPath(data.getString("backdrop_path"));
                        items.setOriTitle(data.getString("original_title"));
                        items.setOriLanguage(data.getString("original_language"));
                        mData.add(items);
                    }

                    adapter = new NowAndUpMovieAdapter(mData, getActivity());
                    rvMovie.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Erorr" + error.toString(), Toast.LENGTH_SHORT).show();
                loadUrlData();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
