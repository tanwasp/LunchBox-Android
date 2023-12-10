package edu.vassar.cmpu203.lunchbox.view;

/**
 * Interface defining functionality of AddRestaurantFragment
 */
public interface IAddRestaurantView {

    /**
     * Listener for AddRestaurantFragment
     */
    interface Listener{
        void addRestaurant(String name, String address, String city, String state, String country, String postalCode, String lat, String lon);
    }
}
