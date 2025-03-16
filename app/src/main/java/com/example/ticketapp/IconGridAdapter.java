package com.example.ticketapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IconGridAdapter extends BaseAdapter {
    private final Context context;
    private final IconItem[] items;

    public IconGridAdapter(Context context, IconItem[] items) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_layout, parent, false);
        }

        ImageView iconImageView = convertView.findViewById(R.id.iconImageView);
        TextView iconTextView = convertView.findViewById(R.id.iconTextView);

        IconItem item = items[position];
        iconImageView.setImageResource(item.getImageResId());
        iconTextView.setText(item.getText());

        // Set an OnClickListener to launch the target activity
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, item.getTargetActivity());
            context.startActivity(intent);
        });

        return convertView;
    }
}
