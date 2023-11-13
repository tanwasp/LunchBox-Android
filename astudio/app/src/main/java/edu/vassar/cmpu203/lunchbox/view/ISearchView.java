package edu.vassar.cmpu203.lunchbox.view;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.model.Restaurant;



public interface ISearchView {

    /* interface for contract that listener to view events must fulfill. */
    interface Listener{
        /**
//         * Called when the user has provided an amount of cash to pay for the sale.
//         * @param amount the amount of cash provided
//         * @param view the view the event originated from
         */
        void onPerformSearch(String searchTerm, String priceFilter, String distanceFilter, String sortOption);
        void onNavigateToRestaurant(Restaurant restaurant, Boolean reversible);

        void onNavigateToAddRestaurant();
    }

    void updateSearchResults(List<Restaurant> searchResults);
    // Other methods related to the search UI
}
