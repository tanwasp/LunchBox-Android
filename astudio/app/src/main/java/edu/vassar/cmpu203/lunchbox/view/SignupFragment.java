package edu.vassar.cmpu203.lunchbox.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.lunchbox.databinding.FragmentSignupBinding;


public class SignupFragment extends AuthFragment implements ISignupView {
    private FragmentSignupBinding binding;
    private Listener listener;

    public SignupFragment(@NonNull Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentSignupBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Text watchers for real-time validation
        binding.username.addTextChangedListener(createTextWatcher());
        binding.password.addTextChangedListener(createTextWatcher());
        binding.email.addTextChangedListener(createTextWatcher());

        // Click listener for login button
        this.binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.username.getText().toString();
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();
                binding.loading.setVisibility(View.VISIBLE);
                listener.checkUsernameExists(username, email, password);
                hideKeyboard(v);
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
        String username = binding.username.getText().toString();
        String password = binding.password.getText().toString();
        String email = binding.email.getText().toString();

        if (!isValidEmail(email)) {
            binding.email.setError("Invalid email address");
        } else {
            binding.email.setError(null); // Clears the error
        }

        if (!isValidPassword(password)) {
            binding.password.setError("Password must be at least 6 characters");
        } else {
            binding.password.setError(null); // Clears the error
        }

        boolean isValid = isValidUsername(username) && isValidPassword(password) && isValidEmail(email);
        binding.signUpButton.setEnabled(isValid);
    }

    public void onSignupResult(boolean isSuccess, String message) {
        this.binding.loading.setVisibility(View.GONE);
        if (isSuccess) {
            // Handle success
        } else {
            Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
        }
    }

    public void onUsernameExistsResult(boolean exists, String message) {
        if (exists) {
            Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG).show();
            this.binding.loading.setVisibility(View.GONE);
        }
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}