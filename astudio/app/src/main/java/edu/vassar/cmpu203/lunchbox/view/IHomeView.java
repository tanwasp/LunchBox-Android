package edu.vassar.cmpu203.lunchbox.view;

/**
 * Interface defining functionality of HomeFragment
 */
public interface IHomeView {

    /**
     * Listener for HomeFragment
     */
    interface Listener{
        void onNavigateToSearch();

        void onNavigateToSearchUsers();

        void getUserReviewsNavToProfile();
        //void onNavigateToNotifications();
        //void onNavigateToFindFriends();

        void onNavigateToSearchLocation();
    }
}
