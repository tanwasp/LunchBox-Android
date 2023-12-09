package edu.vassar.cmpu203.lunchbox.view;

/**
 * Interface defining functionality of LandingFragment
 */
public interface ILandingView {
    /**
     * Listener for LandingFragment
     */
    interface Listener{
        void onNavigateToLogin();
        void onNavigateToSignup();
    }
}
