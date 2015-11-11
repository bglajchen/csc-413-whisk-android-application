package com.example.junt_t.mapdemo;

/**
 * Created by Junt_T on 2015/10/21 0021.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.FileNotFoundException;

public class ProductActivity extends Activity {
    String searchProduct = "orange juice";
    String searchStoreID = "e6k3fjw75k";
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
        produceListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    /**
                     * Callback method to be invoked when an item in this AdapterView has
                     * been clicked.
                     * <p/>
                     * Implementers can call getItemAtPosition(position) if they need
                     * to access the data associated with the selected item.
                     *
                     * @param parent   The AdapterView where the click happened.
                     * @param view     The view within the AdapterView that was clicked (this
                     *                 will be a view provided by the adapter)
                     * @param position The position of the view in the adapter.
                     * @param id       The row id of the item that was clicked.
                     */
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ProductActivity.this, MapsActivity.class);

                    }

                });


        /*
         * If network is available download the xml from the Internet.
		 * If not then try to use the local file from last time.
		 */
        if (isNetworkAvailable()) {
            Log.i("StackSites", "starting download Task");
            DownloadTask download = new DownloadTask();
            download.execute(obtainURL(searchProduct, searchStoreID));
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
    private class DownloadTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... arg) {
            //Download the file
            try {
                Downloader.DownloadFromUrl(arg[0], openFileOutput("COMMERCIAL_SearchForItem.xml", Context.MODE_PRIVATE));
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

    public String obtainURL(String searchItem, String searchStoreId) {
        String replacedItem = searchItem.replace(" ", "%20");

        String URL = "http://www.supermarketapi.com/api.asmx/COMMERCIAL_SearchForItem?APIKEY=6471b24741&StoreId=" + searchStoreId + "&ItemName=" + replacedItem;
        return URL;
    }
}
