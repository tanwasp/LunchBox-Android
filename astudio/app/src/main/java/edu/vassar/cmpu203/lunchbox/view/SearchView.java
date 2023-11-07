package edu.vassar.cmpu203.lunchbox.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;

public class SearchView implements ISearchView {
    private View rootView;
    private EditText searchEditText, distanceFilterEditText;
    private Spinner priceFilterSpinner;
    private RadioGroup sortRadioGroup;
    private Button searchButton;
    private Listener listener;

    public interface Listener {
        void onPerformSearch(String searchTerm, String priceFilter, String distanceFilter, String sortOption);
    }

    public SearchView(View rootView, Listener listener) {
        this.rootView = rootView;
        this.listener = listener;
        searchEditText = rootView.findViewById(R.id.searchEditText);
        priceFilterSpinner = rootView.findViewById(R.id.priceFilterSpinner);
        distanceFilterEditText = rootView.findViewById(R.id.distanceFilterEditText);
        sortRadioGroup = rootView.findViewById(R.id.sortRadioGroup);
        searchButton = rootView.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(v -> {
            String searchTerm = searchEditText.getText().toString();
            String priceFilter = priceFilterSpinner.getSelectedItem().toString();
            String distanceFilter = distanceFilterEditText.getText().toString();
            String sortOption = sortRadioGroup.getCheckedRadioButtonId() == R.id.proximityRadioButton ? "prox" : "rating";
            listener.onPerformSearch(searchTerm, priceFilter, distanceFilter, sortOption);
        });
    }
    @Override
    public void displaySearchResults(List<Restaurant> searchResults) {
        // Update the RecyclerView with the results
    }
}
