package edu.vassar.cmpu203.lunchbox.model.data_repositories;

import com.google.firebase.firestore.FirebaseFirestore;

import edu.vassar.cmpu203.lunchbox.model.FirestoreSingleton;

public class FirestoreFriendsDataRepository implements FriendsDataRepository{

    @Override
    public void getLatestActivity() {

    }

    @Override
    public void searchMembers() {

    }

    @Override
    public void getProfile() {

    }

    @Override
    public void getConnections(){
        FirebaseFirestore db = FirestoreSingleton.getInstance().getFirestore();

    }
}
