package net.andreanunez.encuestabackend.services;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.andreanunez.encuestabackend.entities.AnswerEntity;
import net.andreanunez.encuestabackend.entities.PollEntity;
import net.andreanunez.encuestabackend.entities.QuestionEntity;
import net.andreanunez.encuestabackend.entities.UserEntity;
import net.andreanunez.encuestabackend.interfaces.PollResult;
import net.andreanunez.encuestabackend.models.requests.PollCreationRequestModel;
import net.andreanunez.encuestabackend.repositories.PollRepository;
import net.andreanunez.encuestabackend.repositories.UserRepository;

@Service
public class PollServiceImpl implements PollService {

    PollRepository pollRepository;
    UserRepository userRepository;

    public PollServiceImpl(PollRepository pollRepository, UserRepository userRepository) {
        this.pollRepository = pollRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String createPoll(PollCreationRequestModel model, String email) {

        // 1.Pasamos el correo electrónico del usuario que está autenticado
        UserEntity user = userRepository.findByEmail(email);

        // 2.Mapear desde PollCreationRequestModel hacia PollEntity
        ModelMapper mapper = new ModelMapper();
        PollEntity pollEntity = mapper.map(model, PollEntity.class); // fuente, destino

        // 3.Setear a PollEntity el usuario
        pollEntity.setUser(user);

        // 4.Seteamos el UUID de Poll
        pollEntity.setPollId(UUID.randomUUID().toString());

        // 5. Recorremos cada una de las preguntas de Poll model para setearle como
        // poll la pollEntity
        for (QuestionEntity question : pollEntity.getQuestions()) {
            question.setPoll(pollEntity);

            // 6. Seteamos en la respuestas de la Question esa pregunta
            for (AnswerEntity answer : question.getAnswers()) {
                answer.setQuestion(question);
            }
        }

        // 7.Guardar la Poll en el repositorio, de paso crea las preguntas y las
        // respuestas en la base de datos
        pollRepository.save(pollEntity);

        // 8.Retornar el Id público de pollEntity
        return pollEntity.getPollId();
    }

    @Override
    public PollEntity getPoll(String pollId) {

        PollEntity poll = pollRepository.findByPollId(pollId);
        if (poll == null) {
            throw new RuntimeException("Poll not found");
        }
        if (!poll.isOpened()) {
            // la encuesta está cerrada
            throw new RuntimeException("Poll does not accept more replies");
        }
        return poll;

    }

    @Override
    public Page<PollEntity> getPolls(int page, int limit, String email) {
        // 1.seleccionamos al usuario
        UserEntity user = userRepository.findByEmail(email);
        // 2.creamos a un objeto de tipo Pageable
        Pageable pageable = PageRequest.of(page, limit);
        // 3. Page va a ser de tipo PollEntity
        Page<PollEntity> paginatedPolls = this.pollRepository.findAllByUserId(user.getId(), pageable);
        return paginatedPolls;
    }

    @Override
    public void togglePollOpened(String pollId, String email) {
        UserEntity user = userRepository.findByEmail(email);

        PollEntity poll = pollRepository.findByPollIdAndUserId(pollId, user.getId());

        if (poll == null) {
            throw new RuntimeException("Poll not found");
        }
        poll.setOpened(!poll.isOpened());

        pollRepository.save(poll);
    }

    @Override
    public void deletePoll(String pollId, String email) {
        UserEntity user = userRepository.findByEmail(email);

        PollEntity poll = pollRepository.findByPollIdAndUserId(pollId, user.getId());

        if (poll == null) {
            throw new RuntimeException("Poll not found");
        }
        pollRepository.delete(poll);
    }

    @Override
    public List<PollResult> getResults(String pollId, String email) {
        UserEntity user = userRepository.findByEmail(email);

        PollEntity poll = pollRepository.findByPollIdAndUserId(pollId, user.getId());
        if (poll == null) {
            throw new RuntimeException("Poll not found");
        }

        return pollRepository.getPollResults(poll.getId());
    }

}
