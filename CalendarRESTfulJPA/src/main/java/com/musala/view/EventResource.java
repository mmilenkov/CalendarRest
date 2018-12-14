package com.musala.view;

import com.musala.model.Event;
import com.musala.repository.EventRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/calendar")
public class EventResource {

    private EventRepository eventRepository = new EventRepository();

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEvent(Event event) {
        eventRepository.addEvent(event);
        return Response.ok().build();
    }

    @DELETE
    @Path("delete/{eventId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEvent(@PathParam("eventId") String eventId) {
        eventRepository.removeEvent(Integer.parseInt(eventId));
        return Response.ok().build();
    }

    @PUT
    @Path("edit/{eventId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editEvent(@PathParam("eventId") String eventId, Event event) {
        eventRepository.editEvent(event,Integer.parseInt(eventId));
        return Response.ok().build();
    }

    @GET
    @Path("print")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> retrieveAllEvents() {
        eventRepository.generateSearchPrintAllEvents();
        return eventRepository.getEventList();
    }

    @POST
    @Path("print-month/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public List<Event> retrieveEventsForMonth(@PathParam("date") String date) {//yyyy-mm
        System.out.println(date);
        eventRepository.generateSearchPrintMonth(date);
        return eventRepository.getEventList();
    }

    @POST
    @Path("print-day/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public List<Event> retrieveEventsForDay(@PathParam("date") String date) {//yyyy-mm-dd
        System.out.println(date);
        eventRepository.generateSearchPrintDay(date);
        return eventRepository.getEventList();
    }
}
