package net.andreanunez.encuestabackend.models.requests;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Esta clase sirve para que con ella se haga un mapeo del request de una Poll
 * con esta clase
 **/
@Data
public class PollCreationRequestModel {

    @NotEmpty
    private String content;

    @NotNull
    private boolean isOpened;

    @Valid
    @NotEmpty
    @Size(min = 1, max = 30) // cada encuesta tiene cmo máximo 30 preguntas
    private List<QuestionCreationRequestModel> questions;

}
