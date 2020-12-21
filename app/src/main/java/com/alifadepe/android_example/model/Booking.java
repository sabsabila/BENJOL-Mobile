package com.alifadepe.android_example.model;

public class Booking {
    private int bookingId;
    private int bengkelId;
    private int userId;
    private int motorcycleId;
    private String repairmentDate;
    private String repairmentNote;
    private String isPickup;
    private String pickupLocation;
    private String dropOffLocation;
    private int selectedService;
    private String name;

    public Booking(int bengkelId, int motorcycleId, String repairmentDate, String repairmentNote, String isPickup, String pickupLocation, String dropOffLocation, int selectedService) {
        this.bengkelId = bengkelId;
        this.motorcycleId = motorcycleId;
        this.repairmentDate = repairmentDate;
        this.repairmentNote = repairmentNote;
        this.isPickup = isPickup;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.selectedService = selectedService;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getBengkelId() {
        return bengkelId;
    }

    public void setBengkelId(int bengkelId) {
        this.bengkelId = bengkelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMotorcycleId() {
        return motorcycleId;
    }

    public void setMotorcycleId(int motorcycleId) {
        this.motorcycleId = motorcycleId;
    }

    public String getRepairmentDate() {
        return repairmentDate;
    }

    public void setRepairmentDate(String repairmentDate) {
        this.repairmentDate = repairmentDate;
    }

    public String getRepairmentNote() {
        return repairmentNote;
    }

    public void setRepairmentNote(String repairmentNote) {
        this.repairmentNote = repairmentNote;
    }

    public String getIsPickup() {
        return isPickup;
    }

    public void setIsPickup(String isPickup) {
        this.isPickup = isPickup;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public int getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(int selectedService) {
        this.selectedService = selectedService;
    }
}
