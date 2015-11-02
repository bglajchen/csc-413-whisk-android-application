package com.example.junt_t.mapdemo;

/**
 * Created by Junt_T on 2015/10/21 0021.
 */


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
public class IngredientItem  extends Activity {
    String search = null;
    ListView produceListView;
    //ResponseSupermarketAPI responseObj;
    AdapterProduct adapterProduct;
   // AsyncHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);
        InputStream stream = null;

        produceListView = (ListView) findViewById(R.id.product_list);

        ProductXmlHandle productXmlHandler = new ProductXmlHandle();

        List<ProductXmlHandle.Product> productsList = null;
        String ItemName = null;
        String ItemDescription = null;
        String ItemCategory = null;
        String ItemID = null;
        String ItemImage = null;
        String AisleNumber = null;

        try {
            stream = downloadUrl(obtainURL(search));
            productsList = productXmlHandler.parse(stream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        adapterProduct = new AdapterProduct(productsList, IngredientItem.this);
        produceListView.setAdapter(adapterProduct);



        /*
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
        */
    }

    public String obtainURL( String productSearch)
    {
        String URL = "http://www.SupermarketAPI.com/api.asmx/SearchByProductName?APIKEY=6471b24741&ItemName=apple";
        return URL;
    }

    // Given a string representation of a URL, sets up a connection and gets
    // an input stream.
    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        try {
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
           // conn.connect();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            return in;
        } finally {
               conn.disconnect();
        }
    }
}
