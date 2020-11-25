package com.alifadepe.android_example.model;

public class ProgressService {
    private String start_time;
    private String end_time;
    private String plate_number;

    public ProgressService(String start_time, String end_time, String plate_number) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.plate_number = plate_number;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getPlate_number() {
        return plate_number;
    }
}
