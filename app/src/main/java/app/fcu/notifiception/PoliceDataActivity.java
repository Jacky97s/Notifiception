package app.fcu.notifiception;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class PoliceDataActivity extends Fragment {

    public PoliceDataActivity() {
        // Required empty public constructor
    }

    public void getDataFromFirebase() {
        final List<PoliceStationData> lsData = new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    DataSnapshot dsName = ds.child("Name");
                    DataSnapshot dsAddress = ds.child("Address");
                    DataSnapshot dsContact = ds.child("Contact");

                    String dName = String.valueOf(dsName.getValue());
                    String dAddress = String.valueOf(dsAddress.getValue());
                    String dContact = String.valueOf(dsContact.getValue());
                    PoliceStationData psd = new PoliceStationData(dName, dAddress, dContact);
                    lsData.add(psd);
                }
                PoliceArrayAdapter adapter = new PoliceArrayAdapter(getActivity(), lsData);
                ListView lv = Objects.requireNonNull(getView()).findViewById(R.id.listview_psd);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(itemClick);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("Police Cancel", databaseError.getMessage());
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

    AdapterView.OnItemClickListener itemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            PoliceStationData policeStationData = (PoliceStationData) parent.getItemAtPosition(position);
            new getCoordinates().execute(policeStationData.getAddress().replace(" ", "+"));
        }
    };

    @SuppressLint("StaticFieldLeak")
    private class getCoordinates extends AsyncTask<String, Void, String> {

        ProgressDialog dialog = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please Wait...");
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String response;
            try {
                String address = strings[0];
                HttpDataHandler httpDataHandler = new HttpDataHandler();
                String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s", address);
                response = httpDataHandler.getHttpData(url);
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);

                String lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                        .getJSONObject("location").get("lat").toString();

                String lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                        .getJSONObject("location").get("lng").toString();

                Intent intent = new Intent(getContext(), MapsActivity.class);

                intent.putExtra("Py", lat);
                intent.putExtra("Px", lng);
                startActivity(intent);

                if(dialog.isShowing()) {
                    dialog.dismiss();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
