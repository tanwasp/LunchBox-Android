package edu.vassar.cmpu203.lunchbox.view;

/**
 * Interface defining functionality of RestaurantFragment
 */
public interface IRestaurantView {

    /**
     * Listener for RestaurantFragment
     */
    interface Listener {
        void onNavigateToPostReview(String restaurantId, String restaurantName);
    }
}
