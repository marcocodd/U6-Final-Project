package marco.U6FinalProject.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(@NotEmpty(message = "Il nome è obbligatorio")
                         @Size(min = 2, max = 20, message = " Il nome deve essere tra 2 e 20 caratteri") String name,

                         @NotEmpty(message = "Il cognome è obbligatorio")
                         @Size(min = 2, max = 20, message = "Il cognome deve essere tra 2 e 20 caratteri") String surname,

                         @NotEmpty(message = "l'email è obbligatoria")
                         @Email(message = "email inserita non valida")
                         String email,
                         @NotEmpty(message = "La password è obbligatoria")
                         @Size(min = 8, message = "La password deve essere di almeno 8 caratteri")
                         String password) {
}
