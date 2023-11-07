package edu.vassar.cmpu203.lunchbox.view;

import android.view.View;
import android.widget.Button;
import edu.vassar.cmpu203.lunchbox.R;

public class HomeView implements IHomeView {
    private View rootView;
    private Button btnNavigateToSearch;
    private Listener listener;

    public interface Listener {
        void onNavigateToSearch();
    }

    public HomeView(View rootView, Listener listener) {
        this.rootView = rootView;
        this.listener = listener;
        btnNavigateToSearch = rootView.findViewById(R.id.btnNavigateToSearch);
        btnNavigateToSearch.setOnClickListener(v -> listener.onNavigateToSearch());
    }

    @Override
    public void navigateToSearch() {
        // This will be handled by the listener in the activity
    }
}
