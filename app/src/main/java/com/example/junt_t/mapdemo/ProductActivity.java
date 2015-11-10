package com.example.junt_t.mapdemo;

/**
 * Created by Junt_T on 2015/10/21 0021.
 */


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.FileNotFoundException;

public class ProductActivity extends Activity {
    String searchProduct = "orange juice";
    String searchStoreID = null;
    ListView produceListView;
    //ResponseSupermarketAPI responseObj;
    ProductAdapter productAdapter;
    // AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);

        //Get reference to our ListView
        produceListView = (ListView) findViewById(R.id.sitesList);

        /**
         * Set the click listener to launch the browser when a row is clicked.
         */



        /*
         * If network is available download the xml from the Internet.
		 * If not then try to use the local file from last time.
		 */
        if (isNetworkAvailable()) {
            Log.i("StackSites", "starting download Task");
            DownloadTask download = new DownloadTask();
            download.execute();
        }else{
            productAdapter = new ProductAdapter(ProductActivity.this, -1, ProductXmlPullParser.getListFromFile(ProductActivity.this));
            produceListView.setAdapter(productAdapter);
        }
    }

    //Helper method to determine if Internet connection is available.
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /*
	 * AsyncTask that will download the xml file for us and store it locally.
	 * After the download is done we'll parse the local file.
	 */
    private class DownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            //Download the file
            try {
                Downloader.DownloadFromUrl(obtainURL(searchProduct), openFileOutput("SearchByProductName.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            //setup our Adapter and set it to the ListView.

            productAdapter = new ProductAdapter(ProductActivity.this, -1, ProductXmlPullParser.getListFromFile(ProductActivity.this));
            produceListView.setAdapter(productAdapter);

           // Log.i("StackSites", "adapter size = "+ mAdapter.getCount());
        }
    }

    public String obtainURL(String search) {
        String URL = "http://www.SupermarketAPI.com/api.asmx/SearchByProductName?APIKEY=6471b24741&ItemName=orange%20juice";
        return URL;
    }
}
