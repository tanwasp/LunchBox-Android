package edu.vassar.cmpu203.lunchbox.model.data_repositories;

public interface UserDataRepository {
    void createUser(String username, String email, String password, DataRepositoryCallback callback);
    void loginUser(String email, String password, DataRepositoryCallback callback);
    // ... other methods for user data operations
}
