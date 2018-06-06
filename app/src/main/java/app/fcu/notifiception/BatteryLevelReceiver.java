package app.fcu.notifiception;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class BatteryLevelReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    }
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//
//        builder.setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Notifiception Activated!")
//                .setContentText("Your Battery is low")
//                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
//                .setContentInfo("Info");
//
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(1, builder.build());
//    }
}
