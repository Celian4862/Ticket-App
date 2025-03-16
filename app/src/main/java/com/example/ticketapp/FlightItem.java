package com.example.ticketapp;

public class FlightItem {
    private final String airline_name;
    private final int arrival_time, departure_time;
    private final String destination, flight_number, origin;
    private final double price;
    private final Class<?> targetActivity;

    public FlightItem(String airline_name, int arrival_time, int departure_time, String destination, String flight_number, String origin, double price, Class<?> targetActivity) {
        this.airline_name = airline_name;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.destination = destination;
        this.flight_number = flight_number;
        this.origin = origin;
        this.price = price;
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

    public String getDestination() {
        return destination;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public String getOrigin() {
        return origin;
    }

    public double getPrice() {
        return price;
    }

    public Class<?> getTargetActivity() {
        return targetActivity;
    }
}
