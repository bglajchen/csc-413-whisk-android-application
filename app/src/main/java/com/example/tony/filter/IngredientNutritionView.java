package com.example.tony.filter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class IngredientNutritionView extends AppCompatActivity {

    ArrayList<String> ingredientName = new ArrayList<String>();
    ArrayList<String> ingredientID = new ArrayList<String>();
    ArrayList<String> recipeNutrition = new ArrayList<String>();
    ArrayList<String> recipeIngredient = new ArrayList<String>();
    ArrayList<String> ingredientText = new ArrayList<String>();
    ArrayList<IngredientNutritionResponse> responseObj = new ArrayList<IngredientNutritionResponse>();
    String url;
    String ingredient;
    String recipeName;
    String recipeURL;
    int count = 0;
    View view;
    ListView listView;
    Gson gson;
    AsyncHttpClient client;
    NutritionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_nutrition_view);
        listView = (ListView) findViewById(R.id.nutritionList);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
        {
            ingredientName = bundle.getStringArrayList("ingredientName");
            ingredientID = bundle.getStringArrayList("ingredientID");
            recipeNutrition = bundle.getStringArrayList("recipeNutrition");
            recipeIngredient = bundle.getStringArrayList("recipeIngredient");
            ingredientText = bundle.getStringArrayList("ingredientText");
            recipeName = bundle.getString("recipeName");
            recipeURL = bundle.getString("recipeURL");
        }

        client = new AsyncHttpClient();

        for (int i = 0; i < ingredientID.size(); i++) {
            client.get(IngredientNutritionView.this, obtainURL(ingredientID.get(i)), new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String responseStr = new String(responseBody);
                    gson = new Gson();
                    responseObj.add(gson.fromJson(responseStr, IngredientNutritionResponse.class));

                    if (count == (ingredientID.size() - 1) )
                    {
                        adapter = new NutritionAdapter(responseObj, IngredientNutritionView.this);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(
                                new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                                        Intent intent = new Intent(IngredientNutritionView.this, SearchIngredient.class);
                                        ingredient = responseObj.get(position).getItem_name();
                                        intent.putExtra("food", ingredient);
                                        startActivity(intent);
                                    }
                                }
                        );
                    }
                    count++;
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast toast = Toast.makeText(IngredientNutritionView.this, "Error, could not resolve URL (Here)", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ingredient_nutrition_view, menu);
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

    public String obtainURL(String itemID) {
        url = "https://api.nutritionix.com/v1_1/item?id=" + itemID + "&appId=c313890c&appKey=6cc65ba8dd9a804c54666bf70cf9a6a1";
        return url;
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(IngredientNutritionView.this, URLView.class);
        intent.putExtra("recipeIngredient", recipeIngredient);
        intent.putExtra("recipeNutrition", recipeNutrition);
        intent.putExtra("ingredientText", ingredientText);
        intent.putExtra("recipeName", recipeName);
        intent.putExtra("recipeURL", recipeURL);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
