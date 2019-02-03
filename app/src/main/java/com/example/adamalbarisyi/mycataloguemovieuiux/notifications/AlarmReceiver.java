package com.example.adamalbarisyi.mycataloguemovieuiux.notifications;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.adamalbarisyi.mycataloguemovieuiux.MainActivity;
import com.example.adamalbarisyi.mycataloguemovieuiux.R;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {

    private final int NOTIF_ID_REPEATING = 101;

    public AlarmReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int notifId = NOTIF_ID_REPEATING;
        showAlarmNotification(context,notifId);
    }


    private void showAlarmNotification(Context context,int notifId){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound  = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,notifId,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        String appName = context.getString(R.string.app_name);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(Resources.getSystem(),R.mipmap.ic_launcher))
                .setContentTitle(appName)
                .setContentText(appName + " New Update Movie .....")
                .setColor(ContextCompat.getColor(context,android.R.color.transparent))
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setSound(alarmSound);
        notificationManager.notify(notifId,builder.build());
    }

    public void setRepeatingAlarm(Context context,String time){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,AlarmReceiver.class);

        String timeArray[] = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE,Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND,0);

        int requestCode = NOTIF_ID_REPEATING;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,requestCode,intent,0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(context, "Repeating alarm set up", Toast.LENGTH_SHORT).show();
    }

    public void cancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,AlarmReceiver.class);

        int requestCode = NOTIF_ID_REPEATING;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,requestCode,intent,0);
        alarmManager.cancel(pendingIntent);
    }

}
