package com.example.tony.filter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

public class URLView extends AppCompatActivity {

    WebView webView;
    ArrayList<String> ingredient;
    ArrayList<String> ingredientText;
    ArrayList<String> nutrition;
    String recipeName;
    String recipeURL;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            recipeName = bundle.getString("recipeName");
            ingredient = bundle.getStringArrayList("recipeIngredient");
            ingredientText = bundle.getStringArrayList("recipeIngredientText");
            nutrition = bundle.getStringArrayList("recipeNutrition");
            recipeURL = bundle.getString("recipeURL");
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
        Intent intent = new Intent(URLView.this, Recipe.class);
        intent.putExtra("recipeIngredientText", ingredientText);
        intent.putExtra("recipeIngredient", ingredient);
        intent.putExtra("recipeName", recipeName);
        intent.putExtra("recipeNutrition", nutrition);
        startActivity(intent);
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
}
