package com.abhilash.abhi.recipedatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends MainActivity {

    ArrayAdapter arrayAdapter;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayList<String> ingred = new ArrayList<String>();
    ArrayList<String> cont = new ArrayList<String>();
    //ArrayList<Integer> id = new ArrayList<Integer>();
    //int arrayId[];
    //int recipeId;
    String tableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent i = getIntent();
        String ingredient = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tableName = extras.getString("tableName");
            ingredient = extras.getString("ingredients");
        }

        updateListView();


    }


    public void updateListView(){

        final Cursor c = recipeDB.rawQuery("SELECT * FROM " + tableName, null);

        try {

            int ingredientsIndex = c.getColumnIndex("ingredients");
            int titleIndex = c.getColumnIndex("recipeTitle");
            int contentIndex = c.getColumnIndex("recipeContent");
            int idIndex = c.getColumnIndex("id");
            c.moveToFirst();
            titles.clear();
            ingred.clear();
            cont.clear();
            //id.clear();

            int i = 0;
            while (c != null) {
                titles.add(c.getString(titleIndex));
                ingred.add(c.getString(ingredientsIndex));
                cont.add(c.getString(contentIndex));
                //id.add(c.getInt(idIndex));
                //arrayId[i] = c.getInt(idIndex);
                //i++;
                c.moveToNext();
            }
            arrayAdapter.notifyDataSetChanged();







        }catch (Exception e) {

            e.printStackTrace();

        }


        ListView listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //recipeId = arrayId[position];
                Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                intent.putExtra("recipeURL", cont.get(position));
                //intent.putExtra("recipeId", recipeId);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
