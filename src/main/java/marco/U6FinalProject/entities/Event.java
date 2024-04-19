package marco.U6FinalProject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    public Event(String title, String description, LocalDate eventDate, String eventPlace, int placesAvailable) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.eventPlace = eventPlace;
        this.placesAvailable = placesAvailable;
    }
    
}
