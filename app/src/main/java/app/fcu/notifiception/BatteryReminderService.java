package app.fcu.notifiception;

import android.app.AlertDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Config;
import android.util.Log;
import android.view.WindowManager;

public class BatteryReminderService extends Service {

    String set_level;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // do not receive all available system information (it is a filter!)
        set_level = intent.getStringExtra("SET_LEVEL");
        final IntentFilter battChangeFilter = new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED);
        // register our receiver
        this.registerReceiver(this.batteryChangeReceiver, battChangeFilter);
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
        int level = Integer.valueOf(set_level);
        // compare level
        if (percentage < level) {
            Log.d("MySerive", "battery reminder" + set_level);
//            showDialog("Low Power Alert!", "Your battery level is under " + level + "%");
//            showBox(getBaseContext());
        }
    }

//    private void showBox(final Context context)
//    {
//        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
//        dialog.setTitle("提示");
//        dialog.setIcon(android.R.drawable.ic_dialog_info);
//        dialog.setMessage("完成次数: 5\n"+"总计次数: 6");
//        dialog.setPositiveButton("停止测试",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // TODO Auto-generated method stub
//                //点击后跳转到某个Activity
//                Intent result = new Intent(context, DialogActivity.class);
//                result.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(result);
//            }
//        });
//        AlertDialog mDialog=dialog.create();
//        mDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);//设定为系统级警告，关键
//        mDialog.show();
//    }
//    public void showDialog(String title,String message){
//        Log.i("service","show dialog function");
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.setCancelable(false);
//        builder.setPositiveButton(AlertDialog.BUTTON_NEUTRAL, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                dialog.dismiss();
//            }
//        });
//        AlertDialog alert = builder.create();
//        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);//設定提示框為系統提示框
//        alert.show();
//    }

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
