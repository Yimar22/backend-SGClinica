package net.andreanunez.encuestabackend.models.responses;

import java.util.List;

import lombok.Data;

@Data
public class PollRest {

    private long id;

    // id público
    private String pollId;

    private String content; // titulo de la encuesta

    private boolean opened;

    // lista de questions
    private List<QuestionRest> questions;
}
