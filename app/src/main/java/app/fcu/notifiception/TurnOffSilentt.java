package app.fcu.notifiception;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class TurnOffSilentt extends Fragment {


    public TurnOffSilentt() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View d = inflater.inflate(R.layout.fragment_turn_off_silent, container, false);
        Spinner spHour = d.findViewById(R.id.durationSpinner);
        ArrayAdapter<CharSequence> hAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.hourForSpinner, R.layout.spinner_text);
        hAdapter.setDropDownViewResource(R.layout.spinner_style);
        spHour.setAdapter(hAdapter);
        return d;

    }

}
