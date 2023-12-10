package edu.vassar.cmpu203.lunchbox.view;

/**
 * Interface defining functionality of LoginFragment
 */
public interface ILoginView {
    /**
     * Listener for LoginFragment
     */
    interface Listener{
        void onLogin(String username, String password);
    }
}
