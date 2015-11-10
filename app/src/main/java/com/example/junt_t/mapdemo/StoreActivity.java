package com.example.junt_t.mapdemo;

/**
 * Created by Junt_T on 2015/11/4 0004.
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

public class StoreActivity extends Activity {
    String searchCity = null;
    String searchState = null;
    ListView produceListView;
    //ResponseSupermarketAPI responseObj;
    ProductAdapter productAdapter;
    // AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
            StoreXmlPullParser.getListFromFile(StoreActivity.this);
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
                Downloader.DownloadFromUrl(obtainURL(searchCity,searchState), openFileOutput("StoresByCityState.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            StoreXmlPullParser.getListFromFile(StoreActivity.this);
            // Log.i("StackSites", "adapter size = "+ mAdapter.getCount());
        }
    }

    public String obtainURL(String city, String state) {
        String URL = "http://www.supermarketapi.com/api.asmx/StoresByCityState?APIKEY=6471b24741&SelectedCity=" + city + "&SelectedState=" + state;
        return URL;
    }
}
