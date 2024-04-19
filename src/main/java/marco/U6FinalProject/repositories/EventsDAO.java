package marco.U6FinalProject.repositories;

import marco.U6FinalProject.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsDAO extends JpaRepository<Event, Long> {
}
