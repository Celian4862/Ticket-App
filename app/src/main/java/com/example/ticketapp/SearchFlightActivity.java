package com.example.ticketapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SearchFlightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_flight);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button back = findViewById(R.id.btn_back);
        Button book_flight = findViewById(R.id.btn_book_flight);
        ListView list = findViewById(R.id.list_flights);

        book_flight.setOnClickListener(v -> {});
        back.setOnClickListener(v -> {
            Intent intent = new Intent(SearchFlightActivity.this, MainActivity.class);
            startActivity(intent);
        });
        FlightListAdapter adapter = getFlightListAdapter();
        list.setAdapter(adapter);
    }

    @NonNull
    private FlightListAdapter getFlightListAdapter() {
        return new FlightListAdapter(this, new FlightItem[]{
                new FlightItem("Lorem Ipsum Airlines", 945, 1235, "NY", "432GH4", "MAD", 190, FlightDetailsActivity.class),
                new FlightItem("Lorem Ipsum Airlines", 2125, 2330, "NY", "563GH4", "MAD", 115, FlightDetailsActivity.class)
        });
    }
}