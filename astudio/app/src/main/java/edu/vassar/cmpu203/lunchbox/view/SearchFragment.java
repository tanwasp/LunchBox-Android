package edu.vassar.cmpu203.lunchbox.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentSearchBinding;
import edu.vassar.cmpu203.lunchbox.view.recyclerview.RestaurantAdapter;

public class SearchFragment extends Fragment implements ISearchView, RestaurantAdapter.OnItemClickListener {
    private FragmentSearchBinding binding;
    private final Listener listener;
    private View rootView;
    private EditText searchEditText, distanceFilterEditText;
    private Spinner priceFilterSpinner;
    private RadioGroup sortRadioGroup;
    private Button searchButton;

    private RestaurantAdapter restaurantAdapter;
    private RecyclerView searchResultsRecyclerView;

    public SearchFragment(@NonNull Listener listener){
        this.listener = listener;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentSearchBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        // Set up the RecyclerView
        searchResultsRecyclerView = view.findViewById(R.id.searchResultsRecyclerView);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        // Initialize your adapter with an empty list or null
        // Inside SearchFragment onViewCreated method
        restaurantAdapter = new RestaurantAdapter(view.getContext(), new ArrayList<>(), this);
        searchResultsRecyclerView.setAdapter(restaurantAdapter);

        this.binding.searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String term = SearchFragment.this.binding.searchTermText.getText().toString();
                String price = SearchFragment.this.binding.priceFilterSpinner.getSelectedItem().toString();
                String distance = SearchFragment.this.binding.distanceFilterEditText.getText().toString();
                String sort;
                int buttonID = binding.sortRadioGroup.getCheckedRadioButtonId();
                System.out.println(buttonID);
                if (buttonID != -1) {
                    RadioButton radioButton = binding.getRoot().findViewById(buttonID);
                    sort = radioButton.getText().toString();
                    if (sort.equals("Proximity")){
                        sort = "prox";
                    } else if (sort.equals("Rating")){
                        sort = "rating";
                    }
                } else {
                    sort = "rating";
                }

                listener.onPerformSearch(term, price, distance, sort);
                hideKeyboard(v);
            }
        });
    }

    @Override
    public void updateSearchResults(List<Restaurant> searchResults) {
        // Update the adapter with the new search results and refresh the RecyclerView
        restaurantAdapter.setRestaurants(searchResults); // Make sure you have a method in your adapter to update the data
        restaurantAdapter.notifyDataSetChanged();
    }

    public void showNoResultsMessage(boolean show) {
        if (show) {
            binding.noResultsTextView.setVisibility(View.VISIBLE);
        } else {
            binding.noResultsTextView.setVisibility(View.GONE);
        }
    }
    @Override
    public void onNavigateToRestaurant(Restaurant restaurant){
        listener.onNavigateToRestaurant(restaurant, true);
    }

    @Override
    public void onNavigateToAddRestaurant(){
        listener.onNavigateToAddRestaurant();
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}