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
public class Notifiception extends Fragment {


    public Notifiception() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_notifiception, container, false);
        Spinner spinner = (Spinner) v.findViewById(R.id.timeSpinner);
        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.hourForSpinner, android.R.layout.simple_spinner_item );
        nAdapter.setDropDownViewResource(R.layout.spinner_style);
        spinner.setAdapter(nAdapter);
        return v;
    }

}
