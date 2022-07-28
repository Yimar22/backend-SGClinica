package net.andreanunez.encuestabackend.services;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import net.andreanunez.encuestabackend.entities.PollEntity;
import net.andreanunez.encuestabackend.entities.PollReplyDetailEntity;
import net.andreanunez.encuestabackend.entities.PollReplyEntity;
import net.andreanunez.encuestabackend.models.requests.PollReplyRequestModel;
import net.andreanunez.encuestabackend.repositories.PollReplyRepository;
import net.andreanunez.encuestabackend.repositories.PollRepository;

@Service
public class PollReplyServiceImpl implements PollReplyService {

    PollReplyRepository pollReplyRepository;
    PollRepository pollRepository;

    public PollReplyServiceImpl(PollReplyRepository pollReplyRepository, PollRepository pollRepository) {
        this.pollReplyRepository = pollReplyRepository;
        this.pollRepository = pollRepository;
    }

    @Override
    public long createPollReply(PollReplyRequestModel model) {

        // Mapear de PollReplyRequestModel hacia PollReplyEntity
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true); // para solucionar el problema de ambiguedad a la hora de
                                                             // mapear
        PollReplyEntity pollReply = mapper.map(model, PollReplyEntity.class);

        // validación de si el usuario recorrió todas las preguntas
        Set<Long> uniqueReplies = new HashSet<>();

        PollEntity poll = pollRepository.findById(model.getPoll());

        // setear la poll a pollreply
        pollReply.setPoll(poll);

        // recorrer todas las pollanswerdetail y setearle el pollReply
        for (PollReplyDetailEntity pollReplyDetailEntity : pollReply.getPollReplies()) {
            pollReplyDetailEntity.setPollReply(pollReply);
            uniqueReplies.add(pollReplyDetailEntity.getQuestionId());
        }
        if (uniqueReplies.size() != poll.getQuestions().size()) {
            // si el size es distinto es porque no respondió todas las preguntas
            throw new RuntimeException("You must answer all the questions");
        }

        // enviar a la base de datos
        PollReplyEntity replyEntity = pollReplyRepository.save(pollReply);
        return replyEntity.getId();
    }

}
