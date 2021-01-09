package com.benjolteam.benjol.model;

public class Sparepart {
    private int sparepart_id;
    private int bengkel_id;
    private String name;
    private int price;
    private int stock;
    private String picture;
    private String created_at;
    private String updated_at;
    private String bengkel;
    private String address;

    public Sparepart(int sparepart_id, int bengkel_id, String name, int price, int stock, String picture, String created_at, String updated_at, String bengkel, String address) {
        this.sparepart_id = sparepart_id;
        this.bengkel_id = bengkel_id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.picture = picture;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.bengkel = bengkel;
        this.address = address;
    }

    public int getSparepart_id() {
        return sparepart_id;
    }

    public void setSparepart_id(int sparepart_id) {
        this.sparepart_id = sparepart_id;
    }

    public int getBengkel_id() {
        return bengkel_id;
    }

    public void setBengkel_id(int bengkel_id) {
        this.bengkel_id = bengkel_id;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getBengkel() {
        return bengkel;
    }

    public void setBengkel(String bengkel) {
        this.bengkel = bengkel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
