package com.example.ticketapp;

public class IconItem {
    private final String text;
    private final int imageResId;
    private final Class<?> targetActivity;

    public IconItem(String text, int imageResId, Class<?> targetActivity) {
        this.text = text;
        this.imageResId = imageResId;
        this.targetActivity = targetActivity;
    }

    public String getText() {
        return text;
    }

    public int getImageResId() {
        return imageResId;
    }

    public Class<?> getTargetActivity() {
        return targetActivity; // Getter for the target activity
    }
}
