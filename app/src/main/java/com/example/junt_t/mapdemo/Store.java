package com.example.junt_t.mapdemo;

import java.util.ArrayList;
import java.util.*;

/**
 * Created by Junt_T on 2015/11/4 0004.
 */
public class Store implements Comparator<Store>, Comparable<Store> {
    private String Storename;
    private String Address;
    private String City;
    private String State;
    private String Zip;
    private String Phone;
    private String StoreId;
    private ArrayList<String> itemsName;

    public  Store(){

    }
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

    public void setItemName(String itemName) {
        this.itemsName.add(itemName);
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

    public ArrayList getItemName() {
        return itemsName;
    }

    /**
     * Compares this object to the specified object to determine their relative
     * order.
     *
     * @param another the object to compare to this instance.
     * @return a negative integer if this instance is less than {@code another};
     * a positive integer if this instance is greater than
     * {@code another}; 0 if this instance has the same order as
     * {@code another}.
     * @throws ClassCastException if {@code another} cannot be converted into something
     *                            comparable to {@code this} instance.
     */
    @Override
    public int compareTo(Store another) {
        return (this.itemsName.size()) - (another.getItemName().size());
    }

    /**
     * Compares the two specified objects to determine their relative ordering. The ordering
     * implied by the return value of this method for all possible pairs of
     * {@code (lhs, rhs)} should form an <i>equivalence relation</i>.
     * This means that
     * <ul>
     * <li>{@code compare(a,a)} returns zero for all {@code a}</li>
     * <li>the sign of {@code compare(a,b)} must be the opposite of the sign of {@code
     * compare(b,a)} for all pairs of (a,b)</li>
     * <li>From {@code compare(a,b) > 0} and {@code compare(b,c) > 0} it must
     * follow {@code compare(a,c) > 0} for all possible combinations of {@code
     * (a,b,c)}</li>
     * </ul>
     *
     * @param lhs an {@code Object}.
     * @param rhs a second {@code Object} to compare with {@code lhs}.
     * @return an integer < 0 if {@code lhs} is less than {@code rhs}, 0 if they are
     * equal, and > 0 if {@code lhs} is greater than {@code rhs}.
     * @throws ClassCastException if objects are not of the correct type.
     */
    @Override
    public int compare(Store lhs, Store rhs) {
        return lhs.getItemName().size() - rhs.getItemName().size();
    }
}
