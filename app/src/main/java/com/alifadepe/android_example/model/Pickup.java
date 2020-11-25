package com.alifadepe.android_example.model;

public class Pickup {
    private int pickup_id;
    private String pickup_location;
    private String dropoff_location;
    private String status;
    private String created_at;
    private String updated_at;

    public Pickup(int pickup_id, String pickup_location, String dropoff_location, String status, String created_at, String updated_at) {
        this.pickup_id = pickup_id;
        this.pickup_location = pickup_location;
        this.dropoff_location = dropoff_location;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getPickup_id() {
        return pickup_id;
    }

    public void setPickup_id(int pickup_id) {
        this.pickup_id = pickup_id;
    }

    public String getPickup_location() {
        return pickup_location;
    }

    public void setPickup_location(String pickup_location) {
        this.pickup_location = pickup_location;
    }

    public String getDropoff_location() {
        return dropoff_location;
    }

    public void setDropoff_location(String dropoff_location) {
        this.dropoff_location = dropoff_location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
