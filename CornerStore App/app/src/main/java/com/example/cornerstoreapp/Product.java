package com.example.cornerstoreapp;

public class Product implements java.io.Serializable{
    //This are the variables that will pass the product information across the view and the database
    private int id;
    private String name;
    private String description;
    private String seller;
    private double price;
    private int pictureID;
    private boolean selectedItem;

    //empty constructor
    public Product() {
    }

    //this is the constructor that helps get and set the data
    public Product(int id,String name, String description, String seller, double price, int pictureID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.seller = seller;
        this.price =  price;
        this.pictureID = pictureID;
    }

    //This returns the selected items
    public boolean Selected() { return selectedItem; }
    //This method set the selected item
    public void setSelected(boolean selectedItem) { this.selectedItem = selectedItem; }
    //This method returns the item ID
    public int getId() {
        return id;
    }
    //This methods sets the item ID
    public void setId(int id) {
        this.id = id;
    }
    //This method returns the item name
    public String getName() {
        return name;
    }
    //This method set the item name
    public void setName(String name) {
        this.name = name;
    }
    //This method gets the description of the item
    public String getDescription() {
        return description;
    }
    //This method sets the item description
    public void setDescription(String description) {
        this.description = description;
    }
    //This method gets the seller name
    public String getSeller() {
        return seller;
    }
    //This sets the seller name
    public void setSeller(String seller) {
        this.seller = seller;
    }
    //This method gets the item price
    public double getPrice() {
        return price;
    }
    //This method sets the item price
    public void setPrice(double price) {
        this.price = price;
    }
    //This gets the ID of the item image
    public int getPictureID() {
        return pictureID;
    }
    //This method set the ID of the picture
    public void setPictureID(int pictureUrl) {
        this.pictureID = pictureUrl;
    }

}
