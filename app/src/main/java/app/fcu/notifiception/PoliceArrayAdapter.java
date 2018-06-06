package app.fcu.notifiception;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class PoliceArrayAdapter extends ArrayAdapter<PoliceStationData> {

    Context context;

    public PoliceArrayAdapter(@NonNull Context context, List<PoliceStationData> items) {
        super(context, 0, items);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout itemLayout = null;
        if (convertView == null) {
            itemLayout = (LinearLayout) inflater.inflate(R.layout.police_item, null);
        } else {
            itemLayout = (LinearLayout) convertView;
        }

        PoliceStationData item = getItem(position);

        TextView tvName = itemLayout.findViewById(R.id.tv_name);
        tvName.setText(item.getName());
        TextView tvAddress = itemLayout.findViewById(R.id.tv_address);
        tvAddress.setText(item.getAddress());
        TextView tvContact = itemLayout.findViewById(R.id.tv_contact);
        tvContact.setText(item.getContact());
        return itemLayout;
    }
}
