package com.example.brandonglajchen.api_recipepuppy;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    TextView api_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api_result = (TextView) findViewById(R.id.api_result);

        apiTask task = new apiTask();
        task.execute("http://www.recipepuppy.com/api/?i=asparagus");
    }

    /*@Override
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
    }*/

    public class apiTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection conn = null;

            try {
                url = new URL(urls[0]);
                conn = (HttpURLConnection) url.openConnection();
                InputStream iStream = conn.getInputStream();
                InputStreamReader isReader = new InputStreamReader(iStream);
                int data = isReader.read();

                while (data != -1) {
                    result += (char) data;
                    data = isReader.read();
                }

                return result;

            } catch (Exception e) {
                e.getMessage();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                StringBuilder sb = new StringBuilder(100);
                JSONObject json = new JSONObject(result);
                JSONArray jArray = json.getJSONArray("results");

                for (int i = 0; i < jArray.length(); i++) {
                    sb.append(jArray.getJSONObject(i).getString("title"));
                    sb.append("\n");
                }

                api_result.setText(sb.toString());

            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
}
