//package edu.vassar.cmpu203.lunchbox;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RadioGroup;
//import android.widget.Spinner;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//
//import edu.vassar.cmpu203.lunchbox.model.*;
//
//public class SearchActivity extends AppCompatActivity {
//
//    private EditText searchEditText, distanceFilterEditText;
//    private Spinner priceFilterSpinner;
//    private RadioGroup sortRadioGroup;
//    private Button searchButton;
//    private static RestaurantLibrary lib;
//    private static ReviewsLibrary revLib;
//    private User curUser;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_search);
//
//        curUser = new User("Me", 30, -90);
//        lib = new RestaurantLibrary();
//        revLib = new ReviewsLibrary();
//
//        searchEditText = findViewById(R.id.searchEditText);
//        priceFilterSpinner = findViewById(R.id.priceFilterSpinner);
//        distanceFilterEditText = findViewById(R.id.distanceFilterEditText);
//        sortRadioGroup = findViewById(R.id.sortRadioGroup);
//        searchButton = findViewById(R.id.searchButton);
//
//        searchButton.setOnClickListener(v -> performSearch());
//    }
//
//    private void performSearch() {
//        String searchTerm = searchEditText.getText().toString();
//        String priceFilter = priceFilterSpinner.getSelectedItem().toString();
//        String distanceFilter = distanceFilterEditText.getText().toString();
//        String sortOption = sortRadioGroup.getCheckedRadioButtonId() == R.id.proximityRadioButton ? "prox" : "rating";
//
//        // Convert filters to a HashSet as in the text-based prototype
//        HashSet<IFilter> filters = new HashSet<>();
//        if (!priceFilter.isEmpty()) {
//            filters.add(new PriceFilter(priceFilter));
//        }
//        if (!distanceFilter.isEmpty()) {
//            try {
//                filters.add(new LocFilter(Integer.parseInt(distanceFilter), curUser));
//            } catch (NumberFormatException e) {
//                // Handle invalid distance input
//            }
//        }
//
//        // Perform the search using the filters and sort option
//        ArrayList<Restaurant> matches = lib.search(searchTerm, filters, sortOption, curUser);
//
//        // Update the RecyclerView with the results
//        updateRecyclerView(matches);
//    }
//
//    private void updateRecyclerView(ArrayList<Restaurant> restaurants) {
//        // Assuming you have a RecyclerView set up with an adapter
//        RestaurantAdapter adapter = (RestaurantAdapter) recyclerView.getAdapter();
//        if (adapter != null) {
//            adapter.setRestaurants(restaurants);
//            adapter.notifyDataSetChanged();
//        }
//    }
//
//}
