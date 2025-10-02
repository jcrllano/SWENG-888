package com.example.listviewapp;

import java.io.Serializable;

public class ListItem implements Serializable {
    public String title;
    public String subtitle;
    public String details;

    public ListItem (String title, String subtitle, String details) {
        this.title = title;
        this.subtitle = subtitle;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public  String getDetails() {
        return  details;
    }

}
