package com.example.sun.umstouristsapp;

/**
 * Created by sun on 24/01/2017.
 */

public class Packages_Model {

    // Getter and Setter model for recycler view items
    private String title, location, year;
    private int image;

    public Packages_Model(String title, String location, String year, int image) {
        this.year = year;
        this.title = title;
        this.location = location;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
