package com.example.team3.whisk;

import android.content.Intent;
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
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**     File name: SearchIngredient.java
 *
 *      This class provides the activity for outputting similar ingredients for obtaining nutritional facts.
 *
 *      Each recipe contains nutritional information for each ingredient of the recipe. If the user
 *      wants to view more similar ingredients, this activity will handle that. The activity does a Nutritionix API
 *      call for similar ingredients. The list is then outputted on the screen. If the user presses
 *      on an item from the list, another Nutritionix API call is engaged that will obtain nutritional
 *      information on the list by expanding on the selected item on the list.
 *
 *      @author Team 3
 *      @version 1.00
 */

public class SearchIngredient extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final private String appID = "16de0c54";
    final private String apiKey = "e87e735d38bbcb5732adbd79dea587dc";
    private String food;
    private ListView listView;
    private IngredientSearchPOJO responseObj;
    private IngredientNutritionPOJO responseObjNutrition;
    private String url;
    private IngredientSearchAdapter adapter;
    private Gson gson;
    private static AsyncHttpClient client;
    private String itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_nutrition);
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

        if (bundle != null)
        {
            food = bundle.getString("food");
        }

        listView = (ListView) findViewById(R.id.food);
        client = new AsyncHttpClient();

        client.get(SearchIngredient.this, obtainURLBrand(food), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseStr = new String(responseBody);
                gson = new Gson();
                responseObj = gson.fromJson(responseStr, IngredientSearchPOJO.class);
                adapter = new IngredientSearchAdapter(responseObj.getHits(), SearchIngredient.this);
                listView.setAdapter(adapter);
               /* ListView foodSearch = (ListView) findViewById(R.id.);*/

                listView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> arg0, final View view, final int position, long id) {
                                final TableLayout show = (TableLayout) view.findViewById(R.id.nutritionList);
                                itemID = responseObj.getHits().get(position).getFields().getItem_id();

                                if (show.getVisibility() == View.GONE)
                                {
                                    client.get(SearchIngredient.this, obtainURLNutrition(itemID), new AsyncHttpResponseHandler() {
                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                            String responseStr = new String(responseBody);
                                            gson = new Gson();
                                            responseObjNutrition = gson.fromJson(responseStr, IngredientNutritionPOJO.class);
                                            setView(view, responseObjNutrition);

                                            show.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                            Toast toast = Toast.makeText(SearchIngredient.this, "Error, could not resolve URL", Toast.LENGTH_LONG);
                                            toast.show();
                                        }
                                    });
                                }
                                else
                                    show.setVisibility(View.GONE);

                            }
                        }
                );
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast toast = Toast.makeText(SearchIngredient.this, "Error, could not resolve URL", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    /**     This method outputs the GSON object containing all nutritional information on the screen from each
     *      API call (One call per ingredient).
     *
     *      @param view User interface component for outputting nutritional infroamtion.
     *      @param responseObj Containing the GSON response object after parsing the JSON.
     */
    public void setView(View view, IngredientNutritionPOJO responseObj) {
        TextView title = (TextView) view.findViewById(R.id.NutritionTitle);
        title.setText(responseObj.getItem_name());

        IngredientNutritionPOJO item = responseObj;

        TextView fat = (TextView) view.findViewById(R.id.fat);
        try {
            fat.setText(item.getNf_total_fat() + "g");
        } catch (NullPointerException e) {
            fat.setText("N/A");
        }

        TextView fiber = (TextView) view.findViewById(R.id.fiber);
        try {
            fiber.setText(item.getNf_dietary_fiber() + "g");
        } catch (NullPointerException e) {
            fiber.setText("N/A");
        }

        TextView carb = (TextView) view.findViewById(R.id.carbs);
        try {
            carb.setText(item.getNf_total_carbohydrate() + "g");
        } catch (NullPointerException e) {
            carb.setText("N/A");
        }

        TextView sugar = (TextView) view.findViewById(R.id.sugar);
        try {
            sugar.setText(item.getNf_sugars() + "g");
        } catch (NullPointerException e) {
            sugar.setText("N/A");
        }

        TextView protein = (TextView) view.findViewById(R.id.protein);
        try {
            protein.setText(item.getNf_protein() + "g");
        } catch (NullPointerException e) {
            protein.setText("N/A");
        }

        TextView cholest = (TextView) view.findViewById(R.id.cholesterol);
        try {
            cholest.setText(item.getNf_cholesterol() + "mg");
        } catch (NullPointerException e) {
            cholest.setText("N/A");
        }

        TextView sodium = (TextView) view.findViewById(R.id.sodium);
        try {
            sodium.setText(item.getNf_sodium() + "mg");
        } catch (NullPointerException e) {
            sodium.setText("N/A");
        }

        TextView calcium = (TextView) view.findViewById(R.id.calcium);
        try {
            calcium.setText(item.getNf_calcium_dv() + "%");
        } catch (NullPointerException e) {
            calcium.setText("N/A");
        }

        TextView vitA = (TextView) view.findViewById(R.id.vitA);
        try {
            vitA.setText(item.getNf_vitamin_a_dv() + "%");
        } catch (NullPointerException e) {
            vitA.setText("N/A");
        }

        TextView vitC = (TextView) view.findViewById(R.id.vitC);
        try {
            vitC.setText(item.getNf_vitamin_c_dv() + "%");
        } catch (NullPointerException e) {
            vitC.setText("N/A");
        }
    }
    /**     This method obtains the URL for parsing which will contain a response of the list
     *      of similar ingredients and their itemID.
     *
     *      @param food Contains the ingredient that will be searched.
     *      @return url Which will contain the JSON that will be parsed by GSON.
     */
    public String obtainURLBrand(String food)
    {
        url = "https://api.nutritionix.com/v1_1/search/"+food+"?results=0%3A20&cal_min=0&cal_max=50000&fields=item_name%2Cbrand_name%2Citem_id%2Cbrand_id&appId=" + appID + "&appKey=" + apiKey;
        return url;
    }

    /**     This method obtains the URL for parsing each itemID which contains nutritional information.
     *
     *      @param itemID Contains the Nutritionix API item ID associated with each ingredient.
     *      @return url Which will contain the JSON that will be parsed by GSON.
     */
    public String obtainURLNutrition(String itemID) {
        url = "https://api.nutritionix.com/v1_1/item?id=" + itemID + "&appId=" + appID + "&appKey=" + apiKey;
        return url;
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
        getMenuInflater().inflate(R.menu.search_nutrition, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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
