package com.example.safeauthapp;

//Thi class will set the apps title and other descriptions on the app
public class Item {
    private String id;
    private String title;
    private String desc;


    //This is the default constructor
    public Item() {}

    public Item(String id, String title, String desc) {
        this.id = id; this.title = title; this.desc = desc;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public String getDesc()  { return desc; }

    public void setTitle(String title) { this.title = title; }
    public void setDesc(String desc)   { this.desc = desc; }
}
