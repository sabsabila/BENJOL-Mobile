package com.alifadepe.android_example.model;

public class Motorcycle {
    private int motorcycleId;
    private String brand;
    private String plateNumber;
    private int userId;

    public Motorcycle(int motorcycleId, String brand, String plateNumber, int userId) {
        this.motorcycleId = motorcycleId;
        this.brand = brand;
        this.plateNumber = plateNumber;
        this.userId = userId;
    }

    public int getMotorcycleId() {
        return motorcycleId;
    }

    public void setMotorcycleId(int motorcycleId) {
        this.motorcycleId = motorcycleId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
