package com.example.ticketapp;

public class FlightItem {
    private final String airline_name;
    private final int arrival_time, departure_time;
    private final String flight_number;
    private final double price;
    private final String seat_tier;
    private final Class<?> targetActivity;

    public FlightItem(String airline_name, int arrival_time, int departure_time, String flight_number, double price, String seat_tier, Class<?> targetActivity) {
        this.airline_name = airline_name;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.flight_number = flight_number;
        this.price = price;
        this.seat_tier = seat_tier;
        this.targetActivity = targetActivity;
    }

    public String getAirline_name() {
        return airline_name;
    }

    public int getArrival_time() {
        return arrival_time;
    }

    public int getDeparture_time() {
        return departure_time;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public double getPrice() {
        return price;
    }

    public String getSeat_tier() {
        return seat_tier;
    }

    public Class<?> getTargetActivity() {
        return targetActivity;
    }
}
