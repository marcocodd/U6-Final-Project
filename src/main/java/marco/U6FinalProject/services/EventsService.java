package marco.U6FinalProject.services;

import marco.U6FinalProject.entities.Event;
import marco.U6FinalProject.exceptions.BadRequestException;
import marco.U6FinalProject.exceptions.NotFoundException;
import marco.U6FinalProject.payloads.NewEventDTO;
import marco.U6FinalProject.repositories.EventsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventsService {
    @Autowired
    private EventsDAO eventsDAO;

    public Event save(NewEventDTO body) throws BadRequestException {

        Event newEvent = new Event(body.title(), body.description(), body.eventDate(), body.eventPlace(), body.placesAvailable());
        return eventsDAO.save(newEvent);
    }

    public Event findById(Long eventId) {
        return this.eventsDAO.findById(eventId).orElseThrow(() -> new NotFoundException(eventId));
    }

    public Event findByIdAndUpdate(Long eventId, Event modifiedEvent) {
        Event found = this.findById(eventId);
        found.setTitle(modifiedEvent.getTitle());
        found.setDescription(modifiedEvent.getDescription());
        found.setEventDate(modifiedEvent.getEventDate());
        found.setEventPlace(modifiedEvent.getEventPlace());
        found.setPlacesAvailable(modifiedEvent.getPlacesAvailable());
        return this.eventsDAO.save(found);
    }

    public void findByIdAndDelete(Long eventId) {
        Event found = this.findById(eventId);
        this.eventsDAO.delete(found);
    }
}
