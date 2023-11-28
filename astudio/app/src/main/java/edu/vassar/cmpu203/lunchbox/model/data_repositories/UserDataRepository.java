package edu.vassar.cmpu203.lunchbox.model.data_repositories;

public interface UserDataRepository {
    void createUser(String username, String email, String password, IDataRepositoryCallback callback);
    void checkUsernameExists(String username, IDataRepositoryCallback callback);
    void loginUser(String email, String password, IDataRepositoryCallback callback);
}
