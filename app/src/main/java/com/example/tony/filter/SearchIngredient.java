package com.example.tony.filter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class SearchIngredient extends AppCompatActivity {

    String food;
    ListView listView;
    IngredientSearchResponse responseObj;
    String url;
    IngredientSearchAdapter adapter;
    Gson gson;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
        {
           food = bundle.getString("food");
        }

        listView = (ListView) findViewById(R.id.food);
        client = new AsyncHttpClient();

        client.get(SearchIngredient.this, obtainURL(food), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseStr = new String(responseBody);
                gson = new Gson();
                responseObj = gson.fromJson(responseStr, IngredientSearchResponse.class);
                adapter = new IngredientSearchAdapter(responseObj.getHits(), SearchIngredient.this);
                listView.setAdapter(adapter);
               /* ListView foodSearch = (ListView) findViewById(R.id.);*/

                listView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                                Intent intent = new Intent(SearchIngredient.this, OutputNutrition.class);
                                intent.putExtra("itemID", responseObj.getHits().get(position).getFields().getItem_id());;
                                startActivity(intent);
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

    public String obtainURL(String food)
    {
        url = "https://api.nutritionix.com/v1_1/search/"+food+"?results=0%3A20&cal_min=0&cal_max=50000&fields=item_name%2Cbrand_name%2Citem_id%2Cbrand_id&appId=16de0c54&appKey=e87e735d38bbcb5732adbd79dea587dc";
        return url;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nutrition, menu);
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
