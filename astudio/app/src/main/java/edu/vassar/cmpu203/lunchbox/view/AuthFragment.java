package edu.vassar.cmpu203.lunchbox.view;

import androidx.fragment.app.Fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AuthFragment extends Fragment {
    protected static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    protected final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

    protected boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    protected boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

    protected boolean isValidUsername(String username) {
        return username != null && !username.trim().isEmpty() && username.length() >= 3 && username.length() <= 15;
    }
}
