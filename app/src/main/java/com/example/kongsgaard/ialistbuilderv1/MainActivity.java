package com.example.kongsgaard.ialistbuilderv1;

import android.content.Intent;
import android.database.Cursor;
import android.os.DropBoxManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    DataBaseHelper myOperator;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetector = new GestureDetector(this, this);
        myOperator = new DataBaseHelper(this);
        try {

            myOperator.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        //myOperator.openDataBase();
      //  TestAdapter mDbHelper = new TestAdapter(this);
      //  mDbHelper.createDatabase();
      //  mDbHelper.open();

       // Cursor testdata = mDbHelper.getTestData();

       // mDbHelper.close();

    }
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    public void navigateToFactionCards(View view) {
        Intent intent = new Intent(this, CardsActivity.class);
        startActivity(intent);

    }

    public void navigateToArmyLists(View view) {
        Intent intent = new Intent(this, ArmyListsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onDown(MotionEvent e) {
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
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean leftSwipe = e1.getX() > e2.getX();
        boolean rightSwipe = e1.getX()< e2.getX();
        // Log.d(TAG, "onFling left: " + leftSwipe);
        if (rightSwipe) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else
        if (leftSwipe) {
            Intent intent = new Intent(this, StatestikActivity.class);
            startActivity(intent);
        }

        return true; // done
    }
    public void listDishes(View view){
        Intent intent = new Intent(this,GetDishesActivity.class);
        startActivity(intent);

    }
}
