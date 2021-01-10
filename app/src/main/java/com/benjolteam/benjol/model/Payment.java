package com.benjolteam.benjol.model;

public class Payment {
    private int payment_id;
    private int booking_id;
    private String status;
    private String receipt;
    private String created_at;
    private String updated_at;
    private String repairment_date;
    private int service_cost;
    private String repairment_note;
    private String bengkel_note;

    public Payment(int payment_id, int booking_id, String status, String receipt, String created_at, String updated_at, String repairment_date, int service_cost, String repairment_note, String bengkel_note) {
        this.payment_id = payment_id;
        this.booking_id = booking_id;
        this.status = status;
        this.receipt = receipt;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.repairment_date = repairment_date;
        this.service_cost = service_cost;
        this.repairment_note = repairment_note;
        this.bengkel_note = bengkel_note;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
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

    public String getRepairment_date() {
        return repairment_date;
    }

    public void setRepairment_date(String repairment_date) {
        this.repairment_date = repairment_date;
    }

    public int getService_cost() {
        return service_cost;
    }

    public void setService_cost(int service_cost) {
        this.service_cost = service_cost;
    }

    public String getRepairment_note() {
        return repairment_note;
    }

    public void setRepairment_note(String repairment_note) {
        this.repairment_note = repairment_note;
    }

    public String getBengkel_note() {
        return bengkel_note;
    }

    public void setBengkel_note(String bengkel_note) {
        this.bengkel_note = bengkel_note;
    }
}
