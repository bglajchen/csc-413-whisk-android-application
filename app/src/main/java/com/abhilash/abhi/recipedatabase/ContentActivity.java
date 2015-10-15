package com.abhilash.abhi.recipedatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class ContentActivity extends MainActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        Intent i = getIntent();
        //Bundle extras = getIntent().getExtras();
        //if (extras != null) {
        //   recipeId = extras.getLong("recipeId");
        //}

        url = i.getStringExtra("recipeURL");

        webView.loadUrl(url);

    }

    public void showAlert() {

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Save Recipe")
                .setMessage("Do you want to save this recipe?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        saveFavorites();
                        return;
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }


    public void saveFavorites(){

        String sql = "INSERT INTO favorites (recipeTitle, recipeContent) SELECT recipeTitle, recipeContent FROM recipes WHERE recipeContent = ?";


        SQLiteStatement statement = recipeDB.compileStatement(sql);

        statement.bindString(1, url);
        statement.execute();
        Message.message(this, "Recipe Saved");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_save:
                showAlert();
                return super.onOptionsItemSelected(item);
            default:
                return true;

        }
    }
}
