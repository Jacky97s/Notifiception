package app.fcu.notifiception;


import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.AUDIO_SERVICE;
import static app.fcu.notifiception.Notifiception.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class TurnOffSilent extends Fragment {

    public static final String TAG = "Service";
    private Switch startService;
    private Switch stopService;

     AudioManager modeChange;
     Button troff;

     int time;
     Spinner spHour;
     Switch swMsg;
     boolean onOff;
     String selectedHour;

    public TurnOffSilent() {
        // Required empty public constructor

    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            modeChange = (AudioManager)getContext().getSystemService(Context.AUDIO_SERVICE);
            Log.d(TAG, "onReceiver() executed");
            time = Integer.valueOf(intent.getStringExtra("Counter"));
            if (time <= 0) {
                intent.setAction("TOS_Notification");
                context.sendBroadcast(intent);
                modeChange.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                //times up, stop timer & turn off
                onOff = false;
                swMsg.setChecked(onOff);
                swMsg.setOnCheckedChangeListener(state);
                Intent stopIntent = new Intent(getActivity(), NotificeptionService.class);
                getActivity().stopService(stopIntent);
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_turn_off_silent, container, false);
        spHour = v.findViewById(R.id.durationSpinner);
        swMsg = v.findViewById(R.id.switch1);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TURNOFF_SLIENT", Context.MODE_PRIVATE);
        String Name = sharedPreferences.getString("SelectedName", "");
        Log.d(TAG, "get_hour =  " + Name);
        onOff = sharedPreferences.getBoolean("OnOff", false);
        int spinnerPosition = sharedPreferences.getInt("Counter", -1);

        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.hourForSpinner,
                R.layout.spinner_text);
        nAdapter.setDropDownViewResource(R.layout.spinner_style);
        spHour.setAdapter(nAdapter);
        spHour.setOnItemSelectedListener(spinnerState);
        if(spinnerPosition != -1){
            spHour.setSelection(spinnerPosition);
        }
        Log.d(TAG, "spinnerPos: " + spHour.getSelectedItemPosition());

        swMsg.setOnCheckedChangeListener(state);
        swMsg.setChecked(onOff);

        return v;
    }

//    troff
//            Toast.makeText(getContext(),
//                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
//                AudioManager audioManager = (AudioManager) v.getContext().getSystemService(Context.AUDIO_SERVICE);
//                audioManager.setRingerMode(1);
AdapterView.OnItemSelectedListener spinnerState = new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(TAG, "onItemSelected() executed ");
        time = spHour.getSelectedItemPosition();
        SharedPreferences pref = getActivity().getSharedPreferences("TURNOFF_SLIENT", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = pref.edit();

        selectedHour = adapterView.getItemAtPosition(time).toString();
        prefEdit.putInt("Counter", time);
        prefEdit.putString("SelectedName", selectedHour);
        Log.d(TAG, "Selected_hour =  " + selectedHour);
        prefEdit.apply();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
};

    CompoundButton.OnCheckedChangeListener state = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
            Log.d(TAG, "onCheckedChanged() executed");
            if (check) {
                Log.d(TAG, "onCheckedChanged: TRUE");
                //save
                SharedPreferences pref = getActivity().getSharedPreferences("TURNOFF_SLIENT", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = pref.edit();
                prefEdit.putBoolean("OnOff", true);
                prefEdit.apply();

                //start timer
                getActivity().registerReceiver(receiver, new IntentFilter("Action"));
                Intent startIntent = new Intent(getActivity(), TurnOffSilentService.class);
                startIntent.putExtra("Counter", String.valueOf(time));
                getActivity().startService(startIntent);


            } else {
                Log.d(TAG, "onCheckedChanged: FALSE");
                //save
                SharedPreferences pref = getActivity().getSharedPreferences("TURNOFF_SLIENT", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = pref.edit();
                prefEdit.putBoolean("OnOff", false);
                prefEdit.apply();

                //stop timer
                Intent stopIntent = new Intent(getActivity(), TurnOffSilentService.class);
                getActivity().stopService(stopIntent);
            }

        }
    };
}