package com.example.kongsgaard.ialistbuilderv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by magiskebob on 11-02-2017.
 */

public class ArmyListAdapter extends ArrayAdapter<ArmyList> {
    public ArmyListAdapter(Context context, List<ArmyList> armylists) {
        super(context, 0, armylists);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ArmyList army = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.armylistlayout, parent, false);
        }
        // Lookup view for data population
        TextView armyName = (TextView) convertView.findViewById(R.id.armyname);
        TextView armyCost = (TextView) convertView.findViewById(R.id.armycost);
        // Populate the data into the template view using the data object

        armyName.setText(army.Name);
        armyCost.setText(String.valueOf(army.TotalCost));
        // Return the completed view to render on screen
        return convertView;
    }
}
