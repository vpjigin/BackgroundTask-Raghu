package com.example.raghuapp.foregroundservices;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.raghuapp.R;

public class MyForegroundService extends Service {

    private static int id = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        /*NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "125")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Background task")
                .setContentText("started")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


                startForeground(1,builder.getNotification());
        return START_STICKY;*/

        String NOTIFICATION_CHANNEL_ID = "com.example.raghuapp";
        /*String NOTIFICATION_CHANNEL_ID = "com.example.raghuapp";
        String channelName = "My Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName,
                NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);*/

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();


        startForeground(2, notification);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),id+"",Toast.LENGTH_SHORT).show();

                id++;
                handler.postDelayed(this, 4000);
            }
        },4000);
        return START_STICKY;
    }

}
