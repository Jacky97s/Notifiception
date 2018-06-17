package app.fcu.notifiception;


import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.AUDIO_SERVICE;



/**
 * A simple {@link Fragment} subclass.
 */
public class TurnOffSilent extends Fragment {

 AudioManager modeChange;
 Button troff;

    public TurnOffSilent() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_turn_off_silent, container, false);
        troff = v.findViewById(R.id.button2);
        modeChange = (AudioManager) v.getContext().getSystemService(Context.AUDIO_SERVICE);
        Spinner spHour = v.findViewById(R.id.durationSpinner);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TURNOFF_SLIENT", Context.MODE_PRIVATE);
        int spinnerPosition = sharedPreferences.getInt("TURN_SLIENT", 4);
        String [] hourForSpinner = getResources().getStringArray(R.array.hourForSpinner);


        ArrayAdapter<CharSequence> hAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.hourForSpinner, R.layout.spinner_text);
        hAdapter.setDropDownViewResource(R.layout.spinner_style);
        spHour.setAdapter(hAdapter);




        troff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeChange.setRingerMode(1);
            }
        });

        return v;
    }

//    troff
//            Toast.makeText(getContext(),
//                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
//                AudioManager audioManager = (AudioManager) v.getContext().getSystemService(Context.AUDIO_SERVICE);
//                audioManager.setRingerMode(1);

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("TURNOFF_SILENT", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("TURNOFF_SILENT",  0);
        edit.commit();

    }
}