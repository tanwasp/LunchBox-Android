package edu.vassar.cmpu203.lunchbox.model.data_repositories;

public interface DataRepositoryCallback {
    void onSuccess(Object result);
//    void onSuccess(String username, String email, String firebaseUserId);
    void onFailure(Exception e);
}
