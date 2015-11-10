package com.example.junt_t.mapdemo;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Junt_T on 2015/10/31 0031.
 */
public class ProductXmlHandle {

    public static class Product {
        private String ItemName = null;
        private String ItemDescription = null;
        private String ItemCategory = null;
        private String ItemID = null;
        private String ItemImage = null;
        private String AisleNumber = null;

        private Product(String ItemName, String ItemDescription, String ItemCategory,
                        String ItemID, String ItemImage, String AisleNumber) {
            this.ItemName = ItemName;
            this.ItemDescription = ItemDescription;
            this.ItemCategory = ItemCategory;
            this.ItemID = ItemID;
            this.ItemImage = ItemImage;
            this.AisleNumber = AisleNumber;
        }

        public String getItemName() {
            return ItemName;
        }

        public String getItemDescription() {
            return ItemDescription;
        }

        public String getItemCategory() {
            return ItemCategory;
        }

        public String getItemID() {
            return ItemID;
        }

        public String getItemImage() {
            return ItemImage;
        }

        public String getAisleNumber() {
            return AisleNumber;
        }
    }

    //  dont use namespace
    private static final String ns = null;

    //  instantiate the Parser
    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readArrayOfProduct(parser);
        } finally {
            in.close();
        }
    }

    //  do the work of processing the ArrayOfProduct
    private List readArrayOfProduct (XmlPullParser parser) throws XmlPullParserException, IOException {
        List productList = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "ArrayOfProduct");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the product tag
            if (name.equals("Product")) {
                productList.add(readProduct(parser));
            } else {
                skip(parser);
            }
        }
        return productList;
    }

    private Product readProduct (XmlPullParser parser) throws  XmlPullParserException, IOException {
        String ItemName = null;
        String ItemDescription = null;
        String ItemCategory = null;
        String ItemID = null;
        String ItemImage = null;
        String AisleNumber = null;

        parser.require(XmlPullParser.START_TAG, ns, "<ArrayOfProduct");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the product tag
            if (name.equals("Itemname")) {
                ItemName =  readItemName(parser);
            } else if (name.equals("ItemDescription")) {
                ItemDescription = readItemDescription(parser);
            } else if (name.equals("ItemCategory")) {
                ItemCategory = readItemCategory(parser);
            } else if (name.equals("ItemImage")) {
                ItemImage = readItemImage(parser);
            } else if (name.equals("AisleNumber")) {
                AisleNumber = readAisleNumber(parser);
            } else {
                skip(parser);
            }
        }

        return new Product(ItemName, ItemDescription, ItemCategory,ItemID, ItemImage, AisleNumber);
    }

    // ProcessesItemName tags in the Product.
    private String readItemName (XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "Itemname");
        String ItemName = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Itemname");

        return  ItemName;
    }

    private String readItemDescription (XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "ItemDescription");
        String ItemDescription = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "ItemDescription");

        return  ItemDescription;
    }

    private String readItemCategory (XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "ItemCategory");
        String ItemCategory = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "ItemCategory");

        return  ItemCategory;
    }

    private String readItemID (XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "ItemID");
        String ItemID = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "ItemID");

        return  ItemID;
    }

    private String readItemImage (XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "ItemImage");
        String ItemImage = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "ItemImage");

        return  ItemImage;
    }

    private String readAisleNumber (XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "AisleNumber");
        String AisleNumber = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "AisleNumber");

        return AisleNumber;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    //  Skip Tags that dont need
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
