package com.example.kongsgaard.ialistbuilderv1;

import android.content.Intent;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CardsActivity extends AppCompatActivity {

    ListView mainView;
    ArrayList<CardClass> baselist;
    CardClassArrayAdapter baseAdapter;
    CardClass tempcard;
    DataBaseHelper myOperator;
    List<Integer> cardPaths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        mainView = (ListView) findViewById(R.id.mainListView);
        Intent intent = getIntent();
        baselist = new ArrayList<>();
        if (savedInstanceState !=null)
        {
            baselist = (ArrayList)savedInstanceState.getSerializable("savedList");
        }
        else{
            baselist = new ArrayList<>();
        }

        baseAdapter = new CardClassArrayAdapter(this, baselist);
        mainView.setAdapter(baseAdapter);
        myOperator = new DataBaseHelper(this);
        cardPaths = new ArrayList<>();
 //       getRebelList(null);
        mainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tempcard = (CardClass)mainView.getItemAtPosition(position);
                int card_position = mainView.getSelectedItemPosition();
              //  Intent intent = new Intent(CardsActivity.this, ViewCardActivity.class);
                Intent intent = new Intent(CardsActivity.this,ImageSwiper.class);
                intent.putExtra("cardpath", tempcard.CardImage);
                intent.putExtra("card_position", position);
                intent.putIntegerArrayListExtra("int_list", (ArrayList)cardPaths);
                startActivity(intent);
            }

        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("savedList",baselist);
    }

    public List<Integer> makeIntegerList (List<CardClass> cardlist)
    {
        List<Integer> templist = new ArrayList<>();
        for (CardClass card:cardlist) {
            templist.add(card.CardImage);
        }
        return templist;
    }


    public void getRebelList(View view) {
        Log.d("button", "get rebel list called");
       // List<CardClass> rebelList = new ArrayList<>();
        List<CardClass> rebelList = myOperator.getRebelList();
       // rebelList.add(luke1);
       // rebelList.add(lando);
        baselist.clear();
        for (CardClass card: rebelList) {
            baselist.add(card);
            baseAdapter.notifyDataSetChanged();

        }
        cardPaths = makeIntegerList(baselist);
    }

    public void getEmpireList(View view) {
        Log.d("button", "get empire list called");
        List<CardClass> empireList = myOperator.getEmpireList();
       // CardClass darthVader = new CardClass(1,"Darth vader", 18, R.drawable.darthvader);
       // empireList.add(darthVader);
        baselist.clear();
        for (CardClass card : empireList) {
            baselist.add(card);
            baseAdapter.notifyDataSetChanged();

        }
        cardPaths = makeIntegerList(baselist);
    }

    public void getScumList(View view) {
        Log.d("button", "get scum list called");
        List<CardClass> scumList = myOperator.getScumList();
       // CardClass bobaFett = new CardClass(1,"Boba Fett", 13, R.drawable.bobafett);
      //  scumList.add(bobaFett);
        baselist.clear();
        for (CardClass card : scumList) {
            baselist.add(card);
            baseAdapter.notifyDataSetChanged();

        }
        cardPaths = makeIntegerList(baselist);
    }

    public void viewCard(View view) {
        Intent intent = new Intent(this, ViewCardActivity.class);
        intent.putExtra("cardpath", tempcard.CardImage);
        startActivity(intent);


    }
}
