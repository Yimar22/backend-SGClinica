package net.andreanunez.encuestabackend.services;

import org.springframework.data.domain.Page;

import net.andreanunez.encuestabackend.entities.PollEntity;
import net.andreanunez.encuestabackend.models.requests.PollCreationRequestModel;

public interface PollService {

    // retornar el id público una vez creada la Poll
    public String createPoll(PollCreationRequestModel model, String email);

    public PollEntity getPoll(String pollId);

    // vamos a retornar un objeto de tipo page ya que vamos a estar paginando las
    // encuestas
    public Page<PollEntity> getPolls(int page, int limit, String email);

    public void togglePollOpened(String pollId, String email);

    public void deletePoll(String pollId, String email);

}
