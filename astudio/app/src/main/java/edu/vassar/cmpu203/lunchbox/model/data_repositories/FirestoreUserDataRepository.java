package edu.vassar.cmpu203.lunchbox.model.data_repositories;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import edu.vassar.cmpu203.lunchbox.model.FirestoreSingleton;

public class FirestoreUserDataRepository implements UserDataRepository {
    @Override
    public void createUser(String username, String email, String password, DataRepositoryCallback callback) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = task.getResult().getUser();
                String firebaseUserId = user.getUid();

                // Update Firebase profile with username
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();

                user.updateProfile(profileUpdates).addOnCompleteListener(profileTask -> {
                    if (profileTask.isSuccessful()) {
                        Log.d("Profile Update", "User profile updated.");

                        storeUserDataInFirestore(username, email, firebaseUserId, callback);
                    } else {
                        // Handle failure in updating Firebase profile
                        Log.e("Profile Update", "Failed to update user profile", profileTask.getException());
                        callback.onFailure(profileTask.getException());
                    }
                });
            } else {
                callback.onFailure(task.getException());

            }
        });
    }

    @Override
    public void loginUser(String email, String password, DataRepositoryCallback callback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = task.getResult().getUser();
                callback.onSuccess(user);
            } else {
                callback.onFailure(task.getException());
            }
        });
    }

    private void storeUserDataInFirestore(String username, String email, String firebaseUserId, DataRepositoryCallback callback) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("email", email);

        FirebaseFirestore db = FirestoreSingleton.getInstance().getFirestore();
        db.collection("users").document(firebaseUserId).set(userData).addOnSuccessListener(aVoid -> {
            callback.onSuccess(firebaseUserId);
        }).addOnFailureListener(e -> {
            callback.onFailure(e);
        });
    }

//    private void updateFirebaseUsernameOnLogin(FirebaseUser user, String username) {
//        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
//        user.updateProfile(profileUpdates).addOnCompleteListener(profileTask -> {
//
//            if (profileTask.isSuccessful()) {
//                Log.d("Profile Update", "User profile updated.");
//            } else {
//                // Handle failure in updating Firebase profile
//                Log.e("Profile Update", "Failed to update user profile", profileTask.getException());
//            }
//        });
//    }

//    private Map getUserDataFromFirestore(String firebaseUserId) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        Map userData = new HashMap<>();
//        db.collection("users").document(firebaseUserId).get().addOnSuccessListener(documentSnapshot -> {
//            userData.put("username", documentSnapshot.get("username"));
//            userData.put("email", documentSnapshot.get("email"));
//        }).addOnFailureListener(e -> {
//            Log.e("Firestore", "Failed to get user data from Firestore", e);
//        });
//        return userData;
//    }

}
