package com.example.kongsgaard.ialistbuilderv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.FirebaseException;

public class DishActivity extends AppCompatActivity {
    TextView Title;
    TextView Desc;
    TextView Alk;
    TextView Protein;
    TextView Fat;
    TextView Energy;
    TextView Carbs;
    TextView Price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        Intent intent = getIntent();
        DishClass dish = (DishClass)intent.getSerializableExtra("DISH");
        Title = (TextView) findViewById(R.id.dish_title);
        Title.setText(dish.Title);
        Desc = (TextView) findViewById(R.id.dish_desc);
        Desc.setText(dish.Description);
        Alk = (TextView) findViewById(R.id.dish_alk);
        Alk.setText(String.valueOf(dish.Alcohol));
        Protein = (TextView) findViewById(R.id.dish_protein);
        Protein.setText(String.valueOf(dish.Protein));
        Fat = (TextView) findViewById(R.id.dish_fat);
        Fat.setText(String.valueOf(dish.Fat));
        Energy = (TextView) findViewById(R.id.dish_energy);
        Energy.setText(String.valueOf(dish.Energy));
        Carbs = (TextView) findViewById(R.id.dish_carbs);
        Carbs.setText(String.valueOf(dish.Carbohydrates));
        Price = (TextView) findViewById(R.id.dish_price);
        Price.setText(String.valueOf(dish.Price)+ " kr");
    }
}
