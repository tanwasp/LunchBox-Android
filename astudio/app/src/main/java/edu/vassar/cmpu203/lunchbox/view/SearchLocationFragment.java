package edu.vassar.cmpu203.lunchbox.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.List;

import edu.vassar.cmpu203.lunchbox.BuildConfig;
import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.controller.MainActivity;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentSearchLocationBinding;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.view.recyclerview.LocationAdapter;


public class SearchLocationFragment extends Fragment implements ISearchLocationView, LocationAdapter.OnItemClickListener {
    /**
     * View fragment that allows users to change their location
     */
    private Listener listener;
    private FragmentSearchLocationBinding binding;
    private RecyclerView locationRecyclerView;
    private LocationAdapter locationAdapter;
    private List<String> locationList;

    public SearchLocationFragment(@NonNull Listener listener, List<String> locationList) {
        this.listener = listener;
        this.locationList = locationList;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentSearchLocationBinding.inflate(inflater);
        return this.binding.getRoot();
    }


    @Override
    public void updateLocationList(List<String> locationList) {
        this.locationList = locationList;
        locationAdapter.setLocations(locationList);
    }

    @Override
    public void onUseGivenLocation(String location) {
        listener.onUseGivenLocation(location);
    }

    @Override
    public void onUseCurrentLocation() {
        listener.onUseCurrentLocation();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView with LocationAdapter
        locationRecyclerView = view.findViewById(R.id.locationRecyclerView);
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ArrayList<String> locations = new ArrayList<>();
        locations.add("Current Location");
        locationAdapter = new LocationAdapter(view.getContext(), locations, this);
        locationRecyclerView.setAdapter(locationAdapter);

        // Initialize Places client
        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), BuildConfig.PLACES_API_KEY);
        }

        PlacesClient placesClient = Places.createClient(requireContext());

        // Set up the SearchView listener
        binding.searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
                FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                        .setTypeFilter(TypeFilter.CITIES)
                        .setSessionToken(token)
                        .setQuery(newText)
                        .build();

                placesClient.findAutocompletePredictions(request).addOnSuccessListener(response -> {
                    List<String> suggestions = new ArrayList<>();
                    for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                        String fullText = prediction.getPrimaryText(null).toString();
                        CharSequence secondaryText = prediction.getSecondaryText(null);
                        if (secondaryText != null) {
                            fullText += ", " + secondaryText.toString();
                        }
                        suggestions.add(fullText);
                    }
                    // Update your RecyclerView with these suggestions
                    locationAdapter.setLocations(suggestions);
                }).addOnFailureListener(exception -> {
                    if (exception instanceof ApiException) {
                        ApiException apiException = (ApiException) exception;
                        System.out.println("Place not found: " + apiException.getStatusCode());
                    }
                });
                return false;
            }

        });
    }

}