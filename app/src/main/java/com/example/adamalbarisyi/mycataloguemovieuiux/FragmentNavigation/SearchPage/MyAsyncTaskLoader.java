package com.example.adamalbarisyi.mycataloguemovieuiux.FragmentNavigation.SearchPage;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import com.example.adamalbarisyi.mycataloguemovieuiux.BuildConfig;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItems>> {

    private ArrayList<MovieItems> mData;
    private boolean mHasResult;
    private String mTitleName;

    public MyAsyncTaskLoader(final Context context,String titleName) {
        super(context);
        onContentChanged();
        this.mTitleName = titleName;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
            else if (mHasResult)
                deliverResult(mData);
    }

    @Override
    public void deliverResult(final ArrayList<MovieItems> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult){
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private void onReleaseResources(ArrayList<MovieItems> mData) {
    }


    String API_KEY = BuildConfig.ApiKey;
    String BASE_URL = BuildConfig.BaseUrl;
    public static final String API_URL ="search/movie?api_key=";

    @Override
    public ArrayList<MovieItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<MovieItems> movieItemses = new ArrayList<>();
        String url = BASE_URL+API_URL+API_KEY+"&language=en-US&query=("+mTitleName+")";
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length() ; i++){
                        JSONObject movie = list.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movie);
                        movieItemses.add(movieItems);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movieItemses;
    }
}
