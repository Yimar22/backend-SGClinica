package net.andreanunez.encuestabackend.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Esta clase es lo que vamos a devolver cuando se cree una Poll
 * La utiliza el controlador de Poll
 */
@Data
@AllArgsConstructor
public class CreatedPollRest {

    private String pollId;

}
