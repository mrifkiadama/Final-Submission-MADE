package com.example.adamalbarisyi.mycataloguemovieuiux.notifications;

import android.content.Context;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;

public class TaskScheduler {
   GcmNetworkManager mGcmNetworkManager;

   public TaskScheduler(Context context) {
      mGcmNetworkManager = GcmNetworkManager.getInstance(context);
   }

   public void createPeriodicTask(){
      Task periodicTask = new PeriodicTask.Builder()
              .setService(ServiceScheduler.class)
              .setFlex(10)
              .setPeriod(3*60*1000)
              .setTag(ServiceScheduler.TAG_TASK)
              .setPersisted(true)
              .build();
    mGcmNetworkManager.schedule(periodicTask);
   }

   public void cancelPeriodicTask(){
      if (mGcmNetworkManager != null){
         mGcmNetworkManager.cancelTask(ServiceScheduler.TAG_TASK,ServiceScheduler.class);
      }
   }
}
