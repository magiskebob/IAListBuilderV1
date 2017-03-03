package com.example.kongsgaard.ialistbuilderv1;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ArmyListsActivity extends AppCompatActivity {
    ListView armieslistview;
    List<ArmyList> baselist;
    ArmyListAdapter baseAdapter;
    DataBaseHelper myOperator;
    ArmyList tempArmyList;
    List<CardClass> test_list;

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
                //Intent intent = new Intent(CardsActivity.this, ViewCardActivity.class);
               // intent.putExtra("cardpath", tempcard.CardImage);
               // startActivity(intent);
            }

        });
       // test_list = new ArrayList<>();
      //  ArmyList test = new ArmyList(test_list,"test_test", 40);
       // myOperator.addArmyList(test);
        getArmyLists();
    }

    public void getArmyLists() {
        List<ArmyList> templist = myOperator.getArmyLists();
        baselist.clear();
        for (ArmyList army : templist) {
            baselist.add(army);
            baseAdapter.notifyDataSetChanged();


        }
    }
}
