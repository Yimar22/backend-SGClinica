package net.andreanunez.encuestabackend.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.andreanunez.encuestabackend.entities.PollEntity;
import net.andreanunez.encuestabackend.interfaces.PollResult;

@Repository
public interface PollRepository extends CrudRepository<PollEntity, Long> {

    PollEntity findByPollId(String pollId);

    PollEntity findById(long id);

    // método para paginar los resultados
    Page<PollEntity> findAllByUserId(long userId, Pageable pageable);

    PollEntity findByPollIdAndUserId(String pollId, long userId);

    // Función que se encarga de devolver el resultado de la consulta
    // a través de un objeto PollResult
    @Query(value = "SELECT q.question_order questionOrder, prd.question_id as questionId, q.content as question, prd.answer_id as answerId, a.content as answer, count(prd.answer_id) as result FROM poll_replies pr LEFT JOIN poll_reply_details prd ON prd.poll_reply_id = pr.id LEFT JOIN answers a ON a.id = prd.answer_id LEFT JOIN questions q ON q.id = prd.question_id WHERE pr.poll_id = :pollId GROUP BY prd.question_id, prd.answer_id ORDER BY q.question_order ASC", nativeQuery = true)
    List<PollResult> getPollResults(@Param("pollId") long id);

}
