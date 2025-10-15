package com.example.cornerstoreapp;

public class Product {
    //This are the variables that will pass the product information across the view and the database
    private int id;
    private String name;
    private String description;
    private String seller;
    private double price;
    private int pictureID;

    public Product() {
    }

    //this si the constructor that helps get and set the data
    public Product(int id,String name, String description, String seller, double price, int pictureID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.price =  price;
        this.pictureID = pictureID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(int pictureUrl) {
        this.pictureID = pictureUrl;
    }

}
