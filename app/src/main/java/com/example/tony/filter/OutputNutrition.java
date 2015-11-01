package com.example.tony.filter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class OutputNutrition extends AppCompatActivity {

    String itemID;
    View view;
    IngredientNutritionResponse responseObj;
    String url;
    Gson gson;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_output);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
        {
            itemID = bundle.getString("itemID");
        }

        client = new AsyncHttpClient();

        client.get(OutputNutrition.this, obtainURL(itemID), new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                String responseStr = new String(responseBody);
                gson = new Gson();
                responseObj = gson.fromJson(responseStr, IngredientNutritionResponse.class);
                /*adapter = new IngredientSearchAdapter(responseObj.getHits(), SearchNutrition.this);*/
              /*  listView.setAdapter(adapter);*/
               /* ListView foodSearch = (ListView) findViewById(R.id.);*/
                setView(responseObj);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
            {
                Toast toast = Toast.makeText(OutputNutrition.this, "Error, could not resolve URL", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nutrition_output, menu);
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

    public String obtainURL(String itemID)
    {
        url = "https://api.nutritionix.com/v1_1/item?id="+itemID+"&appId=16de0c54&appKey=e87e735d38bbcb5732adbd79dea587dc";
        return url;
    }

    public void setView(IngredientNutritionResponse responseObj)
    {
        TextView title = (TextView) findViewById(R.id.NutritionTitle);
        title.setText(responseObj.getItem_name());

        IngredientNutritionResponse item = responseObj;

        TextView fat = (TextView) findViewById(R.id.fat);
        fat.setText(item.getNf_total_fat()+"g");

        TextView sugar = (TextView) findViewById(R.id.sugar);
        sugar.setText(item.getNf_sugars()+"g");

        TextView protein = (TextView) findViewById(R.id.protein);
        protein.setText(item.getNf_protein()+"g");

        TextView cholest = (TextView) findViewById(R.id.cholesterol);
        cholest.setText(item.getNf_cholesterol()+"mg");

        TextView sodium = (TextView) findViewById(R.id.sodium);
        sodium.setText(item.getNf_sodium()+"mg");

        TextView calcium = (TextView) findViewById(R.id.calcium);
        calcium.setText(item.getNf_calcium_dv()+"%");

        TextView vitA = (TextView) findViewById(R.id.vitA);
        vitA.setText(item.getNf_vitamin_a_dv()+"%");

        TextView vitC = (TextView) findViewById(R.id.vitC);
        vitC.setText(item.getNf_vitamin_c_dv()+"%");

    }
}
