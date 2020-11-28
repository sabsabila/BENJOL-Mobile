package com.alifadepe.android_example.model;

public class BookingData {
    private String repairment_date;
    private String repairment_note;
    private String name;

    public BookingData(String repairment_date, String repairment_note, String name) {
        this.repairment_date = repairment_date;
        this.repairment_note = repairment_note;
        this.name = name;
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
