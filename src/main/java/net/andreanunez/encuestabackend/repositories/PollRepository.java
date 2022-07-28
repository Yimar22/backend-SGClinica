package net.andreanunez.encuestabackend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.andreanunez.encuestabackend.entities.PollEntity;

@Repository
public interface PollRepository extends CrudRepository<PollEntity, Long> {

    public PollEntity findByPollId(String pollId);

    public PollEntity findById(long id);
}
