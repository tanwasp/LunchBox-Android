//package edu.vassar.cmpu203.lunchbox.model.data_repositories;
//
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Set;
//
//import edu.vassar.cmpu203.lunchbox.model.IFilter;
//import edu.vassar.cmpu203.lunchbox.model.Restaurant;
//import edu.vassar.cmpu203.lunchbox.model.User;
//
//public class FStoreRestaurantDataRepo implements IRestaurantsDataRepository{
//
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
////        queries firestore for restaurants
////        for each in restaurantNames, query firestore for restaurant with that name and those filters.
////        if price is not null, add equal comparison of price to query
////        if maxDistance is not null, add comparison of distance to query
////        distance should be the distance between curUser.location and restaurant.coordinates
////        construct Restaurant for each query result if it passes all filters
////        add to list
////        then sort and return list
////        if ("prox".equals(sort)) {
////            Collections.sort(matches, Comparator.comparingDouble(r -> (double) r.distanceToUser));
////        } else {
////            Collections.sort(matches, Comparator.comparingDouble(r -> (double) - r.getRating()));
////        }
////        return matches;
//    }
//
//}
