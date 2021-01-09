package com.benjolteam.benjol.model;

public class Service {
    private int service_id;
    private int bengkel_id;
    private String service_name;

    public Service(int service_id, int bengkel_id, String service_name) {
        this.service_id = service_id;
        this.bengkel_id = bengkel_id;
        this.service_name = service_name;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getBengkel_id() {
        return bengkel_id;
    }

    public void setBengkel_id(int bengkel_id) {
        this.bengkel_id = bengkel_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    @Override
    public String toString() {
        return service_name;
    }
}
