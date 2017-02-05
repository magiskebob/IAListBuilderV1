package com.example.kongsgaard.ialistbuilderv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ViewCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);
        ImageView selectedCard = (ImageView) findViewById(R.id.cardView);
        Bundle intent = getIntent().getExtras();
        int image = intent.getInt("cardpath");
        selectedCard.setImageResource(image);
    }
}
