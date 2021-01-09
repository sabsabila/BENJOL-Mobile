package com.benjolteam.benjol.model;

public class Bengkel {
    private int bengkel_id;
    private int account_id;
    private String name;
    private String address;
    private String created_at;
    private String updated_at;
    private String phone_number;
    private String profile_picture;

    public Bengkel(int bengkel_id, int account_id, String name, String address, String created_at, String updated_at, String phone_number, String profile_picture) {
        this.bengkel_id = bengkel_id;
        this.account_id = account_id;
        this.name = name;
        this.address = address;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.phone_number = phone_number;
        this.profile_picture = profile_picture;
    }

    public int getBengkel_id() {
        return bengkel_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getProfile_picture() {
        return profile_picture;
    }
}
