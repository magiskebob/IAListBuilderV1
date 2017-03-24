package com.example.kongsgaard.ialistbuilderv1;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ArmyListsActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    ListView armieslistview;
    List<ArmyList> baselist;
    ArmyListAdapter baseAdapter;
    DataBaseHelper myOperator;
    ArmyList tempArmyList;
    List<CardClass> test_list;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_army_lists);
        armieslistview = (ListView) findViewById(R.id.armyListView);
        baselist = new ArrayList<>();
        baseAdapter = new ArmyListAdapter(this, baselist);
        armieslistview.setAdapter(baseAdapter);
        myOperator = new DataBaseHelper(this);
        armieslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tempArmyList = (ArmyList) armieslistview.getItemAtPosition(position);
                Intent intent = new Intent(ArmyListsActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });
        armieslistview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getBaseContext();
                int duration = Toast.LENGTH_LONG;
                CharSequence test = "testing testing " + position;
                Toast toast = Toast.makeText(context, test, duration);
                toast.show();
                return true;
            }
        });
       // test_list = new ArrayList<>();
      //  ArmyList test = new ArmyList(test_list,"test_test", 40);
       // myOperator.addArmyList(test);
        getArmyLists();
    }
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void getArmyLists() {
        List<ArmyList> templist = myOperator.getArmyLists();
        baselist.clear();
        for (ArmyList army : templist) {
            baselist.add(army);
            baseAdapter.notifyDataSetChanged();


        }
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
        return false;
    }
}
