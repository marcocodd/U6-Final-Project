package marco.U6FinalProject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "events")
@NoArgsConstructor
@Getter
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    @Setter
    private String title;
    @Setter
    private String description;
    @Setter
    private LocalDate eventDate;
    @Setter
    private String eventPlace;
    @Setter
    private int placesAvailable;
    @ManyToMany
    @JoinTable(
            name = "user_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Setter
    private List<User> users;

    public Event(String title, String description, LocalDate eventDate, String eventPlace, int placesAvailable) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.eventPlace = eventPlace;
        this.placesAvailable = placesAvailable;
    }

}
