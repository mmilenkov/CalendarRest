package com.musala.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event",schema = "event_list")
public class Event {

    private String name;

    @Transient
    private String location;

    @ManyToOne (optional = false)
    @JoinColumn(name = "location_id" , insertable = false,updatable = false)
    private Location locationO;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id",updatable = false, nullable = false)
    private int eventID;

    @Column(name = "location_id")
    private int locationId;

    public Event(){}

    public Event(int d,int m, int y, String name, int locationId, String startTime, String endTime) {
        this.name = name;
        this.locationId = locationId;
        startDate = new Date(y-1900,m,d,Integer.valueOf(startTime.substring(0,2)),Integer.valueOf(startTime.substring(3)));
        endDate = new Date(y-1900,m,d,Integer.valueOf(endTime.substring(0,2)),Integer.valueOf(endTime.substring(3)));
    }

    public Event (int id,String name, Date startDate, Date endDate, String location) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.eventID = id;
    }

    public int getEventID() {
        return eventID;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getLocationId() {
        return  locationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Location getLocationO() {
        return locationO;
    }

    public void setLocationO(Location locationO) {
        this.locationO = locationO;
    }
}

