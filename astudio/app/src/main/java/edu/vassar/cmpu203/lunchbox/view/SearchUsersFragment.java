package edu.vassar.cmpu203.lunchbox.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.vassar.cmpu203.lunchbox.R;
import edu.vassar.cmpu203.lunchbox.databinding.FragmentSearchBinding;
import edu.vassar.cmpu203.lunchbox.model.User;
import edu.vassar.cmpu203.lunchbox.view.recyclerview.UserAdapter;

/**
 * View fragment that allows users to search for other users
 */
public class SearchUsersFragment extends Fragment implements ISearchUsersView, UserAdapter.OnItemClickListener {

    private FragmentSearchBinding binding;
    private final Listener listener;
    private UserAdapter userAdapter;
    private RecyclerView searchResultsRecyclerView;

    public SearchUsersFragment(@NonNull Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentSearchBinding.inflate(inflater);
        return this.binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupSearchToolbar(view);

        // Set up the RecyclerView
        searchResultsRecyclerView = view.findViewById(R.id.searchResultsRecyclerView);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        // Initialize your adapter with an empty list or null
        UserAdapter userAdapter = new UserAdapter(view.getContext(), new ArrayList<>(), this);
        searchResultsRecyclerView.setAdapter(userAdapter);
    }

    private void performUserSearch(String searchTerm) {
        listener.onPerformUserSearch(searchTerm);
    }

    @Override
    public void updateSearchResults(List<User> searchResults) {
        userAdapter.setUsers(searchResults);
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoResultsMessage(boolean show) {
        if (show) {
            binding.noResultsTextView.setVisibility(View.VISIBLE);
        } else {
            binding.noResultsTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNavigateToUserProfile(User user) {
        listener.onNavigateToUserProfile(user);
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void setupSearchToolbar(View view) {
        Toolbar toolbarSearch = view.findViewById(R.id.toolbar_search);
        toolbarSearch.setVisibility(View.VISIBLE);

        toolbarSearch.setNavigationIcon(R.drawable.baseline_arrow_back_ios_24);

        toolbarSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });

        SearchView searchUserView = toolbarSearch.findViewById(R.id.searchRestaurantView);
        int searchEditTextId = androidx.appcompat.R.id.search_src_text;
        SearchView.SearchAutoComplete searchEditText = (SearchView.SearchAutoComplete) searchUserView.findViewById(searchEditTextId);
        searchEditText.setTextColor(getResources().getColor(android.R.color.white));
        searchEditText.setHintTextColor(getResources().getColor(android.R.color.white));
        searchUserView.setIconified(false);
        searchUserView.requestFocus();

        searchUserView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performUserSearch(query);
                hideKeyboard(view);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performUserSearch(newText);
                return false;
            }
        });
    }
}
