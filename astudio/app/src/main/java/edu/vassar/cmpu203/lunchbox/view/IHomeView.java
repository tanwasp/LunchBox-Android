package edu.vassar.cmpu203.lunchbox.view;

public interface IHomeView {
    interface Listener{
        void onNavigateToSearch();

        void onNavigateToLogin();

        void onNavigateToSignup();

        void getUserReviewsNavToProfile();
        //void onNavigateToNotifications();
        //void onNavigateToFindFriends();
    }
}
