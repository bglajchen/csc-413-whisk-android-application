package com.example.junt_t.mapdemo;

import com.google.gson.Gson;

/**
 * Created by Junt_T on 2015/11/4 0004.
 */
public class Store {
    private String Storename;
    private String Address;
    private String City;
    private String State;
    private String Zip;
    private String Phone;
    private String StoreId;

    public void setStorename(String Storename) {
        this.Storename = Storename;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setState(String State) {
        this.State = State;
    }

    public void setZip(String Zip) {
        this.Zip = Zip;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setStoreId(String StoreId) {
        this.StoreId = StoreId;
    }

    public String getStorename() {
        return Storename;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getZip() {
        return Zip;
    }

    public String getPhone() {
        return Phone;
    }

    public String getStoreId() {
        return StoreId;
    }
}
