package marco.U6FinalProject.services;

import marco.U6FinalProject.entities.Event;
import marco.U6FinalProject.exceptions.BadRequestException;
import marco.U6FinalProject.exceptions.NotFoundException;
import marco.U6FinalProject.payloads.NewEventDTO;
import marco.U6FinalProject.repositories.EventsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Event> getEvents(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.eventsDAO.findAll(pageable);
    }
}
