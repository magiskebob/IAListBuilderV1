package com.example.kongsgaard.ialistbuilderv1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetDishesActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_dishes);
        ReadTask task = new ReadTask();
        task.execute("http://anbo-canteen.azurewebsites.net/Service1.svc/dishes");
    }


    private class ReadTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence charSequence) {
            super.onPostExecute(charSequence);
         //   TextView messageTextView = (TextView) findViewById(R.id.main_message_textview);
            //messageTextView.setText(charSequence);
            final List<DishClass> dishess = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(charSequence.toString());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    String desc = obj.getString("Description");
                    double price = obj.getDouble("Price");
                    String title = obj.getString("Title");
                    int alk = obj.getInt("Alcohol");
                    int carbs = obj.getInt("Carbohydrates");
                    int energy = obj.getInt("Energy");
                    double fat = obj.getDouble("Fat");
                    double protein = obj.getDouble("Protein");
                    DishClass dish = new DishClass(fat,energy,carbs,alk,desc,price,protein,title);
                    dishess.add(dish);
                }
                ListView listView = (ListView) findViewById(R.id.DishListView);
                //ArrayAdapter<Book> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, books);
                DishAdapter adapter = new DishAdapter(getBaseContext(),dishess);
                listView.setAdapter(adapter);
               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getBaseContext(), DishActivity.class);
                        intent.putExtra("DISH", dishess.get(position));
                        startActivity(intent);
                    }
                });
            } catch (JSONException ex) {
              //  messageTextView.setText(ex.getMessage());
                Log.e("DISHES", ex.getMessage());
            }
        }

        @Override
        protected void onCancelled(CharSequence charSequence) {
            super.onCancelled();
          //  TextView messageTextView = (TextView) findViewById(R.id.main_message_textview);
          //  messageTextView.setText(charSequence);
            Log.e("DISHES", charSequence.toString());
        }
    }
}
