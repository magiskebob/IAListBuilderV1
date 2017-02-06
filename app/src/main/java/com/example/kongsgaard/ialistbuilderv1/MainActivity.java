package com.example.kongsgaard.ialistbuilderv1;

import android.content.Intent;
import android.os.DropBoxManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper myOperator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myOperator = new DataBaseHelper(this);
        try {

            myOperator.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        myOperator.openDataBase();

    }

    public void navigateToFactionCards(View view) {
        Intent intent = new Intent(this, CardsActivity.class);
        startActivity(intent);

    }
}
