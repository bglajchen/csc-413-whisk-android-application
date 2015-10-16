//package com.junt_t.googlemapdemp;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.util.Log;
//
//import static android.Manifest.*;
//import static android.Manifest.permission.ACCESS_FINE_LOCATION;
//import static android.support.v4.content.PermissionChecker.checkSelfPermission;
//
///**
// * Created by Junt_T on 2015/10/15 0015.
// * This class provide the user current location by using GPS or Network severs
// */
//public class locationTracker {
//    private final Context mContext;
//
//    // Flag for GPS status
//    boolean isGPSEnabled = false;
//
//    // Flag for network status
//    boolean isNetworkEnabled = false;
//
//    // Flag for GPS status
//    boolean canGetLocation = false;
//
//    Location location; // Location
//    double latitude; // Latitude
//    double longitude; // Longitude
//
//    // The minimum distance to change Updates in meters
//    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
//
//    // The minimum time between updates in milliseconds
//    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
//
//    // Declaring a Location Manager
//    protected LocationManager locationManager;
//
//    public locationTracker(Context context) {
//        this.mContext = context;
//        getLocation();
//    }
//
//    public Location getLocation() {
//        try {
//            locationManager = (LocationManager) mContext
//                    .getSystemService(LOCATION_SERVICE);
//
//            // Getting GPS status
//            isGPSEnabled = locationManager
//                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//            // Getting network status
//            isNetworkEnabled = locationManager
//                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//
//            if (!isGPSEnabled && !isNetworkEnabled) {
//                // No network provider is enabled
//            } else {
//                this.canGetLocation = true;
//                if (isNetworkEnabled) {
//                    locationManager.requestLocationUpdates(
//                            LocationManager.NETWORK_PROVIDER,
//                            MIN_TIME_BW_UPDATES,
//                            MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);
//                    Log.d("Network", "Network");
//                    if (locationManager != null) {
//                        location = locationManager
//                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                        if (location != null) {
//                            latitude = location.getLatitude();
//                            longitude = location.getLongitude();
//                        }
//                    }
//                }
//                // If GPS enabled, get latitude/longitude using GPS Services
//                if (isGPSEnabled) {
//                    if (location == null) {
//                        locationManager.requestLocationUpdates(
//                                LocationManager.GPS_PROVIDER,
//                                MIN_TIME_BW_UPDATES,
//                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//                        Log.d("GPS Enabled", "GPS Enabled");
//                        if (locationManager != null) {
//                            location = locationManager
//                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                            if (location != null) {
//                                latitude = location.getLatitude();
//                                longitude = location.getLongitude();
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return location;
//    }
//
//
//}
