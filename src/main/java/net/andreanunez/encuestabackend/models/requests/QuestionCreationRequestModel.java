package net.andreanunez.encuestabackend.models.requests;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;
import net.andreanunez.encuestabackend.annotations.ValueOfEnum;
import net.andreanunez.encuestabackend.enums.QuestionType;

/**
 * Esta clase sirve para que con ella se haga un mapeo del request de una
 * Question
 * con esta clase
 **/
@Data
public class QuestionCreationRequestModel {

    @NotEmpty
    private String content;

    @NotNull
    @Range(min = 1, max = 30) // esto indica que la encuesta tendrá como máximo 30 preguntas
    private int questionOrder;

    @NotEmpty
    @ValueOfEnum(enumClass = QuestionType.class)
    private String type;

    @Valid
    @NotEmpty
    @Size(min = 1, max = 10) // máximo 10 respuestas por pregunta
    private List<AnswerCreationRequestModel> answers;

}
