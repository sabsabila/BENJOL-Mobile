package com.benjolteam.benjol.model;

public class PickupData {
    private int booking_id;
    private String repairment_date;
    private String name;
    private String pickup_location;
    private String dropoff_location;

    public PickupData(int booking_id, String repairment_date, String name, String pickup_location, String dropoff_location) {
        this.booking_id = booking_id;
        this.repairment_date = repairment_date;
        this.name = name;
        this.pickup_location = pickup_location;
        this.dropoff_location = dropoff_location;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getRepairment_date() {
        return repairment_date;
    }

    public void setRepairment_date(String repairment_date) {
        this.repairment_date = repairment_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
