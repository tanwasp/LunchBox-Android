package edu.vassar.cmpu203.lunchbox.view;

public interface ILoginView {
    interface Listener{
        void onLogin(String username, String password);
    }
}
