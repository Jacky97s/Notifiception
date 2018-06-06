package app.fcu.notifiception;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PoliceData extends Fragment {

    public static final int LIST_DATA = 1;

    public PoliceData() {
        // Required empty public constructor
    }

    private void getDataFromFirebase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                List<PoliceStationData> lsData = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    DataSnapshot dsName = ds.child("Name");
                    DataSnapshot dsAddress = ds.child("Address");
                    DataSnapshot dsContact = ds.child("Contact");

                    String dName = String.valueOf(dsName.getValue());
                    String dAddress = String.valueOf(dsAddress.getValue());
                    String dContact = String.valueOf(dsContact.getValue());

                    PoliceStationData psd = new PoliceStationData(dName, dAddress, dContact);
                    lsData.add(psd);
                    Log.v("PSD", dName + ";" + dAddress + ";" + dContact);

                    List<PoliceStationData> psdlist = lsData;
                    PoliceArrayAdapter adapter = new PoliceArrayAdapter(getActivity(), psdlist);
                    ListView lv = getView().findViewById(R.id.listview_psd);
                    lv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("Hotel_Cancel", databaseError.getMessage());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_police_data, container, false);
        getDataFromFirebase();
        return v;
    }
}
