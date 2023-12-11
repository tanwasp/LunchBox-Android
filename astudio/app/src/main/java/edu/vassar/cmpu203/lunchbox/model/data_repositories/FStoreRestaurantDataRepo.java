package edu.vassar.cmpu203.lunchbox.model.data_repositories;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import edu.vassar.cmpu203.lunchbox.model.FirestoreSingleton;
import edu.vassar.cmpu203.lunchbox.model.IFilter;
import edu.vassar.cmpu203.lunchbox.model.Location;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.model.Review;
import edu.vassar.cmpu203.lunchbox.model.User;

public class FStoreRestaurantDataRepo implements IRestaurantsDataRepository {
    @Override
    public void getAllRestaurants(IDataRepositoryCallback callback) {
        FirebaseFirestore db = FirestoreSingleton.getInstance().getFirestore();
        db.collection("restaurants")
//                .limit(10)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Restaurant> restaurants = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Restaurant r = (document.toObject(Restaurant.class));
                            r.setRestaurantId(document.getId());
                            r.setCity(document.getString("city"));
                            r.setState(document.getString("state"));
                            r.setCountry(document.getString("country"));
                            r.setPostalCode(document.getString("postalCode"));
                            r.setDistanceToUser(-1.0f);
                            GeoPoint geoPoint = document.getGeoPoint("coordinates");
                            if (geoPoint != null) {
                                // Create Location object and set latitude and longitude
                                Location loc = new Location((float) geoPoint.getLongitude(), (float) geoPoint.getLatitude());
                                r.setLoc(loc);
                            }
                            restaurants.add(r);
                        }
                        callback.onSuccess(restaurants);
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    @Override
    public void addRestaurant(Restaurant restaurant, IDataRepositoryCallback callback) {
        FirebaseFirestore db = FirestoreSingleton.getInstance().getFirestore();
        db.collection("restaurants")
                .add(restaurant).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(task.getResult());
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

//    public static void searchRestaurants(List<String> restaurantNames, int price, int maxDistance, String sort, User curUser, IDataRepositoryCallback callback) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("restaurants")
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        List<Restaurant> matches = null;
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            Restaurant res = document.toObject(Restaurant.class);
//                            if (restaurantNames.contains(res.getName())) {
//                                if (price != 0 && res.getPriceRange() != price) {
//                                    continue;
//                                }
//                                if (maxDistance != 0 && res.getDistanceToUser() > maxDistance) {
//                                    continue;
//                                }
//                                matches.add(res);
//                            }
//                        }
//                        if ("prox".equals(sort)) {
//                            Collections.sort(matches, Comparator.comparingDouble(r -> (double) r.getDistanceToUser()));
//                        } else {
//                            Collections.sort(matches, Comparator.comparingDouble(r -> (double) - r.getRating()));
//                        }
//                        callback.onSuccess(matches);
//                    } else {
//                        callback.onFailure();
//                    }
//                });
//    }
//        queries firestore for restaurants
//        for each in restaurantNames, query firestore for restaurant with that name and those filters.
//        if price is not null, add equal comparison of price to query
//        if maxDistance is not null, add comparison of distance to query
//        distance should be the distance between curUser.location and restaurant.coordinates
//        construct Restaurant for each query result if it passes all filters
//        add to list
//        then sort and return list
//        if ("prox".equals(sort)) {
//            Collections.sort(matches, Comparator.comparingDouble(r -> (double) r.distanceToUser));
//        } else {
//            Collections.sort(matches, Comparator.comparingDouble(r -> (double) - r.getRating()));
//        }
//        return matches;


}
