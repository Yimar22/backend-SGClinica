package net.andreanunez.encuestabackend.models.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollResultRest {
    private String question;

    // lista de cada una de las respuestas de la pregunta
    private List<ResultDetailRest> details;
}
