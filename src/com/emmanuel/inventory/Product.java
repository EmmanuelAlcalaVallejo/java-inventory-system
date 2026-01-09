package com.emmanuel.inventory;

public class Product {
    //Attributes
    private String id;
    private String name;
    private String type;
    private double cost;

    //Constructor
    public Product(String id, String name, String type, double cost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString(){
        return id + " " + name + " " + type + " " + cost;
    }
}