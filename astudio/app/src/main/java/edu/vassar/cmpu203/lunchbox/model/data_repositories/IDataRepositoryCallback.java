package edu.vassar.cmpu203.lunchbox.model.data_repositories;

public interface IDataRepositoryCallback {
    void onSuccess(Object result);
//    void onSuccess(String username, String email, String firebaseUserId);
    void onFailure(Exception e);
}
