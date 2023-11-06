package edu.vassar.cmpu203.lunchbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;


public class SearchActivity extends AppCompatActivity {

    private EditText searchEditText, distanceFilterEditText;
    private Spinner priceFilterSpinner;
    private RadioGroup sortRadioGroup;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        searchEditText = findViewById(R.id.searchEditText);
        priceFilterSpinner = findViewById(R.id.priceFilterSpinner);
        distanceFilterEditText = findViewById(R.id.distanceFilterEditText);
        sortRadioGroup = findViewById(R.id.sortRadioGroup);
        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(v -> performSearch());
//        replaced with the line above
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                performSearch();
//            }
//        });
    }

    private void performSearch() {
        String searchTerm = searchEditText.getText().toString();
        String priceFilter = priceFilterSpinner.getSelectedItem().toString();
        String distanceFilter = distanceFilterEditText.getText().toString();
        String sortOption = sortRadioGroup.getCheckedRadioButtonId() == R.id.proximityRadioButton ? "prox" : "rating";

        // TODO: Use these values to perform the search and display results.
    }
}
