package edu.vassar.cmpu203.lunchbox.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.graphics.Color;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;


import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.controller.MainActivity;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentSearchBinding;
import edu.vassar.cmpu203.lunchbox.view.recyclerview.RestaurantAdapter;

/**
 * View fragment that allows users to search restaurants
 */
public class SearchFragment extends Fragment implements ISearchView, RestaurantAdapter.OnItemClickListener {
    private FragmentSearchBinding binding;
    private final Listener listener;
    private RestaurantAdapter restaurantAdapter;
    private RecyclerView searchResultsRecyclerView;

    public SearchFragment(@NonNull Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentSearchBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupSearchToolbar(view);

        // Set up the RecyclerView
        searchResultsRecyclerView = view.findViewById(R.id.searchResultsRecyclerView);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        // Initialize your adapter with an empty list or null
        // Inside SearchFragment onViewCreated method
        restaurantAdapter = new RestaurantAdapter(view.getContext(), new ArrayList<>(), this);
        searchResultsRecyclerView.setAdapter(restaurantAdapter);
    }

    private void performSearch(String term) {
        String price = binding.priceFilterSpinner.getSelectedItem().toString();
        String distance = binding.distanceFilterEditText.getText().toString();
        String sort = getSortCriteria();

        listener.onPerformSearch(term, price, distance, sort);
    }

    private String getSortCriteria() {
        int buttonID = binding.sortRadioGroup.getCheckedRadioButtonId();
        if (buttonID != -1) {
            RadioButton radioButton = binding.getRoot().findViewById(buttonID);
            String sort = radioButton.getText().toString();
            if (sort.equals("Proximity")) {
                return "prox";
            } else if (sort.equals("Rating")) {
                return "rating";
            }
        }
        return "rating"; // Default sort criteria
    }

    /**
     * Updates the search results
     *
     * @param searchResults
     */
    @Override
    public void updateSearchResults(List<Restaurant> searchResults) {
        // Update the adapter with the new search results and refresh the RecyclerView
        restaurantAdapter.setRestaurants(searchResults); // Make sure you have a method in your adapter to update the data
        restaurantAdapter.notifyDataSetChanged();
    }

    /**
     * Shows or hides the no results message
     *
     * @param show
     */
    public void showNoResultsMessage(boolean show) {
        if (show) {
            binding.noResultsTextView.setVisibility(View.VISIBLE);
        } else {
            binding.noResultsTextView.setVisibility(View.GONE);
        }
    }

    /**
     * Navigates to the restaurant fragment
     *
     * @param restaurant
     */
    @Override
    public void onNavigateToRestaurant(Restaurant restaurant) {
        listener.onNavigateToRestaurant(restaurant, true, 0);
    }

    /**
     * Navigates to the add restaurant fragment
     */
    @Override
    public void onNavigateToAddRestaurant() {
        listener.onNavigateToAddRestaurant();
    }

    /**
     * Hides the keyboard
     *
     * @param view
     */
    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



    private void setupSearchToolbar(View view) {

        Toolbar toolbarSearch = view.findViewById(R.id.toolbar_search);
        toolbarSearch.setVisibility(View.VISIBLE); // Make the toolbar visible

        // Set the navigation icon
        toolbarSearch.setNavigationIcon(R.drawable.baseline_arrow_back_ios_24);

        // Define navigation click listener
        toolbarSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the back button action
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });

        SearchView searchRestaurantView = toolbarSearch.findViewById(R.id.searchRestaurantView);
        int searchEditTextId = androidx.appcompat.R.id.search_src_text;
        TextView searchEditText = (TextView) searchRestaurantView.findViewById(searchEditTextId);
        searchEditText.setTextColor(Color.WHITE);
        searchEditText.setHintTextColor(Color.WHITE);
        searchRestaurantView.setIconified(false); // Open the SearchView
        searchRestaurantView.requestFocus(); // Request focus if needed

        searchRestaurantView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                hideKeyboard(view);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return false;
            }
        });
    }
}