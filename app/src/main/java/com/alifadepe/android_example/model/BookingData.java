package com.alifadepe.android_example.model;

public class BookingData {
    private int booking_id;
    private String repairment_date;
    private String repairment_note;
    private String name;

    public BookingData(int booking_id, String repairment_date, String repairment_note, String name) {
        this.booking_id = booking_id;
        this.repairment_date = repairment_date;
        this.repairment_note = repairment_note;
        this.name = name;
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

    public String getRepairment_note() {
        return repairment_note;
    }

    public void setRepairment_note(String repairment_note) {
        this.repairment_note = repairment_note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
