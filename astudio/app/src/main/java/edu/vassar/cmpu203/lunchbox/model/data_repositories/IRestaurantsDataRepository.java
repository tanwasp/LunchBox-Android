package edu.vassar.cmpu203.lunchbox.model.data_repositories;

import java.util.List;
import java.util.Set;

import edu.vassar.cmpu203.lunchbox.model.IFilter;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.model.User;

public interface IRestaurantsDataRepository {
//    static void searchRestaurants(List<String> restaurantNames, Set<IFilter> filters, String sort, User curUser) {
//    }
    void getAllRestaurants(IDataRepositoryCallback callback);

    void addRestaurant(Restaurant restaurant, IDataRepositoryCallback callback);
}
