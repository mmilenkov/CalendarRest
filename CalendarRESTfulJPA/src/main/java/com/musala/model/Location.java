package com.musala.model;

import javax.persistence.*;

@Entity
@Table(name = "location_list",schema = "event_list")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id",updatable = false, nullable = false)
    private int locationId;

    private String location;

    public Location () {}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public  int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}