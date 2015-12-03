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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductActivity extends Activity {

    private static final String TAG = "ProductActivity";

    ListView storeListView;
    StoreAdapter storeAdapter;

    ListView itemListView;
    ProductAdapter productAdapter;

    List<Product> productTempList = new ArrayList<Product>();
    List<Product> productResultList = new ArrayList<Product>();
    List<Store> storeResultList = new ArrayList<Store>();

    List<String> productSearchArr = new ArrayList<String>();

    List<Store> storePassParam;

    StoreService storeService;

    String city = null;
    String state = null;
    String productName = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.store_list);
//        storeService = new StoreService();
//        List<Store> storeList = storeService.getStore();
        setContentView(R.layout.store_list);


        productSearchArr.add("chicken");
        productSearchArr.add("beef");
        productSearchArr.add("apple");
        productSearchArr.add("banana");
        productSearchArr.add("orange");
        productSearchArr.add("chocolate");
        productSearchArr.add("chip");

        //Get reference to our ListView
        storeListView = (ListView) findViewById(R.id.storeList);
        itemListView = (ListView) findViewById(R.id.productList);

        /**
         * Set the click listener to launch the browser when a row is clicked.
         */
//       itemListView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    /**
//                     * Callback method to be invoked when an item in this AdapterView has
//                     * been clicked.
//                     * <p/>
//                     * Implementers can call getItemAtPosition(position) if they need
//                     * to access the data associated with the selected item.
//                     *
//                     * @param parent   The AdapterView where the click happened.
//                     * @param view     The view within the AdapterView that was clicked (this
//                     *                 will be a view provided by the adapter)
//                     * @param position The position of the view in the adapter.
//                     * @param id       The row id of the item that was clicked.
//                     */
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent = new Intent(ProductActivity.this, MapsActivity.class);
//
//                    }
//
//                });


        /*
         * If network is available download the xml from the Internet.
		 * If not then try to use the local file from last time.
		 */
        if (isNetworkAvailable()) {
            Log.i("ProductActivity", "starting download Task");

            DownloadTaskStore downloadStore;
            DownloadTaskProduct downloadProduct;
            downloadStore = new DownloadTaskStore();
            downloadStore.execute("http://www.supermarketapi.com/api.asmx/StoresByCityState?APIKEY=6471b24741&SelectedCity=San%20Francisco&SelectedState=CA");
            storeResultList = StoreXmlPullParser.getListFromFile(ProductActivity.this);

            for (int i = 0; i < storeResultList.size(); i++) {
                downloadProduct = new DownloadTaskProduct();
                downloadProduct.execute("http://www.supermarketapi.com/api.asmx/COMMERCIAL_SearchForItem?APIKEY=APIKEY" +
                        "&StoreId=" + storeResultList.get(i).getStoreId() +
                        "&ItemName=" + "chicken");
                productResultList = ProductXmlPullParser.getListFromFile(ProductActivity.this);
                storeResultList.get(i).setAisleNumber(productResultList.get(0).getAisleNumber());
            }
            storeAdapter = new StoreAdapter(ProductActivity.this, -1,storeResultList);
            storeListView.setAdapter(storeAdapter);
//            for (int i = 0; i < productSearchArr.size();i++ ) {



//            productResultList.add(ProductXmlPullParser.getListFromFile(ProductActivity.this).get(0));


               // if (!ProductXmlPullParser.getListFromFile(ProductActivity.this).isEmpty()) {

//                }
//            if (!productTempList.isEmpty()) {
//                productResultList.add(productTempList.get(0));
//            }

//            }
//            productAdapter = new ProductAdapter(ProductActivity.this, -1, ProductXmlPullParser.getListFromFile(ProductActivity.this));
//            itemListView.setAdapter(productAdapter);


            //storeListView.setAdapter(storeAdapter);


            //  download and parse the xml file for stores
//            downloadStore = new DownloadTaskStore();
//            downloadStore.execute(obtainStoreURL(city, state));
//            storeResultList = StoreXmlPullParser.getListFromFile(ProductActivity.this);

//            for (int i = 0; i < storeResultList.size(); i++ ) {
//                String storeID = storeResultList.get(i).getStoreId();
//                for (int j = 0; j < productSearchArr.size(); j++) {
//                    String itemName = productSearchArr.get(j);
//
//                    //  download and parse the xml file for items
//                    downloadProduct = new DownloadTaskProduct();
//                    downloadProduct.execute(obtainProductURL(itemName, storeID));
//                    productResultList = ProductXmlPullParser.getListFromFile(ProductActivity.this);

                    // getting the first item name from the prodoct list and
                    //  adding to the store list
                  /*  if (productResultList != null) {
                        productName = productResultList.get(0).getItemName();
                    }
                    if (!"NOITEM".equals(productName)) {
                        storeResultList.get(i).setItemName(productName);
                    }*/
//                }
//            }

          /*  Collections.sort(storeResultList);
            for (int i = 0; i < 5; i++) {
                storePassParam.add(storeResultList.get(i));
            }*/

            //test the product list from parsing xml file
//            productAdapter = new ProductAdapter(ProductActivity.this, -1,productResultList);
//            itemListView.setAdapter(productAdapter);
           /* storeAdapter = new StoreAdapter(ProductActivity.this, -1,storeResultList);
            storeListView.setAdapter(storeAdapter);*/
        } else {

            //test the product list from parsing xml file
            productAdapter = new ProductAdapter(ProductActivity.this, -1,productResultList);
            itemListView.setAdapter(productAdapter);

           /* storeAdapter = new StoreAdapter(ProductActivity.this, -1,storeResultList);
            storeListView.setAdapter(storeAdapter);*/
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
    private class DownloadTaskProduct extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... arg0) {
            //Download the file
            try {
                Downloader.DownloadFromUrl(arg0[0],
                        openFileOutput("COMMERCIAL_SearchForItem.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            productAdapter = new ProductAdapter(ProductActivity.this, -1,ProductXmlPullParser.getListFromFile(ProductActivity.this));
            //itemListView.setAdapter(productAdapter);
        }
    }

    public String obtainProductURL(String searchItem) {
        String replacedItem = searchItem.replace(" ", "%20");

       // String[] urlStringArray = new String[storeObjList.size()];
//        for (int i = 0; i < storeObjList.size(); i++) {
//            String URL = "http://www.supermarketapi.com/api.asmx/" +
//                    "COMMERCIAL_SearchForItem?APIKEY=6471b24741&StoreId="
//                    + storeObjList.get(i).getStoreId() + "&ItemName=" + replacedItem;
//            urlStringArray[i] = URL;
//        }
//        return urlStringArray;
        String URL = "http://www.supermarketapi.com/api.asmx/SearchByProductName?APIKEY=6471b24741&ItemName="
                + replacedItem;
        return URL;
    }

    private class DownloadTaskStore extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... arg0) {
            //Download the file
            try {
                Downloader.DownloadFromUrl(arg0[0],
                        openFileOutput("StoresByCityState.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

//        @Override
//        protected void onPostExecute(Void result) {
//            String storeID = StoreXmlPullParser.getListFromFile(ProductActivity.this).get(1).getStoreId();
//            DownloadTaskProduct downloadProduct;
//            downloadProduct = new DownloadTaskProduct();
//            downloadProduct.execute("http://www.supermarketapi.com/api.asmx/COMMERCIAL_SearchForItem?APIKEY=APIKEY" +
//                    "&StoreId=" + "e6k3fjw187" +
//                    "&ItemName=" +"Apple");
//        }
    }

    public String obtainStoreURL(String city, String state) {
//        String cityReplace = city.replace(" ", "%20");
        String URL = "http://www.supermarketapi.com/api.asmx/StoresByCityState?APIKEY=6471b24741&SelectedCity=San%20Francisco&SelectedState=CA";
        return URL;
    }
}
