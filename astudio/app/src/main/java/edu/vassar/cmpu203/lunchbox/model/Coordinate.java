package edu.vassar.cmpu203.lunchbox.model;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * Represents coordinates
 */
public class Coordinate implements Serializable {
    /**
     * Latitude of the location.
     */
    private float lat;

    /**
     * Longitude of the location.
     */
    private float lon;

    /**
     * Creates a location with the given latitude and longitude.
     */
    public Coordinate(float lat, float lon){
        this.lat = lat;
        this.lon = lon;
    }

    public float getLat(){
        return this.lat;
    }

    public float getLon(){
        return this.lon;
    }

    public Coordinate(){}

    /**
     * Computes the distance between two points using the Haversine formula.
     *
     * @param loc Location (coordinates) of the 2nd point.
     *
     * @return The distance between the two points in miles.
     */
    public float haversine(Coordinate loc){

        double earthRadius = 6371.0;

        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(this.lat);
        double long1Rad = Math.toRadians(this.lon);
        double lat2Rad = Math.toRadians(loc.lat);
        double long2Rad = Math.toRadians(loc.lon);

        // Haversine formula
        double dlong = long2Rad - long1Rad;
        double dlat = lat2Rad - lat1Rad;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dlong / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        // Convert distance from kilometers to miles
        return (float) distance * 0.621371f;
    }

    /**
     * Converts Location to String
     *
     * @return string representation of the object
     */
    public String toString(){
        return "(" + this.lat + ", " + this.lon + ")";
    }

    public String getAddress(Context context) {
        System.out.println("Getting address from coordinates: " + this.toString());
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(this.lat, this.lon, 1);
            System.out.println("Got addresses: " + addresses);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                return address.getLocality(); // Or other parts of the address
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unknown Location";
    }
}
