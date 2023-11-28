package edu.vassar.cmpu203.lunchbox.model.data_repositories;

public interface IConnectionDataRepo {
    void getFollowers(String userId, IDataRepositoryCallback callback);
    void getFollowing(String userId, IDataRepositoryCallback callback);
    void getConnections(String userId, IDataRepositoryCallback callback);

    void getLatestFriendsReviews(String userId);
    void getFriendsReviews(String userId, String restaurantId);
}
