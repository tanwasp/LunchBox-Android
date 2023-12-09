package edu.vassar.cmpu203.lunchbox.view;

/**
 * Interface defining functionality of UserProfileFragment
 */
public interface IUserProfileView {
    /**
     * Listener for UserProfileFragment
     */
    interface Listener {
        void onNavigateToMyFriends();
    }
}
