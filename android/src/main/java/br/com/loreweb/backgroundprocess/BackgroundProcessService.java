package br.com.loreweb.backgroundprocess;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.ReactActivity;

public class BackgroundProcessService extends Service {

    private static final int SERVICE_NOTIFICATION_ID = 12345;
    private static final String CHANNEL_ID = "loreweb-background-process";

    private Handler handler = new Handler();
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            Context context = getApplicationContext();
            Intent myIntent = new Intent(context, BackgroundProcessEventService.class);
            context.startService(myIntent);
            HeadlessJsTaskService.acquireWakeLockNow(context);
            handler.postDelayed(this, 5000);
        }
    };
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "loreweb-background-process", importance);
            channel.setDescription("Descricao da notificacao que esta sendo apresentada.");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.runnableCode);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle extras = intent.getExtras();
        this.handler.post(this.runnableCode);
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, ReactActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(extras.getString("msgTitle"))
                .setContentText(extras.getString("msgBody"))
                .setContentIntent(contentIntent)
                .setOngoing(true);

        Context context = getApplicationContext();

        Resources res = context.getResources();
        String packageName = context.getPackageName();


        int smallIconResId;
        int largeIconResId;

        String largeIcon = extras.getString("largeIcon");
        String smallIcon = extras.getString("smallIcon");
            smallIconResId = (smallIcon != null)
                    ? res.getIdentifier(smallIcon, "mipmap", packageName)
                    : res.getIdentifier("ic_notification", "mipmap", packageName);

        if (smallIconResId == 0) {
            smallIconResId = res.getIdentifier("ic_launcher", "mipmap", packageName);

            if (smallIconResId == 0) {
                smallIconResId = android.R.drawable.ic_dialog_info;
            }
        }

        if (largeIcon != null) {
            largeIconResId = res.getIdentifier(largeIcon, "mipmap", packageName);
        } else {
            largeIconResId = res.getIdentifier("ic_launcher", "mipmap", packageName);
        }

        Bitmap largeIconBitmap = BitmapFactory.decodeResource(res, largeIconResId);

        if (largeIconResId != 0 && (largeIcon != null || Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)) {
            notification.setLargeIcon(largeIconBitmap);
        }

        notification.setSmallIcon(smallIconResId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notification.setCategory(NotificationCompat.CATEGORY_CALL);
            String color = extras.getString("colorIcon");

            if (color != null) {
                notification.setColor(Color.parseColor(color));
            } else {
                notification.setColor(Color.parseColor("#000000"));
            }
        }


        startForeground(SERVICE_NOTIFICATION_ID, notification.build());
        return START_STICKY;
    }

}
