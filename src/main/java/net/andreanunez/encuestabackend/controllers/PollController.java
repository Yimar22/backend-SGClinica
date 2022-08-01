package net.andreanunez.encuestabackend.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.andreanunez.encuestabackend.entities.PollEntity;
import net.andreanunez.encuestabackend.models.requests.PollCreationRequestModel;
import net.andreanunez.encuestabackend.models.responses.CreatedPollRest;
import net.andreanunez.encuestabackend.models.responses.PaginatedPollRest;
import net.andreanunez.encuestabackend.models.responses.PollRest;
import net.andreanunez.encuestabackend.services.PollService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    // traer todas las rutas de un determinado usuario
    @GetMapping // polls?page=1&limit=10
    public PaginatedPollRest getPolls(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            Authentication authentication) {

        // sacamos page y limit desde la url
        Page<PollEntity> paginatedPolls = pollService.getPolls(page, limit, authentication.getPrincipal().toString());

        // Mapear desde PollEntity hacia PollRest que es lo que vamos a estar
        // devolviendo
        ModelMapper mapper = new ModelMapper();

        // aquí le estamos diciendo al mapper que no queremos incluir las preguntas de
        // la Poll
        mapper.typeMap(PollEntity.class, PollRest.class)
                .addMappings(m -> m.skip(PollRest::setQuestions));

        // pasar la lista de polls mappeada hacia un poll rest
        PaginatedPollRest paginatedPollRest = new PaginatedPollRest();
        paginatedPollRest.setPolls(
                paginatedPolls.getContent().stream().map(p -> mapper.map(p, PollRest.class))
                        .collect(Collectors.toList()));

        // setear los datos de la paginación
        paginatedPollRest.setTotalPages(paginatedPolls.getTotalPages());
        paginatedPollRest.setTotalRecords(paginatedPolls.getTotalElements());
        paginatedPollRest.setCurrentPageRecords(paginatedPolls.getNumberOfElements());
        paginatedPollRest.setCurrentPage(paginatedPolls.getPageable().getPageNumber() + 1); // LE SUmanos 1 porq las
                                                                                            // paginas en JPA empiezan
                                                                                            // desde 0

        return paginatedPollRest;
    }

    // Método para modificar el estado de la encuesta
    @PatchMapping(path = "/{id}")
    public void togglePollOpened(@PathVariable String id, Authentication authentication) {
        pollService.togglePollOpened(id, authentication.getPrincipal().toString());
    }

    // endpoint para eliminar la encuesta
    @DeleteMapping(path = "/{id}")
    public void deletePoll(@PathVariable String id, Authentication authentication) {
        pollService.deletePoll(id, authentication.getPrincipal().toString());
    }

}
