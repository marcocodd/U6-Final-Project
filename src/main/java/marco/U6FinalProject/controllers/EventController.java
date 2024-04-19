package marco.U6FinalProject.controllers;

import marco.U6FinalProject.entities.Event;
import marco.U6FinalProject.exceptions.BadRequestException;
import marco.U6FinalProject.payloads.NewEventDTO;
import marco.U6FinalProject.payloads.NewEventResponseDTO;
import marco.U6FinalProject.services.EventsService;
import marco.U6FinalProject.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventsService eventsService;
    @Autowired
    private UsersService usersService;


    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ORGANIZER')")
    public Page<Event> getAllEvents(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        return this.eventsService.getEvents(page, size, sortBy);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZER')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewEventResponseDTO save(@RequestBody @Validated NewEventDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewEventResponseDTO(this.eventsService.save(body).getId());
    }

    // per prenotazione evento


    @PutMapping("/{eventId}")
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public Event findByIdAndUpdate(@PathVariable Long eventId, @RequestBody Event body) {
        return this.eventsService.findByIdAndUpdate(eventId, body);
    }

    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasAuthority('ORGANIZER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long eventId) {
        this.eventsService.findByIdAndDelete(eventId);
    }

}



