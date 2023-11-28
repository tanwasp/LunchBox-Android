package edu.vassar.cmpu203.lunchbox.view;

public interface ISignupView {
    interface Listener{
        void onSignup(String username, String email, String password);

        void checkUsernameExists(String username, String email, String password);
    }
}
