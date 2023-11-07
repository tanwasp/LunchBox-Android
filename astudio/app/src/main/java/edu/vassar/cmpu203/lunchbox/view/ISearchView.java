package edu.vassar.cmpu203.lunchbox.view;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.model.Restaurant;

public interface ISearchView {
    void displaySearchResults(List<Restaurant> searchResults);
    // Other methods related to the search UI
}
