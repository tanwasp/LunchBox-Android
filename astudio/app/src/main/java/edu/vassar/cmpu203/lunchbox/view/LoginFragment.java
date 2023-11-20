package edu.vassar.cmpu203.lunchbox.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.lunchbox.databinding.FragmentLoginBinding;


public class LoginFragment extends AuthFragment implements ILoginView {
    private FragmentLoginBinding binding;
    private Listener listener;

    public LoginFragment(@NonNull Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentLoginBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Text watchers for real-time validation
//        binding.username.addTextChangedListener(createTextWatcher());
        binding.password.addTextChangedListener(createTextWatcher());
        binding.email.addTextChangedListener(createTextWatcher());

        // Click listener for login button
        this.binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String username = binding.username.getText().toString();
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();
//                binding.loading.setVisibility(View.VISIBLE);
                listener.onLogin(email, password);
            }
        });
    }


    private TextWatcher createTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not used
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateInput();
            }
        };
    }

    private void validateInput() {
//        String username = binding.username.getText().toString();
        String password = binding.password.getText().toString();
        String email = binding.email.getText().toString();
        boolean isValid = isValidPassword(password) && isValidEmail(email);
        binding.loginButton.setEnabled(isValid);
    }

    public void onLoginResult(boolean isSuccess, String message) {
//        binding.loading.setVisibility(View.GONE);
        if (isSuccess) {
            // Handle success
        } else {
            Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
        }
    }

}