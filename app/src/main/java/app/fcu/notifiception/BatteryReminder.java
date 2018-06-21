package app.fcu.notifiception;


import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

    int setLevel;
    private BatteryReminderService service;

    public BatteryReminder() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_battery_reminder, container, false);
        SeekBar seekBar = v.findViewById(R.id.br_seekBar);
        final TextView seekValue = v.findViewById(R.id.seekValue);

        Intent intent = new Intent(getActivity(),BatteryReminderService.class);
        String set_Level = String.valueOf(setLevel);
        intent.putExtra("SET_LEVEL", set_Level);

        if( service == null) {
            getActivity().startService(intent);
        } else {
            getActivity().stopService(intent);
            getActivity().startService(intent);
        }

        //load
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("REMINDER", Context.MODE_PRIVATE);
        int percentage_battery = sharedPreferences.getInt("BATTERY_REMINDER", 20);
        seekValue.setText(String.valueOf(percentage_battery) + "%");
        setLevel = seekBar.getProgress();
        seekBar.setProgress(percentage_battery);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekValue.setText(String.valueOf(progress) + "%");
                setLevel = progress;

                Intent intent = new Intent(getActivity(),BatteryReminderService.class);
                String set_Level = String.valueOf(setLevel);
                intent.putExtra("SET_LEVEL", set_Level);

                if( service == null) {
                    getActivity().startService(intent);
                } else {
                    getActivity().stopService(intent);
                    getActivity().startService(intent);
                }
                //save
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("REMINDER", Context.MODE_PRIVATE);
                SharedPreferences.Editor preEdit = sharedPreferences.edit();
                preEdit.putInt("BATTERY_REMINDER", progress);
                preEdit.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return v;
    }
}
