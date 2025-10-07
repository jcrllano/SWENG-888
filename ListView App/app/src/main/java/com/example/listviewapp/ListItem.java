package com.example.listviewapp;

import java.io.Serializable;

public class ListItem implements Serializable {
    public String title;
    public String subtitle;
    public String details;

    //This is the constructor that will pass the list items
    public ListItem (String title, String subtitle, String details) {
        this.title = title;
        this.subtitle = subtitle;
        this.details = details;
    }

    //This string class calls the title
    public String getTitle() {
        return title;
    }

    //This string class calls the subtitle
    public String getSubtitle() {
        return subtitle;
    }

    //This string class calls the details
    public  String getDetails() {
        return  details;
    }

}
