package net.andreanunez.encuestabackend.services;

import java.util.UUID;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import net.andreanunez.encuestabackend.entities.AnswerEntity;
import net.andreanunez.encuestabackend.entities.PollEntity;
import net.andreanunez.encuestabackend.entities.QuestionEntity;
import net.andreanunez.encuestabackend.entities.UserEntity;
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

}
