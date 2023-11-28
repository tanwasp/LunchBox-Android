package edu.vassar.cmpu203.lunchbox.view;

public interface IHomeView {
    interface Listener{
        void onNavigateToSearch();

        void getUserReviewsNavToProfile();
        //void onNavigateToNotifications();
        //void onNavigateToFindFriends();
    }
}
