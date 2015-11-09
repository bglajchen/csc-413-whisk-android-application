package com.example.tony.filter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class Filter extends Activity {
    String search;
    String recipeName;
    ListView listView;
    ArrayList<String> param = new ArrayList<>();
    ArrayList<String> food = new ArrayList<String>();
    ArrayList<String> foodText = new ArrayList<String>();
    EdamamResponse responseObj;
    String url;
    RecipeAdapter adapter;
    Gson gson;
    AsyncHttpClient client;
    String recipeURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            param = bundle.getStringArrayList("list");
            search = bundle.getString("search");
        }

        listView = (ListView) findViewById(R.id.recipeList);
        client = new AsyncHttpClient();

        client.get(Filter.this, obtainURL(param, search), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseStr = new String(responseBody);
                gson = new Gson();
                responseObj = gson.fromJson(responseStr, EdamamResponse.class);
                adapter = new RecipeAdapter(responseObj.getHits(), Filter.this);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                                Intent intent = new Intent(Filter.this, URLView.class);
                                EdamamResponse.HitsEntity.RecipeEntity recipe = responseObj.getHits().get(position).getRecipe();
                                foodText.clear();
                                for (int i = 0; i < recipe.getIngredients().size(); i++) {
                                    food.add((String) recipe.getIngredients().get(i).getFood());
                                }
                                for (int i = 0; i < recipe.getIngredients().size(); i++) {
                                    foodText.add((String) recipe.getIngredients().get(i).getText());
                                }
                                recipeName = recipe.getLabel();
                                recipeURL = recipe.getUrl();
                                intent.putExtra("recipeIngredientText", foodText);
                                intent.putExtra("recipeIngredient", food);
                                intent.putExtra("recipeName", recipeName);
                                intent.putExtra("recipeURL", recipeURL);
                                //intent.putExtra("recipeNutrition", obtainNutrition(recipe));
                                startActivity(intent);
                            }
                        }
                );
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast toast = Toast.makeText(Filter.this, "Error, could not resolve URL", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    public String obtainURL(ArrayList<String> param, String search) {
        String URL = "https://api.edamam.com/search?q=" + search + "&from=0&to=10&app_id=e494d87e&app_key=b057bd4f328ed351264e6be95f68ecd1";

        for (int i = 0; i < param.size(); i++)
        {
            URL += param.get(i);
        }

        return URL;
    }
}