package edu.vassar.cmpu203.lunchbox.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentManageReviewBinding;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentSearchBinding;
import edu.vassar.cmpu203.lunchbox.model.Review;
import edu.vassar.cmpu203.lunchbox.view.IManageReviewView;
import edu.vassar.cmpu203.lunchbox.view.recyclerview.RestaurantAdapter;

public class ManageReviewFragment extends Fragment implements IManageReviewView {
    private FragmentManageReviewBinding binding;
    private IManageReviewView.Listener listener;
    private Review existingReview;  // Store the existing review

    public ManageReviewFragment(@NonNull IManageReviewView.Listener listener, Review existingReview) {
        this.listener = listener;
        this.existingReview = existingReview;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentManageReviewBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up click listener for the "Update Review" button
        this.binding.buttonUpdateReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve updated values from UI elements
                float updatedRating = binding.ratingBar.getRating();
                String updatedComment = binding.editTextComment.getText().toString();
                String updatedPriceSymbol = binding.spinnerPrice.getSelectedItem().toString();
                if (!updatedPriceSymbol.contains("$")) {
                    updatedPriceSymbol = "";
                }

                // Pass updated data to the listener
                listener.onUpdateReview(existingReview, updatedRating, updatedComment, updatedPriceSymbol.length());
            }
        });

        // Pre-fill UI components with existing review details
        binding.ratingBar.setRating(existingReview.getRating());
        binding.editTextComment.setText(existingReview.getBody());
        // Set selected item in spinner based on existing price range
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.price_filter_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerPrice.setAdapter(adapter);
        binding.spinnerPrice.setSelection(existingReview.getPriceRange());

        // Update button text to reflect the editing action
        binding.buttonUpdateReview.setText(getString(R.string.update_review_button));
    }
}
