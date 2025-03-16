package com.example.ticketapp;

import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        GridView iconGridView = findViewById(R.id.iconGridView);

        IconGridAdapter adapter = getIconGridAdapter();
        iconGridView.setAdapter(adapter);
    }

    @NonNull
    private IconGridAdapter getIconGridAdapter() {
        IconItem[] items = new IconItem[]{
                new IconItem("Flight", R.drawable.ic_flight, SearchFlightActivity.class), // Replace with your actual drawable resource
                new IconItem("Bus", R.drawable.ic_bus, BusActivity.class),
                new IconItem("Train", R.drawable.ic_train, TrainActivity.class),
                new IconItem("Hotels", R.drawable.ic_hotels, HotelsActivity.class),
                new IconItem("My Trips", R.drawable.ic_my_trips, MyTripsActivity.class),
                new IconItem("Settings", R.drawable.ic_settings, SettingsActivity.class)
        };
        return new IconGridAdapter(this, items);
    }
}