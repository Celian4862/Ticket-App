package com.example.ticketapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

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
        FlightItem[] items_all = new FlightItem[]{
                        new FlightItem("Lorem Ipsum Airlines", 1235, 945, "NY", "432GH4", "MAD", 190, FlightDetailsActivity.class),
                        new FlightItem("Lorem Ipsum Airlines", 2330, 2125, "NY", "563GH4", "MAD", 115, FlightDetailsActivity.class),
                        new FlightItem("Cathay Pacific", 1130, 800, "CEB", "CX925", "HKG", 300, FlightListAdapter.class),
                        new FlightItem("Cathay Pacific", 1530, 1200, "HKG", "CX922", "CEB", 300, FlightListAdapter.class),
                        new FlightItem("Philippines Air Asia", 340, 220, "MNL", "123456", "CEB", 58.47, FlightListAdapter.class),
                        new FlightItem("Philippines Air Asia", 1910, 1745, "CEB", "654321", "MNL", 58.47, FlightListAdapter.class)
                };
        ListView list = findViewById(R.id.list_flights);
        Button search_flight = findViewById(R.id.btn_search);

        book_flight.setOnClickListener(v -> {});
        back.setOnClickListener(v -> {
            Intent intent = new Intent(SearchFlightActivity.this, MainActivity.class);
            startActivity(intent);
        });
        search_flight.setOnClickListener(v -> {
            EditText edit_departure = findViewById(R.id.edit_txt_departure),
                    edit_from = findViewById(R.id.edit_txt_from),
                    edit_return = findViewById(R.id.edit_txt_return),
                    edit_to = findViewById(R.id.edit_txt_to);
            String str_dpt = edit_departure.getText().toString(),
                    str_from = edit_from.getText().toString(),
                    str_rtn = edit_return.getText().toString(),
                    str_to = edit_to.getText().toString();

            if (str_dpt.isEmpty() &&
                    str_from.isEmpty() &&
                    str_to.isEmpty() &&
                    str_rtn.isEmpty()) {
                FlightListAdapter adapter = getFlightListAdapter(items_all);
                list.setAdapter(adapter);
                return;
            }

            int i, idx_count = 0, item_count = 0;
            for (i = 0; i < items_all.length; i++) {
                int j, str_dpt_count, str_rtn_count;
                for (j = str_dpt_count = 0; j < str_dpt.length(); j++) {
                    if (str_dpt.charAt(j) != ':') {
                        str_dpt_count *= 10;
                        str_dpt_count += str_dpt.charAt(j) - '0';
                    }
                }

                for (j = str_rtn_count = 0; j < str_rtn.length(); j++) {
                    if (str_rtn.charAt(j) != ':') {
                        str_rtn_count *= 10;
                        str_rtn_count += str_rtn.charAt(j) - '0';
                    }
                }
                if (Objects.equals(items_all[i].getDeparture_time(), str_dpt_count) &&
                        Objects.equals(items_all[i].getOrigin(), str_from) &&
                        Objects.equals(items_all[i].getArrival_time(), str_rtn_count) &&
                        Objects.equals(items_all[i].getDestination(), str_to)) {
                    item_count++;
                    idx_count *= 10;
                    idx_count += i;
                }
            }

            final FlightItem[] items = new FlightItem[item_count];

            i = 0;
            while (idx_count > 0) {
                items[i] = items_all[idx_count % 10];
                idx_count /= 10;
            }

            FlightListAdapter adapter = getFlightListAdapter(items);
            list.setAdapter(adapter);
        });
        FlightListAdapter adapter = getFlightListAdapter(items_all);
        list.setAdapter(adapter);
    }

    @NonNull
    private FlightListAdapter getFlightListAdapter(FlightItem[] items) {
        return new FlightListAdapter(this, items);
    }
}