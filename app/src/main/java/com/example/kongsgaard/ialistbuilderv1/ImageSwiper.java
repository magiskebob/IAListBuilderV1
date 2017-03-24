package com.example.kongsgaard.ialistbuilderv1;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ImageSwiper extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private GestureDetector gestureDetector;
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
        gestureDetector = new GestureDetector(this, this);
    }
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    @Override
    public boolean onDown(MotionEvent e) {
        CharSequence text = "Swipe left or right to view next picture";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        CharSequence text = "Swipe left or right to view next picture";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        CharSequence text = "Swipe left or right to view next picture";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
        return true;
    }
}
