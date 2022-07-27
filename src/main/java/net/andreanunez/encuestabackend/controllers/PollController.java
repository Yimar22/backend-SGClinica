package net.andreanunez.encuestabackend.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.andreanunez.encuestabackend.models.requests.PollCreationRequestModel;
import net.andreanunez.encuestabackend.models.responses.CreatedPollRest;
import net.andreanunez.encuestabackend.services.PollService;

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
}
