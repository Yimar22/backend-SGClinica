package net.andreanunez.encuestabackend.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.andreanunez.encuestabackend.entities.PollReplyEntity;

@Repository
public interface PollReplyRepository extends CrudRepository<PollReplyEntity, Long> {

    public List<PollReplyEntity> findAll();
}
