package edu.vassar.cmpu203.lunchbox.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import edu.vassar.cmpu203.lunchbox.view.IManageReviewView;
import edu.vassar.cmpu203.lunchbox.view.recyclerview.RestaurantAdapter;

public class ManageReviewFragment extends Fragment implements IManageReviewView {
    private FragmentManageReviewBinding binding;
    private IManageReviewView.Listener listener;

    public ManageReviewFragment(@NonNull IManageReviewView.Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentManageReviewBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up click listener for the "Add Review" button
        this.binding.buttonUpdateReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
