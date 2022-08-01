package net.andreanunez.encuestabackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.andreanunez.encuestabackend.entities.PollEntity;

@Repository
public interface PollRepository extends CrudRepository<PollEntity, Long> {

    PollEntity findByPollId(String pollId);

    PollEntity findById(long id);

    // método para paginar los resultados
    Page<PollEntity> findAllByUserId(long userId, Pageable pageable);

    PollEntity findByPollIdAndUserId(String pollId, long userId);

}
