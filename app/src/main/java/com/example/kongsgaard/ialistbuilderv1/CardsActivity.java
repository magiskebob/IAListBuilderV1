package com.example.kongsgaard.ialistbuilderv1;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardsActivity extends AppCompatActivity {

    ListView mainView;
    List<CardClass> baselist;
    CardClassArrayAdapter baseAdapter;
    CardClass tempcard;
    DataBaseHelper myOperator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        mainView = (ListView) findViewById(R.id.mainListView);
        Intent intent = getIntent();
        baselist = new ArrayList<>();
        baseAdapter = new CardClassArrayAdapter(this, baselist);
        mainView.setAdapter(baseAdapter);
        myOperator = new DataBaseHelper(this);
 //       getRebelList(null);
        mainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tempcard = (CardClass)mainView.getItemAtPosition(position);
                Intent intent = new Intent(CardsActivity.this, ViewCardActivity.class);
                intent.putExtra("cardpath", tempcard.CardImage);
                startActivity(intent);
            }

        });



    }




    public void getRebelList(View view) {
       // List<CardClass> rebelList = new ArrayList<>();
        List<CardClass> rebelList = myOperator.getRebelList();
       // rebelList.add(luke1);
       // rebelList.add(lando);
        baselist.clear();
        for (CardClass card: rebelList) {
            baselist.add(card);
            baseAdapter.notifyDataSetChanged();

        }
    }

    public void getEmpireList(View view) {
        List<CardClass> empireList = myOperator.getEmpireList();
       // CardClass darthVader = new CardClass(1,"Darth vader", 18, R.drawable.darthvader);
       // empireList.add(darthVader);
        baselist.clear();
        for (CardClass card : empireList) {
            baselist.add(card);
            baseAdapter.notifyDataSetChanged();

        }
    }

    public void getScumList(View view) {
        List<CardClass> scumList = myOperator.getScumList();
       // CardClass bobaFett = new CardClass(1,"Boba Fett", 13, R.drawable.bobafett);
      //  scumList.add(bobaFett);
        baselist.clear();
        for (CardClass card : scumList) {
            baselist.add(card);
            baseAdapter.notifyDataSetChanged();

        }
    }

    public void viewCard(View view) {
        Intent intent = new Intent(this, ViewCardActivity.class);
        intent.putExtra("cardpath", tempcard.CardImage);
        startActivity(intent);


    }
}
