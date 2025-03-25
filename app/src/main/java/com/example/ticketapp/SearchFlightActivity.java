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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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

        FlightItem[] items_all = null;
        try {
            JSONObject object = new JSONObject(loadJSONFromAssets());
            JSONArray jsonArray = object.getJSONArray("flights");
            items_all = new FlightItem[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject flights = jsonArray.getJSONObject(i);
                items_all[i] = new FlightItem(
                        flights.getString("airline name"),
                        flights.getInt("arrival time"),
                        flights.getInt("departure time"),
                        flights.getString("destination"),
                        flights.getString("flight number"),
                        flights.getString("origin"),
                        flights.getDouble("price"),
                        FlightDetailsActivity.class
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView list = findViewById(R.id.list_flights);
        Button search_flight = findViewById(R.id.btn_search);

        book_flight.setOnClickListener(v -> {});
        back.setOnClickListener(v -> {
            Intent intent = new Intent(SearchFlightActivity.this, MainActivity.class);
            startActivity(intent);
        });
        FlightItem[] finalItems_all = items_all;
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
                FlightListAdapter adapter = getFlightListAdapter(finalItems_all);
                list.setAdapter(adapter);
                return;
            }

            int i, item_count = 0;
            int[] idx = new int[finalItems_all.length];
            for (i = 0; i < finalItems_all.length; i++) {
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
                if (Objects.equals(finalItems_all[i].getDeparture_time(), str_dpt_count) &&
                        Objects.equals(finalItems_all[i].getOrigin(), str_from) &&
                        Objects.equals(finalItems_all[i].getArrival_time(), str_rtn_count) &&
                        Objects.equals(finalItems_all[i].getDestination(), str_to)) {
                    idx[item_count++] = i;
                }
            }

            final FlightItem[] items = new FlightItem[item_count];

            while (item_count > 0) {
                item_count--;
                items[item_count] = finalItems_all[idx[item_count]];
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

    private String loadJSONFromAssets() {
        String json = null;

        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}