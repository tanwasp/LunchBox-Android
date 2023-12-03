package edu.vassar.cmpu203.lunchbox.view;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.lunchbox.databinding.FragmentAddReviewBinding;

public class AddReviewFragment extends Fragment implements IAddReviewView {
    private FragmentAddReviewBinding binding;
    private Listener listener;
    private String restId;

    private RatingBar ratingBar;
    private EditText commentEditText;
    private Spinner priceSpinner;
    private Button addReviewButton;
    private String restaurantName;

    public AddReviewFragment(Listener listener, String restId, String restaurantName){
        this.listener = listener;
        this.restId = restId;
        this.restaurantName = restaurantName;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentAddReviewBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up click listener for the "Add Review" button
        this.binding.buttonAddReview.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when the "Add Review" button is clicked.
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                // Retrieve values from UI elements
                float rating = AddReviewFragment.this.binding.ratingBar.getRating();
                String comment = AddReviewFragment.this.binding.editTextComment.getText().toString();
                String priceSymbol = AddReviewFragment.this.binding.spinnerPrice.getSelectedItem().toString();
                if (!(priceSymbol.contains("$"))){
                    priceSymbol = "";
                }

                // Pass data to the listener
                listener.onAddReview(rating, comment, restId, priceSymbol.length(), restaurantName );
            }
        });
    }

}