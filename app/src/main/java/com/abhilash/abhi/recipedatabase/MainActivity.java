package com.abhilash.abhi.recipedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase recipeDB;
    EditText ingredients, recipeTitle, recipeContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipeDB = this.openOrCreateDatabase("Recipes", MODE_PRIVATE, null);
        recipeDB.execSQL("CREATE TABLE IF NOT EXISTS recipes (id INTEGER PRIMARY KEY, recipeTitle VARCHAR, ingredients VARCHAR, recipeContent VARCHAR)");
        ingredients = (EditText) findViewById(R.id.ingredients);
        recipeTitle = (EditText) findViewById(R.id.recipeTitle);
        recipeContent = (EditText) findViewById(R.id.recipeContent);

    }



    
    public void addRecipe(View view){

        String ingredient = ingredients.getText().toString();
        String title = recipeTitle.getText().toString();
        String content = recipeContent.getText().toString();

        try {


            String sql = "INSERT INTO recipes (recipetitle, ingredients, recipeContent) VALUES (? , ? , ?)";


            SQLiteStatement statement = recipeDB.compileStatement(sql);

            statement.bindString(1, title);
            statement.bindString(2, ingredient);
            statement.bindString(3, content);

            statement.execute();

            Message.message(this, "Successfully Inserted A Row");


        }catch (Exception e) {

        e.printStackTrace();

        }

    }

    public void viewRecipes(View view){

        Intent i = new Intent(getApplicationContext(), ListActivity.class);
        i.putExtra("method", "viewRecipe" );
        startActivity(i);
    }

    public void getRecipe(View view){
        String ingredient = ingredients.getText().toString();
        Intent i = new Intent(getApplicationContext(), ListActivity.class);
        i.putExtra("method", "getRecipe" );
        i.putExtra("ingredients", ingredient );
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
