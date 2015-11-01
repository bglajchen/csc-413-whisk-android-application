package com.example.tony.filter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Recipe extends ListActivity {
    ArrayList<String> ingredient;
    ArrayList<String> ingredientText;
    ArrayList<String> nutrition;
    String recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ListView recipeList = (ListView) findViewById(android.R.id.list);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
        {
            recipeName = bundle.getString("recipeName");
            ingredient = bundle.getStringArrayList("recipeIngredient");
            ingredientText = bundle.getStringArrayList("recipeIngredientText");
            nutrition = bundle.getStringArrayList("recipeNutrition");
        }
        TextView title = (TextView) findViewById(R.id.RecipeTitle);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                ingredientText );
        title.setText(recipeName);
        recipeList.setAdapter(arrayAdapter);

        recipeList.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id)
                    {
                        Intent intent = new Intent(Recipe.this, SearchNutrition.class);
                        String food = ingredient.get(position);
                        intent.putExtra("food", food);
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
