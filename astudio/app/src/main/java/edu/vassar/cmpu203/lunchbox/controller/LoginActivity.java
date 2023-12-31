package edu.vassar.cmpu203.lunchbox.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

import edu.vassar.cmpu203.lunchbox.model.data_repositories.IDataRepositoryCallback;
import edu.vassar.cmpu203.lunchbox.model.data_repositories.FirestoreUserDataRepository;
import edu.vassar.cmpu203.lunchbox.view.ILoginView;
import edu.vassar.cmpu203.lunchbox.view.IMainView;
import edu.vassar.cmpu203.lunchbox.view.ISignupView;
import edu.vassar.cmpu203.lunchbox.view.LoginFragment;
import edu.vassar.cmpu203.lunchbox.view.MainView;


import edu.vassar.cmpu203.lunchbox.view.SignupFragment;

public class LoginActivity extends AppCompatActivity implements ISignupView.Listener, ILoginView.Listener {
    IMainView mainView;
    private SignupFragment signupFragment;
    private LoginFragment loginFragment;

    /**
     * Called when the login activity is first created.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mainView = new MainView(this);

        String action = getIntent().getStringExtra("action");
        if ("signup".equals(action)) {
            signupFragment = new SignupFragment(this);
            this.mainView.displayFragment(signupFragment, false, "signup", 0);
        } else {
            // Assume login if not signup
             loginFragment = new LoginFragment(this);
             this.mainView.displayFragment(loginFragment, false, "login", 0);
        }

        setContentView(this.mainView.getRootView());
    }
    /**
     * Called once the user signs up. Creates a new user in the database and sends data to main activity to create new user for the session
     * @param username
     * @param email
     * @param password
     */

    public void onSignup(String username, String email, String password) {
        FirestoreUserDataRepository repository = new FirestoreUserDataRepository();
        repository.createUser(username, email, password, new IDataRepositoryCallback() {
            @Override
            public void onSuccess(Object result) {
                String firebaseUserId = (String) result;
                Intent resultData = new Intent();
                resultData.putExtra("email", email);
                resultData.putExtra("firebaseUserId", firebaseUserId);
                setResult(Activity.RESULT_OK, resultData);
                finish();
            }

            @Override
            public void onFailure(Exception e) {
                signupFragment.onSignupResult(false, "Signup failed: " + e.getMessage());
            }
        });
    }

    /**
     * Checks if the username already exists in the database. If it does, it returns an error message. If not, it calls onSignup
     * @param username
     * @param email
     * @param password
     */
    public void checkUsernameExists(String username, String email, String password) {
        FirestoreUserDataRepository repository = new FirestoreUserDataRepository();
        String finalUsername = username.trim().toLowerCase();
        repository.checkUsernameExists(finalUsername, new IDataRepositoryCallback() {
            @Override
            public void onSuccess(Object result) {
                if ((Boolean) result) {
                    signupFragment.onUsernameExistsResult(true, "Username already exists");
                    return;
                } else {
                    signupFragment.onUsernameExistsResult(false, null);
                }
                onSignup(finalUsername, email, password);
            }
            @Override
            public void onFailure(Exception e) {
                signupFragment.onUsernameExistsResult(false, null);
            }
        });
    }

    /**
     * Called once the user logs in. Sends data to main activity to create new user for the session
     * @param email
     * @param password
     */
    public void onLogin(String email, String password) {
        FirestoreUserDataRepository repository = new FirestoreUserDataRepository();
        repository.loginUser(email, password, new IDataRepositoryCallback() {

            @Override
            public void onSuccess(Object result) {
                FirebaseUser user = (FirebaseUser) result;
                String firebaseUserId = user.getUid();

                Intent resultData = new Intent();

                resultData.putExtra("email", user.getEmail());
                resultData.putExtra("firebaseUserId", firebaseUserId);

                setResult(Activity.RESULT_OK, resultData);
                finish();
            }

            @Override
            public void onFailure(Exception e) {
                loginFragment.onLoginResult(false, "Login failed: " + e.getMessage());
            }
        });

    }
}