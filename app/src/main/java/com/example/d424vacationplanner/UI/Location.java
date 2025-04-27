package com.example.d424vacationplanner.UI;

public class Location {
    private int id;
    private String name;
    private String date;

    public Location(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getLocationDetails() {
       return name + " on " + date;
    }
}
