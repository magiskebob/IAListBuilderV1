package com.example.kongsgaard.ialistbuilderv1;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ImageSwiper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageswiper);
        Bundle intent = getIntent().getExtras();
        List<Integer> cardImages = intent.getIntegerArrayList("int_list");
        int position = intent.getInt("card_position");
        ViewPager swiper = (ViewPager)findViewById(R.id.viewswiper);
        ImageAdapter adapter = new ImageAdapter(this, cardImages);
        swiper.setAdapter(adapter);
        swiper.setCurrentItem(position, false);
    }
}
