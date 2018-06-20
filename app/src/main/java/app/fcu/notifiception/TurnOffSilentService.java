package app.fcu.notifiception;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class TurnOffSilentService  extends Service {

    public static final String TAG = "Service";

    private Timer timer;
    private TimerTask task;
    private int counter;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        counter = Integer.valueOf(intent.getStringExtra("Counter"))+1;
        //sec to hour (3600 sec)
        counter = counter*10;
        Log.d(TAG, "onStartCommand() executed");
        startTimer();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stopTimer();
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    public void startTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                counter--;
                Log.d(TAG, "startTimer() executed");
                Intent i1 = new Intent();
                i1.setAction("Action");
                i1.putExtra("Counter", String.valueOf(counter));
                sendBroadcast(i1);
            }
        };
        timer.schedule(task, 1000, 1000);
    }

    public void stopTimer() {
        Log.d(TAG, "stopTimer() executed");
        timer.cancel();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
