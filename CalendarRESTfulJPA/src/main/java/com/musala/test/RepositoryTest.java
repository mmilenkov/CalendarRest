package com.musala.test;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import com.musala.model.Event;
import com.musala.repository.EventRepository;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RepositoryTest {
    private List<Event> testList;
    private int month = 10;
    private int day = 10;
    private int year = 2229;
    private Event validEvent = new Event(day,month-1,year, "TestEvent", 4, "09:00", "11:50");
    private Event editedEvent = new Event (day,month-1,year,"EditedTestEvent", 2,"09:00", "11:50");
    private EventRepository EventHolderJPA = new EventRepository();

    @Before
    public void retrieveEventList() {
        EventHolderJPA.generateSearchPrintDay("2229-10-10");
        testList = EventHolderJPA.getEventList();
    }

    @Test
    public void canAddEvent() {
        EventHolderJPA.addEvent(validEvent);
        testList = EventHolderJPA.getEventList();
        for (Event event: testList) {
            assertEquals("TestEvent",event.getName());
            assertEquals(4,event.getLocationId());
        }
    }

    @Test
    public void canEditEvent() {
        boolean edited = false;
        for (Event event: testList) {
            EventHolderJPA.editEvent(editedEvent,event.getEventID());
            edited = true;
        }
        retrieveEventList();
        for (Event event : testList) {
            assertEquals("EditedTestEvent", event.getName());
            assertEquals(2, event.getLocationId());
        }
        assertTrue(edited);
    }

    @Test
    public void canRemoveEvent() {
        boolean removed = false;
        for (Event event: testList) {
            EventHolderJPA.removeEvent(event.getEventID());
            removed = true;
        }
        retrieveEventList();
        assertTrue((testList.isEmpty() && removed));
    }
}
