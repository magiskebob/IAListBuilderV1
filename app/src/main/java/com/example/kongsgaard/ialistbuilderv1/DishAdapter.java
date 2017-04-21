package com.example.kongsgaard.ialistbuilderv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by magiskebob on 07-04-2017.
 */

public class DishAdapter  extends ArrayAdapter<DishClass> {
    public DishAdapter(Context context, List<DishClass> dishes) {
        super(context, 0, dishes);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DishClass dish = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dishlistlayout, parent, false);
        }
        // Lookup view for data population
        TextView dishTitle = (TextView) convertView.findViewById(R.id.title);
        TextView dishDesc = (TextView) convertView.findViewById(R.id.description);
        // Populate the data into the template view using the data object


        dishTitle.setText(dish.Title);
        dishDesc.setText(dish.Description);
        // Return the completed view to render on screen
        return convertView;
    }
}
