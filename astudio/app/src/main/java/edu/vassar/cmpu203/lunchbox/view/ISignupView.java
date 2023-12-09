package edu.vassar.cmpu203.lunchbox.view;

/**
 * Interface defining functionality of SignupFragment
 */
public interface ISignupView {
    /**
     * Listener for SignupFragment
     */
    interface Listener{
        void onSignup(String username, String email, String password);
        void checkUsernameExists(String username, String email, String password);
    }
}
