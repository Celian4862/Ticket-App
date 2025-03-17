package com.example.ticketapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Locale;

public class FlightListAdapter extends BaseAdapter {
    private final Context context;
    private final FlightItem[] items;
    public FlightListAdapter(Context context, FlightItem[] items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.flight_list_layout, parent, false);
        }

        TextView airline = convertView.findViewById(R.id.label_airline),
                destination = convertView.findViewById(R.id.label_destination),
                duration = convertView.findViewById(R.id.label_duration),
                end_time = convertView.findViewById(R.id.label_end_time);
        FlightItem item = items[position];
        TextView origin = convertView.findViewById(R.id.label_origin),
                price = convertView.findViewById(R.id.txt_price),
                start_time = convertView.findViewById(R.id.label_start_time);

        int int_end_time = item.getArrival_time();
        int int_start_time = item.getDeparture_time();

        airline.setText(item.getAirline_name());
        destination.setText(item.getDestination());
        duration.setText(calculateDuration(int_start_time, int_end_time));
        end_time.setText(String.format(new Locale("en"), "%2d:%02d", int_end_time / 100, int_end_time % 100));
        origin.setText(item.getOrigin());
        price.setText(String.format(new Locale("en"), "$%.2f", item.getPrice()));
        start_time.setText(String.format(new Locale("en"), "%2d:%02d", int_start_time / 100, int_start_time % 100));

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, item.getTargetActivity());
            context.startActivity(intent);
        });

        return convertView;
    }

    private String calculateDuration(int start_time, int end_time) {
        String txt_duration;
        int start_hrs = start_time / 100, start_mins = start_time % 100;
        int end_hrs = end_time / 100, end_mins = end_time % 100;
        int duration_hrs, duration_mins;

        if (end_mins >= start_mins) {
            duration_mins = end_mins - start_mins;
        } else {
            duration_mins = end_mins + 60 - start_mins;
            end_hrs -= 1;
        }

        if (end_hrs >= start_hrs) {
            duration_hrs = end_hrs - start_hrs;
        } else {
            duration_hrs = end_hrs + 24 - start_hrs;
        }
        txt_duration = String.format(new Locale("en"), "%d HRS %d MINS", duration_hrs, duration_mins);
        return txt_duration;
    }
}
