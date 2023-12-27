package edu.vassar.cmpu203.lunchbox.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Locale;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.model.Review;
import edu.vassar.cmpu203.lunchbox.model.User;
import edu.vassar.cmpu203.lunchbox.view.IReviewView;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentReviewBinding;

public class ReviewFragment extends Fragment implements IReviewView {

    private final IReviewView.Listener listener;
    private FragmentReviewBinding binding;
    private final Review review;
    private User user;

    public ReviewFragment(@NonNull IReviewView.Listener listener, Review rev, User user) {
        this.listener = listener;
        this.review = rev;
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentReviewBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up the review details in the layout
        binding.usernameView.setText(review.getUsername());
        binding.ratingView.setText(String.valueOf(review.getRating()));
        binding.reviewBodyView.setText(review.getBody());

        // Format the date (assuming review.getDate() returns a Date object)
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(review.getDate());
        binding.dateView.setText(formattedDate);


        // Check if the current user's ID matches the user ID that made the review
        if (user != null && user.getUid().equals(review.getUid())) {
            // User's ID matches, so set the edit and delete buttons visible
            binding.editButton.setVisibility(View.VISIBLE);
            binding.deleteButton.setVisibility(View.VISIBLE);
        // Set onClickListener for the edit button
        binding.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNavigateToEditReview(review);
            }
        });

        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });
        } else {
            // User's ID does not match, so hide the edit and delete buttons
            binding.editButton.setVisibility(View.GONE);
            binding.deleteButton.setVisibility(View.GONE);
        }

    }

    // Method to show delete confirmation dialog
    private void showDeleteConfirmationDialog() {
        View bottomSheetView = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_delete_confirmation, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(bottomSheetView);

        // Set onClickListener for the confirm delete button
        bottomSheetView.findViewById(R.id.confirmDeleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog
                bottomSheetDialog.dismiss();
                // Notify the listener to delete the review
                listener.onDeleteReview(review);
            }
        });

        // Set onClickListener for the cancel button
        bottomSheetView.findViewById(R.id.cancelDeleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog
                bottomSheetDialog.dismiss();
            }
        });

        // Show the bottom sheet dialog
        bottomSheetDialog.show();
    }
}
