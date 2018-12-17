package com.musala.view;

import com.musala.model.Event;
import com.musala.repository.EventRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/events")
public class EventResource {

    private EventRepository eventRepository = new EventRepository();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> retrieveAllEvents(@QueryParam("offset") @DefaultValue("0") int offset,@QueryParam("limit") @DefaultValue("3") int limit ) {
        eventRepository.generateSearchPrintAllEvents(offset,limit);
        return eventRepository.getEventList();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEvent(Event event) {
        eventRepository.addEvent(event);
        return Response.ok().entity("Added Event").build();
    }

    @GET
    @Path("/{eventId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Event retrieveEventById(@PathParam("eventId") int eventId) {
        System.out.println(eventId);
        return eventRepository.generateSearchSpecificEvent(eventId);
    }

    @DELETE
    @Path("/{eventId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deleteEvent(@PathParam("eventId") String eventId) {
        eventRepository.removeEvent(Integer.parseInt(eventId));
        return Response.ok().entity("Deleted Event").build();
    }

    @PUT
    @Path("/{eventId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editEvent(@PathParam("eventId") String eventId, Event event) {
        eventRepository.editEvent(event,Integer.parseInt(eventId));
        return Response.ok().entity("Edited Event").build();
    }

    @GET
    @Path("/{year}/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public List<Event> retrieveEventsForMonth(@PathParam("year") String year,@PathParam("month") String month) {
        String date = year + "-" + month;
        eventRepository.generateSearchPrintMonth(date);
        return eventRepository.getEventList();
    }

    @GET
    @Path("/{year}/{month}/{day}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public List<Event> retrieveEventsForDay(@PathParam("year") String year,@PathParam("month") String month,@PathParam("day") String day) {
        String date = year + "-" + month + "-" + day;
        eventRepository.generateSearchPrintDay(date);
        return eventRepository.getEventList();
    }
}
