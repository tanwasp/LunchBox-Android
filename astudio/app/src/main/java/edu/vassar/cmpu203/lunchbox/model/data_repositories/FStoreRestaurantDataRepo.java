//package edu.vassar.cmpu203.lunchbox.model.data_repositories;
//
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//
//import edu.vassar.cmpu203.lunchbox.model.Restaurant;
//
//public class FStoreRestaurantDataRepo implements IRestaurantsDataRepository{
//    @Override
//    public void getRestaurants() {
//
//    }
//
//    private void fetchRestaurantsData() {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("restaurants").get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                for (QueryDocumentSnapshot document : task.getResult()) {
//                    Restaurant restaurant = document.toObject(Restaurant.class);
//                    restaurantLibrary.addRestaurant(restaurant);
//                }
//            } else {
//                // Handle the error
//            }
//        });
//    }
//
//}
