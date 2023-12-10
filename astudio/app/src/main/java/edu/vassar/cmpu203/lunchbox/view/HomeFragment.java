package edu.vassar.cmpu203.lunchbox.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;


//import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentHomeBinding;

/**
 * View fragment that allows users to navigate the app through the home screen.
 */
public class HomeFragment extends Fragment implements IHomeView{
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
        this.binding.btnNavigateToSearch.setOnClickListener(v -> listener.onNavigateToSearch());
        this.binding.btnLogout.setOnClickListener(v -> listener.onLogout());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}