package app.fcu.notifiception;


import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
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

//    NotificationManager modeChange;
//    Button troff;

    public TurnOffSilent() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_turn_off_silent, container, false);
//        troff = v.findViewById(R.id.button2);
        Spinner spHour = v.findViewById(R.id.durationSpinner);
        ArrayAdapter<CharSequence> hAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.hourForSpinner, R.layout.spinner_text);
        hAdapter.setDropDownViewResource(R.layout.spinner_style);
        spHour.setAdapter(hAdapter);
//        troff.setOnClickListener(turnOff);

        return v;
    }

//    View.OnClickListener turnOff  = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Toast.makeText(getContext(),
//                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
//            modeChange = (NotificationManager) v.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
//
//                AudioManager audioManager = (AudioManager) v.getContext().getSystemService(Context.AUDIO_SERVICE);
//                audioManager.setRingerMode(0);

//            switch (audioManager.getRingerMode()){
//                case AudioManager.RINGER_MODE_SILENT:
//                    Log.i("Notiception", "Silent Mode");
//                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//                    break;
//            }
//        }
//
//        private void startActivityForResult(Intent intent) {
//        }

//    };

//        private Context getApplicationContext() {
//            return null;
//        }
//
//    private AudioManager findViewById(int button2) {
//        return null;
//    }
}
