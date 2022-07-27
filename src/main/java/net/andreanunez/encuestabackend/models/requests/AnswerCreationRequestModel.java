package net.andreanunez.encuestabackend.models.requests;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * Esta clase sirve para que con ella se haga un mapeo del request de una
 * Answer
 * con esta clase
 **/
@Data
public class AnswerCreationRequestModel {

    private String content;
}
