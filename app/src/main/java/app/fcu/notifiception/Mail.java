package app.fcu.notifiception;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mail extends Fragment {

    EditText etMsg;
    EditText etName;
    Button btnFeedback;
    String tmpName;
    String tmpMsg;

    public Mail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mail, container, false);

        btnFeedback = v.findViewById(R.id.btn_feedback);
        etMsg = v.findViewById(R.id.et_msg);
        etName = v.findViewById(R.id.et_name);
        btnFeedback.setOnClickListener(sendFeedback);

        return v;
    }

    View.OnClickListener sendFeedback = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String msg = etMsg.getText().toString();
            String name = etName.getText().toString();

            if(msg.isEmpty()) {
                Toast.makeText(getContext(), "Plaese complete the required field", Toast.LENGTH_LONG).show();
            } else {
                if(name.isEmpty()) {
                    name = "User";
                }
                sendEmail(msg, name);
            }
        }
    };

    private void sendEmail(String msg, String name) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "xhekitan1997@hotmail.com" });
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from " + name);
        emailIntent.putExtra(Intent.EXTRA_TEXT, msg);

        try {
            startActivity(Intent.createChooser(emailIntent, "Email Sent"));
            Log.i("Email Sent", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(),
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        etMsg.setText("");
        etName.setText("");
    }

    @Override
    public void onPause() {
        super.onPause();
        tmpMsg = etMsg.getText().toString();
        tmpName = etName.getText().toString();
    }

    @Override
    public void onResume() {
        super.onResume();
        etMsg.setText(tmpMsg);
        etName.setText(tmpName);
    }
}
