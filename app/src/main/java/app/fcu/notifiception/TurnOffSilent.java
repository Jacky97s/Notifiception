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
public class TurnOffSilent extends Fragment {


    public TurnOffSilent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View d = inflater.inflate(R.layout.fragment_turn_off_silent, container, false);
        Spinner spinner = (Spinner) d.findViewById(R.id.durationSpinner);
        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.hourForSpinner, android.R.layout.simple_spinner_item );
        nAdapter.setDropDownViewResource(R.layout.spinner_style);
        spinner.setAdapter(nAdapter);
        return d;

    }

}
