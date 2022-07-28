package net.andreanunez.encuestabackend.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.andreanunez.encuestabackend.entities.PollEntity;
import net.andreanunez.encuestabackend.models.requests.PollCreationRequestModel;
import net.andreanunez.encuestabackend.models.responses.CreatedPollRest;
import net.andreanunez.encuestabackend.models.responses.PollRest;
import net.andreanunez.encuestabackend.services.PollService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController()
@RequestMapping("/polls")
public class PollController {

    @Autowired
    PollService pollService;

    @PostMapping
    public CreatedPollRest createPoll(@RequestBody @Valid PollCreationRequestModel model,
            Authentication authentication) {

        String pollId = pollService.createPoll(model, authentication.getPrincipal().toString());

        return new CreatedPollRest(pollId);
    }

    @GetMapping(path = "{id}/questions")
    public PollRest getPollWithQuestions(@PathVariable String id) {

        PollEntity poll = pollService.getPoll(id);
        // mapear la encuesta hacia el poll rest
        ModelMapper mapper = new ModelMapper();
        return mapper.map(poll, PollRest.class);
    }

}
