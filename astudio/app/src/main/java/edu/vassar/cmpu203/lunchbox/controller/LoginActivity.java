package edu.vassar.cmpu203.lunchbox.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

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
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = task.getResult().getUser();
                        String firebaseUserId = user.getUid();

                        // Update Firebase profile with username
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();

                        user.updateProfile(profileUpdates).addOnCompleteListener(profileTask -> {
                                    if (profileTask.isSuccessful()) {
                                        Log.d("Profile Update", "User profile updated.");

                                        storeUserDataInFirestore(user, username, email, firebaseUserId);
                                    } else {
                                        // Handle failure in updating Firebase profile
                                        Log.e("Profile Update", "Failed to update user profile", profileTask.getException());
                                        signupFragment.onSignupResult(false, "Signup failed: Failed to update user profile");
                                    }
                                });
                    } else {
                        // Authentication failed
                        signupFragment.onSignupResult(false, "Signup failed: " + task.getException().getMessage());
                    }
                });
    }

    public void onLogin(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Authentication succeeded
                        FirebaseUser user = task.getResult().getUser();
                        String firebaseUserId = user.getUid();

                        Intent resultData = new Intent();

                        resultData.putExtra("email", user.getEmail());
                        resultData.putExtra("firebaseUserId", firebaseUserId);

                        setResult(Activity.RESULT_OK, resultData);
                        finish();
                    } else {
                        // Authentication failed
                        loginFragment.onLoginResult(false, "Login failed: " + task.getException().getMessage());
                    }
                });
    }

    private void storeUserDataInFirestore(FirebaseUser user, String username, String email, String firebaseUserId) {
        // Create a new user map to add to Firestore
        Map<String, Object> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("email", email);

        // Save user info to Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(firebaseUserId).set(userData)
                .addOnSuccessListener(aVoid -> {
                    // Data added successfully
                    Intent resultData = new Intent();
                    resultData.putExtra("username", username);
                    resultData.putExtra("email", email);
                    resultData.putExtra("firebaseUserId", firebaseUserId);
                    setResult(Activity.RESULT_OK, resultData);
                    finish();
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                    Log.e("Firestore Error", "Error writing document", e);
                    signupFragment.onSignupResult(false, "Signup failed: Failed to add user details to Firestore");
                });
    }

//    Getting the username from the database

//    public void onLogin(String email, String password) {
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        FirebaseUser user = task.getResult().getUser();
//                        String firebaseUserId = user.getUid();
//
//                        // Fetch username from Firestore
//                        FirebaseFirestore db = FirebaseFirestore.getInstance();
//                        db.collection("users").document(firebaseUserId).get()
//                                .addOnSuccessListener(documentSnapshot -> {
//                                    if (documentSnapshot.exists()) {
//                                        String username = documentSnapshot.getString("username");
//
//                                        Intent resultData = new Intent();
//                                        resultData.putExtra("username", username);
//                                        resultData.putExtra("email", user.getEmail());
//                                        resultData.putExtra("firebaseUserId", firebaseUserId);
//
//                                        setResult(Activity.RESULT_OK, resultData);
//                                        finish();
//                                    } else {
//                                        // Handle case where user data is not found in Firestore
//                                        loginFragment.onLoginResult(false, "User data not found");
//                                    }
//                                })
//                                .addOnFailureListener(e -> {
//                                    // Handle any errors here
//                                    loginFragment.onLoginResult(false, "Failed to fetch user data: " + e.getMessage());
//                                });
//                    } else {
//                        // Authentication failed
//                        loginFragment.onLoginResult(false, "Login failed: " + task.getException().getMessage());
//                    }
//                });
//    }

}