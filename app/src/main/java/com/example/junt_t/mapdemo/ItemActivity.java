//package com.example.junt_t.mapdemo;
//
///**
// * Created by Junt_T on 2015/10/21 0021.
// */
//
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ItemActivity extends Activity {
//
//    private static final String TAG = "ItemActivity";
//
//    ListView storeListView;
//
//    ListView itemListView;
//    ItemAdapter productAdapter;
//
//
//    List<String> productSearchArr = new ArrayList<String>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.store_list);
////        storeService = new StoreService();
////        List<Store> storeList = storeService.getStore();
//        setContentView(R.layout.item_list);
//
//
//        productSearchArr.add("chicken");
//        productSearchArr.add("beef");
//        productSearchArr.add("apple");
//        productSearchArr.add("banana");
//        productSearchArr.add("orange");
//        productSearchArr.add("chocolate");
//        productSearchArr.add("chip");
//
//        //Get reference to our ListView
//        itemListView = (ListView) findViewById(R.id.productList);
//
//        /**
//         * Set the click listener to launch the browser when a row is clicked.
//         */
//        itemListView.setOnItemClickListener(
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
//                        //Intent intent = new Intent(ItemActivity.this, MapsActivity.class);
//                    }
//
//                });
//
//
//        productAdapter = new ItemAdapter(ItemActivity.this, -1, productSearchArr);
//        storeListView.setAdapter(productAdapter);
//    }
//}