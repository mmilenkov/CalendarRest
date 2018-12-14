package com.musala.repository;

import com.musala.model.Event;
import com.musala.model.Location;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventRepository {

    private List<Event> eventList = new LinkedList<Event>();

    public List<Event> getEventList() {
        return eventList;
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        return emf.createEntityManager();
    }

    public void addEvent(Event event) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(event);
        tx.commit();
        entityManager.close();
    }

    public void removeEvent(int eventId) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        Event toDelete = entityManager.find(Event.class,eventId);
        if(toDelete != null) {
            tx.begin();
            entityManager.remove(toDelete);
            tx.commit();
        }
        entityManager.close();

    }

    public void editEvent(Event event, int eventId) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        Event toEdit = entityManager.find(Event.class,eventId);
        Location newLocation = entityManager.find(Location.class,event.getLocationId());
        tx.begin();
        toEdit.setName(event.getName());
        toEdit.setStartDate(event.getStartDate());
        toEdit.setEndDate(event.getEndDate());
        toEdit.setLocationId(event.getLocationId());
        toEdit.setLocationO(newLocation);
        tx.commit();
        entityManager.close();
    }

    public void generateSearchPrintAllEvents() {
        EntityManager entityManager = getEntityManager();
        String searchTerm = "SELECT e FROM Event e";
        TypedQuery<Event> query = entityManager.createQuery(searchTerm,Event.class);
        eventList = query.getResultList();
        entityManager.close();
    }

    public void generateSearchPrintMonth(String date) {
        EntityManager entityManager = getEntityManager();
        String[] slice = date.split("-");
        int year = Integer.valueOf(slice[0]);
        int month = Integer.valueOf(slice[1]);
        Date initialDate = new Date(year-1900,month-1,1,0,0,0);
        Date secondaryDate = generateSecondaryDate(year, month);

        TypedQuery<Event> query = entityManager.createQuery("Select e FROM Event e Where e.startDate BETWEEN ?1 AND ?2",Event.class);
        query.setParameter(1,initialDate);
        query.setParameter(2,secondaryDate);
        eventList = query.getResultList();
        entityManager.close();
    }

    public void generateSearchPrintDay(String date) {
        EntityManager entityManager = getEntityManager();
        String[] slice = date.split("-");
        int year = Integer.valueOf(slice[0]);
        int month = Integer.valueOf(slice[1]);
        int day = Integer.valueOf(slice[2]);
        Date initialDate = new Date(year-1900,month-1,day,0,0,0);
        Date secondaryDate = new Date(year-1900,month-1,day,23,59,59);


        TypedQuery<Event> query = entityManager.createQuery("Select e FROM Event e Where e.startDate BETWEEN ?1 AND ?2",Event.class);
        query.setParameter(1,initialDate);
        query.setParameter(2,secondaryDate);
        eventList = query.getResultList();
        entityManager.close();
    }

    private Date generateSecondaryDate(int year, int month) {
        if(month == 12) {
            year += 1;
        }
        if(month == 12) {
            month = 1;
        }
        else {
            month += 1;
        }
        return new Date(year-1900,month-1,1,23,59,0);
    }

}
