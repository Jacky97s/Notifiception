package app.fcu.notifiception;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TurnOffSilentBroadcastReceiver extends BroadcastReceiver {

    static int id = 3000;
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent newintent = new Intent();
        newintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, newintent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notify = newNotification(context, pendingIntent, "Your ringer mode had changed!");
        notify.flags = Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(id++, notify);
    }

    private Notification newNotification(Context context, PendingIntent pendingIntent, String title) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(title);
        builder.setContentText("Now are in Normal");
        builder.setSmallIcon(R.mipmap.ic_do_not_disturb_off_black_24dp);
        builder.setContentIntent(pendingIntent);

        builder.setWhen(System.currentTimeMillis());
        return builder.build();
    }
}
