package com.alifadepe.android_example.model;

public class Motorcycle {
    private int motorcycle_id;
    private String brand;
    private String plate_number;
    private int user_id;

    public Motorcycle(int motorcycle_id, String brand, String plate_number, int user_id) {
        this.motorcycle_id = motorcycle_id;
        this.brand = brand;
        this.plate_number = plate_number;
        this.user_id = user_id;
    }

    public Motorcycle(String brand, String plate_number) {
        this.brand = brand;
        this.plate_number = plate_number;
    }

    public int getMotorcycle_id() {
        return motorcycle_id;
    }

    public void setMotorcycle_id(int motorcycle_id) {
        this.motorcycle_id = motorcycle_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
