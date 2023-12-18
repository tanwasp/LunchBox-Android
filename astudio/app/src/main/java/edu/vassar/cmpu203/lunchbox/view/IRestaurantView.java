package edu.vassar.cmpu203.lunchbox.view;

import edu.vassar.cmpu203.lunchbox.model.Review;

/**
 * Interface defining functionality of RestaurantFragment
 */
public interface IRestaurantView {

    /**
     * Listener for RestaurantFragment
     */
    interface Listener {
        void onNavigateToPostReview(String restaurantId, String restaurantName);
        void onNavigateToReview(Review rev);
    }
}
