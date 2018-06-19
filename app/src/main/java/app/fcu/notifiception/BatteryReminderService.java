package app.fcu.notifiception;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class BatteryReminderService extends Service {

    String set_level;
    int level;
    int last_level;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // do not receive all available system information (it is a filter!)
        set_level = intent.getStringExtra("SET_LEVEL");
        final IntentFilter battChangeFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        // register our receiver
        this.registerReceiver(this.batteryChangeReceiver, battChangeFilter);
        last_level = 100;
        return super.onStartCommand(intent, flags, startId);
    }

    private final BroadcastReceiver batteryChangeReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            checkBatteryLevel(intent);
        }
    };

    private void checkBatteryLevel(Intent batteryChangeIntent) {
        // some calculations
        final int currLevel = batteryChangeIntent.getIntExtra(
                BatteryManager.EXTRA_LEVEL, -1);
        final int maxLevel = batteryChangeIntent.getIntExtra(
                BatteryManager.EXTRA_SCALE, -1);
        final int percentage = (int) Math.round((currLevel * 100.0) / maxLevel);

        Log.d("MySerive", "current battery level: " + percentage);
        level = Integer.valueOf(set_level);
        // compare level
        if (percentage <= level) {
            Log.d("MySerive", "battery reminder" + set_level);
            if(percentage < last_level)
            Toast.makeText(getBaseContext(), "Hehehe", Toast.LENGTH_SHORT).show();
            last_level = percentage;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
