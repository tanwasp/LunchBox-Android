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