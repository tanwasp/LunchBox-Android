package edu.vassar.cmpu203.lunchbox.model;

import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Represents a singleton instance of the Firebase Firestore database,
 * ensuring that only one instance of the Firestore database is created
 */
public class FirestoreSingleton {
    private static FirestoreSingleton instance = null;
    private FirebaseFirestore firestore;

    private FirestoreSingleton() {
        firestore = FirebaseFirestore.getInstance();
    }

    public static synchronized FirestoreSingleton getInstance() {
        if (instance == null) {
            instance = new FirestoreSingleton();
        }
        return instance;
    }

    public FirebaseFirestore getFirestore() {
        return firestore;
    }
}
