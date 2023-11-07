package edu.vassar.cmpu203.lunchbox.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;

import java.util.List;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.model.Restaurant;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment implements ISearchView {
    private FragmentSearchBinding binding;
    private final Listener listener;
    private View rootView;
    private EditText searchEditText, distanceFilterEditText;
    private Spinner priceFilterSpinner;
    private RadioGroup sortRadioGroup;
    private Button searchButton;



//    public SearchFragment(View rootView, @NonNull Listener listener) {
//        this.rootView = rootView;
//        this.listener = listener;
//        searchEditText = rootView.findViewById(R.id.searchEditText);
//        priceFilterSpinner = rootView.findViewById(R.id.priceFilterSpinner);
//        distanceFilterEditText = rootView.findViewById(R.id.distanceFilterEditText);
//        sortRadioGroup = rootView.findViewById(R.id.sortRadioGroup);
//        searchButton = rootView.findViewById(R.id.searchButton);
//
//        searchButton.setOnClickListener(v -> {
//            String searchTerm = searchEditText.getText().toString();
//            String priceFilter = priceFilterSpinner.getSelectedItem().toString();
//            String distanceFilter = distanceFilterEditText.getText().toString();
//            String sortOption = sortRadioGroup.getCheckedRadioButtonId() == R.id.proximityRadioButton ? "prox" : "rating";
//            listener.onPerformSearch(searchTerm, priceFilter, distanceFilter, sortOption);
//        });
//    }

    public SearchFragment(@NonNull Listener listener){
        this.listener = listener;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentSearchBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void displaySearchResults(List<Restaurant> searchResults) {
        // Update the RecyclerView with the results
    }
}
