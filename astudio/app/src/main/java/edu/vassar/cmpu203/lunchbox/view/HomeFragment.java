package edu.vassar.cmpu203.lunchbox.view;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;


//import edu.vassar.cmpu203.lunchbox.R;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.controller.MainActivity;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentHomeBinding;
import edu.vassar.cmpu203.lunchbox.model.Coordinate;

/**
 * View fragment that allows users to navigate the app through the home screen.
 */
public class HomeFragment extends Fragment implements IHomeView {
    private FragmentHomeBinding binding;
    private Listener listener;

    public HomeFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IHomeView.Listener) {
            listener = (IHomeView.Listener) context;
        } else {
//            throw new RuntimeException(context.toString() + " must implement IHomeView.Listener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentHomeBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() instanceof MainActivity) {
            MainActivity activity = (MainActivity) getActivity();

            Toolbar toolbar = activity.findViewById(R.id.toolbar);
            TextView tvLocation = toolbar.findViewById(R.id.tvLocation);

            this.binding.btnNavigateToSearch.setOnClickListener(v -> {
                tvLocation.setVisibility(View.GONE);
                listener.onNavigateToSearch();
            });

            this.binding.btnSearchUsers.setOnClickListener(v -> {
                listener.onNavigateToSearchUsers();
            });

            float lat = 37.421997f;
            float lon = -122.084f;

            Coordinate coordinate = new Coordinate(lat, lon);
            // Get address from coordinates
//            Coordinate coordinate = activity.getUserCoordinates();
            String address;
            if (coordinate == null) {
                Snackbar.make(view, "Location not found", Snackbar.LENGTH_LONG).show();
                address = "Location not found";
            } else {
                address = coordinate.getAddress(getContext());
            }
            updateLocationInView(tvLocation, address);
            tvLocation.setOnClickListener(v -> {
                // Handle TextView click
                listener.onNavigateToSearchLocation();
            });
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private void updateLocationInView(TextView tvLocation, String location) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            tvLocation.setText(location);
            tvLocation.setVisibility(View.VISIBLE);
        }
    }
}