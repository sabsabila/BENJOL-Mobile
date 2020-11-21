package com.alifadepe.android_example.model;

public class Motorcycle {
    private int motorcycle_id;
    private String brand;
    private String plateNumber;
    private int user_id;

    public Motorcycle(int motorcycle_id, String brand, String plateNumber, int user_id) {
        this.motorcycle_id = motorcycle_id;
        this.brand = brand;
        this.plateNumber = plateNumber;
        this.user_id = user_id;
    }

    public Motorcycle(String brand, String plateNumber) {
        this.brand = brand;
        this.plateNumber = plateNumber;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
