package com.example.kongsgaard.ialistbuilderv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kongsgaard on 05-02-2017.
 */

public class CardClassArrayAdapter extends ArrayAdapter<CardClass> {
    public CardClassArrayAdapter(Context context, List<CardClass> cards) {
        super(context, 0, cards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CardClass card = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cardlistlayout, parent, false);
        }
        // Lookup view for data population
        TextView cardName = (TextView) convertView.findViewById(R.id.cardName);
        TextView cardCost = (TextView) convertView.findViewById(R.id.cardCost);
        // Populate the data into the template view using the data object
        cardName.setText(card.Name);
        cardCost.setText(String.valueOf(card.PointCost));
        // Return the completed view to render on screen
        return convertView;
    }
}