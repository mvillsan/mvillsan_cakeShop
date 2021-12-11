package com.example.sandoval.model;

public class Product {
    private int id;
    private String name;
    private String description;
    private String flavor;
    private String theme;
    private int price;
    private int quantity;

    public Product() {
    }

    public Product(int id, String name, String description, String flavor, String theme, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.flavor = flavor;
        this.theme = theme;
        this.price = price;
        this.quantity = quantity;
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

    public String getDesc() {
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
