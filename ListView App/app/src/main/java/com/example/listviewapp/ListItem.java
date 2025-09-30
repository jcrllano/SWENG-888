package com.example.listviewapp;

import java.io.Serializable;

public class ListItem implements Serializable {
    public String title;
    public String subtitle;

    public ListItem (String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
