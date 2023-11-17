package edu.vassar.cmpu203.lunchbox.view;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.model.Restaurant;



public interface ISearchView {

    /* interface for contract that listener to view events must fulfill. */
    interface Listener{
        /**
         * Called when the user performs a search.
         * @param searchTerm - they key word or phrase to search by with string contains
         * @param distanceFilter - the distance to filter by
         * @param priceFilter - the price range to filter by
         * @param sortOption - sort by proximity to the user or highest to lowest rating of the restaurants
         */
        void onPerformSearch(String searchTerm, String priceFilter, String distanceFilter, String sortOption);

        /**
         * navigates to restaurant
         * @param restaurant
         * @param reversible
         * @param popCount
         */
        void onNavigateToRestaurant(Restaurant restaurant, boolean reversible, int popCount);

        /**
         * navigates to add restaurant fragment
         */
        void onNavigateToAddRestaurant();
    }

    /**
     * refreshes the recycler view of restaurants
     * @param searchResults
     */
    void updateSearchResults(List<Restaurant> searchResults);
    // Other methods related to the search UI
}
