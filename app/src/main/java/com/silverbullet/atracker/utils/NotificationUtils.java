package com.silverbullet.atracker.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.silverbullet.atracker.R;
import com.silverbullet.atracker.ui.fragment.TrackingFragment;

public class NotificationUtils {

    public static final String TRACKING_N_CHANNEL_ID = "tracking_channel";
    public static final String TRACKING_N_CHANNEL_NAME = "Tacking";
    public static final int TRACKING_NOTIFICATION_ID = 1;

    @NonNull
    public static Notification buildTrackingNotification(Context context) {
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                TrackingFragment.getTrackingFragmentIntent(context),
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        return new NotificationCompat
                .Builder(context, TRACKING_N_CHANNEL_ID)
                .setAutoCancel(false)
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_run)
                .setContentTitle("A Running")
                .setContentText("00:00:00")
                .setContentIntent(pendingIntent)
                .build();
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // No need to create notification channel
            return;
        }
        NotificationManager notificationManager = ContextCompat.getSystemService(context, NotificationManager.class);
        if (notificationManager == null) {
            return;
        }
        NotificationChannel channel = new NotificationChannel(
                TRACKING_N_CHANNEL_ID,
                TRACKING_N_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
        );
        notificationManager.createNotificationChannel(channel);
    }
}
