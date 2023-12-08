package edu.vassar.cmpu203.lunchbox.view;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.model.Restaurant;

/**
 * Interface defining functionality of AddReviewFragment
 */
public interface IAddReviewView {

    /**
     * Listener for AddReviewFragment
     */
    interface Listener{
        void onAddReview(float rating, String comment, String id, int priceSymbol, String restaurantName);
    }

}
