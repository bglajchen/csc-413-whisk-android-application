package com.example.team3.whisk;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**     File name: RecipeList.java
 *
 *      This class provides the recipe list of recipes found from the search from the home screen.
 *
 *      The activity uses the parameters obtained the home screen (search bar and filter selections)
 *      and uses them to parse through the Edamam API. A response is then obtained using GSON to
 *      get a list of recipes which the user can select from.
 *
 *      @author Team 3
 *      @version 1.00
 */

public class RecipeList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final private String appID = "e494d87e";
    final private String apiKey = "b057bd4f328ed351264e6be95f68ecd1";
    private String search;
    private String recipeName;
    private ListView listView;
    private ArrayList<String> param = new ArrayList<>();
    private ArrayList<String> food = new ArrayList<String>();
    private ArrayList<String> foodText = new ArrayList<String>();
    private EdamamPOJO responseObj;
    private String url;
    private RecipeAdapter adapter;
    private Gson gson;
    private AsyncHttpClient client;
    private ArrayAdapter arrayAdapter;
    private String responseStr;
    private SQLiteDatabase recipeDB;
    private String recipeSource = "";
    private String recipeUrl = "";
    private String recipeURL = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            param = bundle.getStringArrayList("list");
            search = bundle.getString("search");
        }
        else {
            search = updateListView();
            param = null;
        }

        listView = (ListView) findViewById(R.id.recipeList);
        client = new AsyncHttpClient();

        client.get(RecipeList.this, obtainURL(param, search), new AsyncHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast toast = Toast.makeText(RecipeList.this, "Error, could not resolve URL", Toast.LENGTH_LONG);
                toast.show();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseStr = new String(responseBody);
                gson = new Gson();
                responseObj = gson.fromJson(responseStr, EdamamPOJO.class);
                adapter = new RecipeAdapter(responseObj.getHits(), RecipeList.this);
                listView.setAdapter(adapter);

                if (responseObj.getHits().size() == 0)
                {
                    Toast.makeText(RecipeList.this, "No Results! Press back and try again!", Toast.LENGTH_LONG).show();
                }

                listView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                                Intent intent = new Intent(RecipeList.this, URLView.class);
                                EdamamPOJO.HitsEntity.RecipeEntity recipe = responseObj.getHits().get(position).getRecipe();

                                String preference = gson.toJson(responseObj.getHits().get(position).getRecipe());

                                foodText.clear();
                                food.clear();
                                for (int i = 0; i < recipe.getIngredients().size(); i++) {
                                    food.add((String) recipe.getIngredients().get(i).getFood());
                                }
                                for (int i = 0; i < recipe.getIngredients().size(); i++) {
                                    foodText.add((String) recipe.getIngredients().get(i).getText());
                                }
                                recipeName = recipe.getLabel();
                                recipeSource = recipe.getSource();
                                recipeUrl = recipe.getUrl();
                                recipeURL = recipe.getUrl();
                                intent.putExtra("recipeIngredientText", foodText);
                                intent.putExtra("recipeIngredient", food);
                                intent.putExtra("foodList", food);
                                intent.putExtra("recipeName", recipeName);
                                intent.putExtra("recipeSource", recipeSource);
                                intent.putExtra("recipeUrl", recipeUrl);
                                intent.putExtra("recipeURL", recipeURL);

                                //intent.putExtra("recipeNutrition", obtainNutrition(recipe));
                                startActivity(intent);
                            }
                        }
                );
            }


        });
    }

    /**     This method allows the user to search from a saved list of ingredients the user saved.
     *      When the user uses the option to select from a saved list of ingredients to search,
     *      this method gets called to use the search from this list as opposed to the manual search.
     *
     *      @return search Contains the ingredients from the saved list that the user selects.
     */
    public String updateListView() {

        recipeDB = this.openOrCreateDatabase("Preferences", MODE_PRIVATE, null);

        String selectQuery = "SELECT * FROM favorites";

        try {
            if (recipeDB.equals(null)){

                Toast.makeText(this, "You do not have any save ingredients", Toast.LENGTH_LONG).show();

            }

            Cursor c = recipeDB.rawQuery(selectQuery, null);

            int ingredientsIndex = c.getColumnIndex("ingredients");
            int recipeSourceIndex = c.getColumnIndex("recipeSource");
            int recipeUrlIndex = c.getColumnIndex("recipeUrl");

            int idIndex = c.getColumnIndex("id");
            c.moveToFirst();
            //search = c.getString(recipeTitleIndex) + " " + c.getString(recipeSourceIndex);
            search = c.getString(ingredientsIndex);
            c.moveToNext();
            int i = 0;
            arrayAdapter.notifyDataSetChanged();

            return search;


        } catch (Exception e) {

            e.printStackTrace();
            return search;

        }
    }

    /**     This method will add in the required strings for obtaining the URL that contains the
     *      JSON that will be parsed.
     *
     *      @param search Contains the string the user entered for searching from the home screen.
     *      @param param  Contains the list of filter selections the user checked from the home screen.
     *      @return url Which will contain the JSON that will be parsed by GSON.
     */
    public String obtainURL(ArrayList<String> param, String search) {
        String URL = "https://api.edamam.com/search?q=" + search + "&from=0&to=10&app_id=" + appID + "&app_key=" + apiKey;

        if (param != null){

            for (int i = 0; i < param.size(); i++)
            {
                URL += param.get(i);
            }
        }


        return URL;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
        } else if (id == R.id.nav_timer) {

            Intent intent = new Intent(getApplicationContext(), Timer.class);
            startActivity(intent);

        } else if (id == R.id.nav_preferences) {

            Intent intent = new Intent(getApplicationContext(), Preferences.class);
            startActivity(intent);

        } else if (id == R.id.nav_ingredients) {
            Intent intent = new Intent(getApplicationContext(), SavedIngredientsList.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
