package com.example.adamalbarisyi.mycataloguemovieuiux.notifications;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.adamalbarisyi.mycataloguemovieuiux.BuildConfig;
import com.example.adamalbarisyi.mycataloguemovieuiux.R;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ServiceScheduler extends GcmTaskService {

    public static final String TAG = "GetUpcoming";
    public static final String TAG_TASK = "GetUpcomingTask";

    @Override
    public int onRunTask(TaskParams taskParams) {
       int result = 0;
       if (taskParams.getTag().equals(TAG_TASK)){
           getUpcomingMovie();
           result = GcmNetworkManager.RESULT_SUCCESS;
       }
       return result;
    }

    private static final String API_URL = BuildConfig.MovieUrl + "upcoming?api_key=" + BuildConfig.ApiKey + "&language=en-US";


    private void getUpcomingMovie(){
        Log.d("GetUpcoming","Running");
        SyncHttpClient client = new SyncHttpClient();
        String url =API_URL;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                Log.d(TAG,result);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i=0; i<list.length(); i++ ){
                        String title = responseObject.getJSONArray("results").getJSONObject(i).getString("title");
                        String message = title + "Today Release !!!";
                        int notifId = 100;
                        showNotification(getApplicationContext(),title,message,notifId);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Get UpComing","Failed!!!");
            }
        });
    }

    @Override
    public void onInitializeTasks() {
        super.onInitializeTasks();
        TaskScheduler taskScheduler = new TaskScheduler(this);
        taskScheduler.cancelPeriodicTask();
    }

    private void showNotification(Context context,String title,String message,int notifId){
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.black))
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setSound(alarmSound);
        notificationManagerCompat.notify(notifId, builder.build());
    }

}
