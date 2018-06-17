package app.fcu.notifiception;


import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notifiception extends Fragment {
    public static final String TAG = "Service";

    public Notifiception() {
        // Required empty public constructor
    }

    private Switch startService;

    private Switch stopService;

    int counter;
    int num;

    Spinner spinner;
    Switch msgSwitch;


    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceiver() executed");
            num = Integer.valueOf(intent.getStringExtra("Counter"));
            if (num < counter) {
                Toast toast = Toast.makeText(getActivity(), "Time to check your new msg", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notifiception, container, false);
        spinner = v.findViewById(R.id.timeSpinner);
        msgSwitch = v.findViewById(R.id.msgSwitch);

        //load xml
        SharedPreferences pref = getActivity().getSharedPreferences("Notifiception", Context.MODE_PRIVATE);
        int spinnerPosition = pref.getInt("Counter", 5);
        boolean switchState = pref.getBoolean("OnOff", true);
        Log.d(TAG, "spinnerPos: " + spinnerPosition);

        msgSwitch.setOnCheckedChangeListener(state);
        msgSwitch.setChecked(switchState);

        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.hourForSpinner,
                R.layout.spinner_text);
        nAdapter.setDropDownViewResource(R.layout.spinner_style);
        spinner.setAdapter(nAdapter);
        spinner.setSelection(spinnerPosition, true);
        counter = spinner.getSelectedItemPosition();
        return v;
    }

    CompoundButton.OnCheckedChangeListener state = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
            Log.d(TAG, "onCheckedChanged() executed");
            if (check) {

                //save
                SharedPreferences pref = getActivity().getSharedPreferences("Notifiception", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = pref.edit();
                prefEdit.putInt("Counter", counter);
                prefEdit.putBoolean("OnOff", true);
                prefEdit.commit();

                //start timer
//                getActivity().registerReceiver(receiver, new IntentFilter("Action"));
                Intent startIntent = new Intent(getActivity(), NotificeptionService.class);
                startIntent.putExtra("Counter", String.valueOf(counter));
                getActivity().startService(startIntent);


            } else {
                //save
                SharedPreferences pref = getActivity().getSharedPreferences("Notifiception", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = pref.edit();
                prefEdit.putInt("Counter", counter);
                prefEdit.putBoolean("OnOff", false);
                prefEdit.commit();

                //stop timer
                Intent stopIntent = new Intent(getActivity(), NotificeptionService.class);
                getActivity().stopService(stopIntent);
            }

        }
    };
}
