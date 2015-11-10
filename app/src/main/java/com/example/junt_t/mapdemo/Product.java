package com.example.junt_t.mapdemo;

/**
 * Created by Junt_T on 2015/11/2 0002.
 */
public class Product {
    private String ItemName = null;
    private String ItemDescription = null;
    private String ItemCategory = null;
    private String ItemID = null;
    private String ItemImage = null;
    private String AisleNumber = null;

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public void setItemDescription(String ItemDescription) {
        this.ItemDescription = ItemDescription;

    }

    public void  setItemCategory(String ItemCategory) {
        this.ItemCategory = ItemCategory;
    }

    public void setItemID(String ItemID) {
        this.ItemID = ItemID;
    }

    public void setItemImage(String ItemImage) {
        this.ItemImage = ItemImage;
    }

    public  void setAisleNumber(String AisleNumber) {
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
