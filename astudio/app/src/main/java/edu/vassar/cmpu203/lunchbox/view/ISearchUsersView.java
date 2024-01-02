package edu.vassar.cmpu203.lunchbox.view;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.model.User;

/**
 * Interface defining functionality of SearchUsersFragment
 */
public interface ISearchUsersView {

    /* interface for contract that listener to view events must fulfill. */
    interface Listener {
        /**
         * Called when the user performs a search.
         *
         * @param searchTerm      The keyword or phrase to search by with string contains.
         */
        void onPerformUserSearch(String searchTerm);

        /**
         * Navigates to user profile or details.
         *
         * @param user The selected user.
         */
        void onNavigateToUserProfile(User user);

    }

    /**
     * Refreshes the recycler view of users.
     *
     * @param searchResults The list of users matching the search criteria.
     */
    void updateSearchResults(List<User> searchResults);

    /**
     * Informs the user if no results match the search criteria.
     *
     * @param show Boolean indicating whether to show or hide the "no results" message.
     */
    void showNoResultsMessage(boolean show);
}