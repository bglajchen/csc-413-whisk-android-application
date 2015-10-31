package com.example.junt_t.mapdemo;

/**
 * Created by Junt_T on 2015/10/21 0021.
 */


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class IngredientItem  extends Activity {
    String search = null;
    ListView produceListView;
    ResponseSupermarketAPI responseObj;
    GsonXml gsonXml;
    String url;
    AdapterProduct adapterProduct;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Bundle bundle = getIntent().getExtras();

        produceListView = (ListView) findViewById(R.id.product_list);
        client = new AsyncHttpClient();

        final XmlParserCreator parserCreator = new XmlParserCreator() {
            @Override
            public XmlPullParser createParser() {
                try {
                    return XmlPullParserFactory.newInstance().newPullParser();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        client.get(obtainURL(search),null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                String xml = new String(responseBody);
               // gson = new Gson();
                gsonXml= new GsonXmlBuilder()
                        .setXmlParserCreator(parserCreator)
                        .create();
                String xml1 = "<ArrayOfProduct> <Product> <Itemname> Hersheys 2% Reduced Fat Chocolate Milk - 3-8 Fl. Oz. </Itemname> <ItemDescription> Grade A. Vitamins A & D added. UHT. 1/3 Less fat than whole milk. Fat reduced from 8 g to 5 g per serving. Product of USA. </ItemDescription> <ItemCategory>Beverages</ItemCategory> <ItemID>27926</ItemID> <ItemImage> http://smapistorage.blob.core.windows.net/thumbimages/960013914_100x100.jpg </ItemImage> <AisleNumber>Aisle:N/A</AisleNumber> </Product></ArrayOfProduct>";
                responseObj = gsonXml.fromXml(xml1, ResponseSupermarketAPI.class);//gson.fromJson(responseStr, ResponseSupermarketAPI.class);

                adapterProduct = new AdapterProduct(responseObj.getArrayOfProduct().getProduct(), IngredientItem.this);
               // produceListView.setAdapter(adapterProduct);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
            {
                Toast toast = Toast.makeText(IngredientItem.this, "Error, could not resolve URL", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    public String obtainURL( String productSearch)
    {
        String URL = "http://www.SupermarketAPI.com/api.asmx/SearchByProductName?APIKEY=6471b24741&ItemName=2%25milk";
        return URL;
    }
}
