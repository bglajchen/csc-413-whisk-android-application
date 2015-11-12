package com.example.tony.filter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class IngredientSearch extends AppCompatActivity {
    ArrayList<String> ingredient;
    ArrayList<String> ingredientText;
    ArrayList<String> nutrition;
    ArrayList<String> ingredientID = new ArrayList<String>();
    ArrayList<String> ingredientName= new ArrayList<String>();
    ArrayList<IngredientSearchResponse> responseObj = new ArrayList<IngredientSearchResponse>();
    String recipeName;
    String url;
    IngredientSearchAdapter adapter;
    Gson gson;
    AsyncHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            recipeName = bundle.getString("recipeName");
            ingredient = bundle.getStringArrayList("recipeIngredient");
            ingredientText = bundle.getStringArrayList("recipeIngredientText");
            nutrition = bundle.getStringArrayList("recipeNutrition");
        }

        client = new AsyncHttpClient();

        for (int i = 0; i < ingredient.size(); i++)
        {
            client.get(IngredientSearch.this, obtainURL(ingredient.get(i)), new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String responseStr = new String(responseBody);
                    gson = new Gson();
                    responseObj.add(gson.fromJson(responseStr, IngredientSearchResponse.class));
                    for (int i = 0; i < ingredient.size(); i++)
                    {
                        ingredientName.add(responseObj.get(i).getHits().get(1).getFields().getItem_name());
                        ingredientID.add(responseObj.get(i).getHits().get(1).getFields().getItem_id());
                    }
                        startActivity();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast toast = Toast.makeText(IngredientSearch.this, "Error, could not resolve URL", Toast.LENGTH_LONG);
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
    public void startActivity()
    {
        Intent intent = new Intent(IngredientSearch.this, IngredientNutritionView.class);
        intent.putExtra("ingredientName", ingredientName);
        intent.putExtra("ingredientID", ingredientID);
        startActivity(intent);
    }
    public String obtainURL(String ingredient)
    {
        url = "https://api.nutritionix.com/v1_1/search/"+ingredient+"?results=0%3A20&cal_min=0&cal_max=50000&fields=item_name%2Cbrand_name%2Citem_id%2Cbrand_id&appId=c313890c&appKey=6cc65ba8dd9a804c54666bf70cf9a6a1";
        return url;
    }
}
