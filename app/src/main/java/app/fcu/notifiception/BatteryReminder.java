package app.fcu.notifiception;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;


/**
 * A simple {@link Fragment} subclass.
 */
public class BatteryReminder extends Fragment{

    Handler handler;
    Runnable runnable;
    int setLevel;

    public BatteryReminder() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_battery_reminder, container, false);
//        SeekBar seekBar = v.findViewById(R.id.br_seekBar);
//        final TextView seekValue = v.findViewById(R.id.seekValue);
//
//        setLevel = seekBar.getProgress();
//        seekValue.setText(String.valueOf(setLevel) + "%");
//
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                seekValue.setText(String.valueOf(progress) + "%");
//                setLevel = progress;
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                int level = (int) batteryLevel();
//                handler.postDelayed(runnable, 0);
//                if (level <= setLevel) {
//                    Toast hello_ = Toast.makeText(getContext(), "Battery low", Toast.LENGTH_SHORT);
//                    hello_.show();
//                }
//            }
//        };
//
//        handler = new Handler();
//        handler.postDelayed(runnable, 0);

        return v;
    }

//    public float batteryLevel() {
//        Intent batteryIntent = getActivity().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
//        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
//        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
//
//        if(level == -1 || scale == -1) {
//            return 50.0f;
//        }
//
//        return ((float) level/ (float) scale) * 100.0f;
//    }
}
