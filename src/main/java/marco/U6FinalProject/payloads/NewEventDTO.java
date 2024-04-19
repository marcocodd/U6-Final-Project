package marco.U6FinalProject.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewEventDTO(
        @NotEmpty(message = "Il titolo è obbligatorio")
        @Size(min = 2, max = 20, message = " Il titolo deve essere tra 2 e 20 caratteri")
        String title,
        @NotEmpty(message = "La descrizione è obbligatoria")
        @Size(min = 2, max = 20, message = " Il nome deve essere tra 2 e 20 caratteri")
        String description,

        LocalDate eventDate,

        String eventPlace,
        
        int placesAvailable) {
}
