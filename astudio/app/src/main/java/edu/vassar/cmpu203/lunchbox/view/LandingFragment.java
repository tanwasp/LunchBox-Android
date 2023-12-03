package edu.vassar.cmpu203.lunchbox.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.vassar.cmpu203.lunchbox.databinding.FragmentLandingBinding;

public class LandingFragment extends Fragment implements ILandingView {
    private FragmentLandingBinding binding;
    private ILandingView.Listener listener;

    public LandingFragment(@NonNull ILandingView.Listener listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        this.binding = FragmentLandingBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        this.binding.btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listener.onNavigateToLogin();
            }
        });

        this.binding.btnSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listener.onNavigateToSignup();
            }
        });

    }
}
