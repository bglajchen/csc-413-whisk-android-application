package com.example.tony.filter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class URLView extends AppCompatActivity {

    WebView webView;
    ArrayList<String> ingredient;
    ArrayList<String> ingredientText;
    ArrayList<String> nutrition;
    ArrayList<String> ingredientID;
    ArrayList<String> ingredientName;
    IngredientSearchResponse responseObj;
    String recipeName;
    String recipeURL;
    String url;
    int count = 0;
    Gson gson;
    AsyncHttpClient client;
    IngredientSearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
        {
            recipeName = bundle.getString("recipeName");
            recipeURL = bundle.getString("recipeURL");
            ingredient = bundle.getStringArrayList("recipeIngredient");
            ingredientText = bundle.getStringArrayList("recipeIngredientText");
            nutrition = bundle.getStringArrayList("recipeNutrition");
        }

        setTitle(recipeName);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl(recipeURL);
    }

    public void onIngredientClick(View view)
    {
        ingredientID = new ArrayList<String>();
        ingredientName = new ArrayList<String>();

        client = new AsyncHttpClient();

        for (int i = 0; i < ingredient.size(); i++)
        {
            client.get(URLView.this, obtainURL(ingredient.get(i)), new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String responseStr = new String(responseBody);
                    gson = new Gson();
                    responseObj = gson.fromJson(responseStr, IngredientSearchResponse.class);
                    ingredientName.add(responseObj.getHits().get(0).getFields().getItem_name());
                    ingredientID.add(responseObj.getHits().get(0).getFields().getItem_id());

                    if (count == (ingredient.size() - 1) )
                    {
                        System.out.println("itemID = " + ingredientID.toString());
                        Intent intent = new Intent(URLView.this, IngredientNutritionView.class);
                        intent.putExtra("recipeIngredient", ingredient);
                        intent.putExtra("recipeNutrition", nutrition);
                        intent.putExtra("ingredientName", ingredientName);
                        intent.putExtra("ingredientID", ingredientID);
                        intent.putExtra("ingredientText", ingredientText);
                        intent.putExtra("recipeURL", recipeURL);
                        intent.putExtra("recipeName", recipeName);
                        startActivity(intent);
                    }
                    count++;
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast toast = Toast.makeText(URLView.this, "Error, could not resolve URL", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
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


    public String obtainURL(String ingredient)
    {
        url = "https://api.nutritionix.com/v1_1/search/"+ingredient+"?results=0%3A20&cal_min=0&cal_max=50000&fields=item_name%2Cbrand_name%2Citem_id%2Cbrand_id&appId=c313890c&appKey=6cc65ba8dd9a804c54666bf70cf9a6a1";
        return url;
    }

}
