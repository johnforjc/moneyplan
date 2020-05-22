package com.example.moneyplan;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channel1Id = "channel1ID";
    public static final String channel1Name ="Channel 1";
    public static final String channel2Id = "channel2ID";
    public static final String channel2Name ="Channel 2";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        createChannels();
    }

    private void createChannels() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(channel1Id, channel1Name, NotificationManager.IMPORTANCE_HIGH);
            channel1.enableLights(true);
            channel1.enableVibration(true);
            channel1.setLightColor(R.color.colorPrimary);
            channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(channel1);

            NotificationChannel channel2 = new NotificationChannel(channel2Id, channel2Name, NotificationManager.IMPORTANCE_HIGH);
            channel2.enableLights(true);
            channel2.enableVibration(true);
            channel2.setLightColor(R.color.colorPrimary);
            channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(channel2);
        }
    }

    public NotificationManager getManager()
    {
        if(mManager == null)
        {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }


    public NotificationCompat.Builder getChannel1Notification()
    {
        return new NotificationCompat.Builder(getApplicationContext(), channel1Id)
                .setContentTitle("Catat Keuangan Anda Hari Ini")
                .setContentText("Hai, jangan lupa untuk menuliskan catatan keuangan anda ya")
                .setSmallIcon(R.drawable.ic_notifications);
    }

    public NotificationCompat.Builder getChannel2Notification()
    {
        return new NotificationCompat.Builder(getApplicationContext(), channel1Id)
                .setContentTitle("Moneyplan")
                .setContentText("Hai, jangan lupa membayar tagihan anda")
                .setSmallIcon(R.drawable.ic_notifications);
    }
}
