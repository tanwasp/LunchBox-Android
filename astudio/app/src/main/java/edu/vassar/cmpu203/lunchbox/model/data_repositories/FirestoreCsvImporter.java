package edu.vassar.cmpu203.lunchbox.model.data_repositories;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FirestoreCsvImporter {

    private Context context;

    public FirestoreCsvImporter(Context context) {
        this.context = context;
    }

    public void importCsvToFirestore() throws IOException {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        AssetManager assetManager = context.getAssets();
        InputStream is = assetManager.open("final_processed_restaurants_first_200.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        int count = 0;

        // Skip the header row
        br.readLine();

        while ((line = br.readLine()) != null && count < 200) {
            String[] values = line.split(",");
            Map<String, Object> data = new HashMap<>();

            // Generate a pseudorandom UUID for each document
            String docId = UUID.randomUUID().toString();

            // Populate data map
            data.put("name", values[1]);
            data.put("address", values[2]);
            data.put("city", values[3]);
            data.put("state", values[4]);
            data.put("country", values[5]);
            data.put("postalCode", values[6]);

            double latitude = 0;
            double longitude = 0;

            try {
                String latString = values[7];
                String[] coords = values[7].split("\\(");
                latitude = Double.parseDouble(coords[1].trim());
            } catch (Exception e) {
                System.out.println("Error parsing coordinates: " + e.getMessage());
            }

            try {
                String lngString = values[8];
                String[] coords = values[8].split("\\)");
                longitude = Double.parseDouble(coords[0].trim());
            } catch (Exception e) {
                System.out.println("Error parsing coordinates: " + e.getMessage());
            }
            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);

            if (latitude != 0 && longitude != 0) {
                GeoPoint geoPoint = new GeoPoint(latitude, longitude);
                data.put("coordinates", geoPoint);
                count ++;
            }
            else{
                throw new IllegalArgumentException("Invalid coordinates");
            }

            int defaultPriceRange = -1;
            float defaultRating = -1;

            // Convert stars and priceRange to appropriate types
            data.put("rating", defaultRating);
            data.put("priceRange", defaultPriceRange);
            data.put("cuisine", values[11]);

            // Add data to Firestore
            db.collection("restaurants").document(docId).set(data);

        }
        br.close();
    }

// Place in MainActivity.java to run
//FirestoreCsvImporter importer = new FirestoreCsvImporter(this);
//
//    // Import CSV data to Firestore
//        try {
//        importer.importCsvToFirestore();
//        System.out.println("Import to Firestore successful");
//    } catch (Exception e) {
//        System.out.println("Import to Firestore failed: " + e.getMessage());
//    }

}
