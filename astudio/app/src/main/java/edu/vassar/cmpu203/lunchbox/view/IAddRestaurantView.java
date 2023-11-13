package edu.vassar.cmpu203.lunchbox.view;

public interface IAddRestaurantView {
    interface Listener{
        void addRestaurant(String name, String address, String city, String state, String country, String postalCode, String lat, String lon);
    }
}
