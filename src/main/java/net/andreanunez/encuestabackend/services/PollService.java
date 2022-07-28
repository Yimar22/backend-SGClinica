package net.andreanunez.encuestabackend.services;

import net.andreanunez.encuestabackend.entities.PollEntity;
import net.andreanunez.encuestabackend.models.requests.PollCreationRequestModel;

public interface PollService {

    // retornar el id público una vez creada la Poll
    public String createPoll(PollCreationRequestModel model, String email);

    public PollEntity getPoll(String pollId);
}
