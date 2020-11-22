package com.alifadepe.android_example.model;

public class Sparepart {
    private int sparepartId;
    private int bengkelId;
    private String name;
    private int price;
    private int stock;
    private String picture;

    public Sparepart(int sparepartId, int bengkelId, String name, int price, int stock, String picture){
        this.sparepartId = sparepartId;
        this.bengkelId = bengkelId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.picture = picture;
    }

    public int getBengkelId() {
        return bengkelId;
    }

    public void setBengkelId(int bengkelId) {
        this.bengkelId = bengkelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getSparepartId() {
        return sparepartId;
    }

    public void setSparepartId(int sparepartId) {
        this.sparepartId = sparepartId;
    }


}
