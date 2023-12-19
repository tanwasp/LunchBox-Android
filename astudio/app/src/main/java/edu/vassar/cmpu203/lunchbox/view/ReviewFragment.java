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

        // Set onClickListener for the edit button
        binding.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the bottom sheet dialog with edit/delete options
                showOptionsBottomSheet();
            }
        });
    }

    private void showOptionsBottomSheet() {
        // Create an instance of the BottomSheetDialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());

        // Inflate the bottom sheet layout
        View bottomSheetView = getLayoutInflater().inflate(R.layout.fragment_manage_review, null);

        // Set onClickListeners for the options (Edit and Delete)
        bottomSheetView.findViewById(R.id.editOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss(); // Dismiss the bottom sheet
                // Call the listener method for editing the review
                listener.onManageReview(review);
            }
        });

        bottomSheetView.findViewById(R.id.deleteOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss(); // Dismiss the bottom sheet
                // You can implement the logic for deleting the review here
                Toast.makeText(requireContext(), "Review deleted", Toast.LENGTH_SHORT).show();
            }
        });

        // Set the inflated view to the bottom sheet
        bottomSheetDialog.setContentView(bottomSheetView);

        // Show the bottom sheet dialog
        bottomSheetDialog.show();
    }
}
