package net.andreanunez.encuestabackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import net.andreanunez.encuestabackend.entities.PollEntity;
import net.andreanunez.encuestabackend.entities.PollReplyEntity;
import net.andreanunez.encuestabackend.entities.UserEntity;
import net.andreanunez.encuestabackend.models.requests.PollReplyRequestModel;
import net.andreanunez.encuestabackend.models.responses.ValidationErrors;
import net.andreanunez.encuestabackend.repositories.PollReplyRepository;
import net.andreanunez.encuestabackend.repositories.PollRepository;
import net.andreanunez.encuestabackend.repositories.UserRepository;
import net.andreanunez.encuestabackend.services.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class PollReplyControllerTest {

    private static final String API_URL = "/polls/reply";
    @Autowired
    TestRestTemplate testRestTemplate; // para poder enviar una petición

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PollRepository pollRepository;

    @Autowired
    PollReplyRepository pollReplyRepository;

    PollEntity poll;

    @BeforeAll
    public void initializeObjects() {
        UserEntity user = userService.createUser(TestUtil.createValidUser());
        this.poll = pollRepository.save(TestUtil.createValidPollEntity(user));
    }

    @AfterAll
    public void cleanUpAfter() {
        pollRepository.deleteAll();
        userRepository.deleteAll();
    }

    @AfterEach
    public void cleanUp() {
        pollReplyRepository.deleteAll();
    }

    @Test
    public void replyPoll_sinUsuario_retornaBadRequest() {

        PollReplyRequestModel model = TestUtil.createValidPollReply(poll);
        model.setUser(null);
        ResponseEntity<ValidationErrors> response = createPollReply(model, ValidationErrors.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void replyPoll_sinUsuario_retornaValidationErrors() {

        PollReplyRequestModel model = TestUtil.createValidPollReply(poll);
        model.setUser(null);
        ResponseEntity<ValidationErrors> response = createPollReply(model, ValidationErrors.class);
        assertTrue(response.getBody().getErrors().containsKey("user"));
    }

    @Test
    public void replyPoll_conUnPollIdInvalido_retornaBadRequest() {

        PollReplyRequestModel model = TestUtil.createValidPollReply(poll);
        model.setPoll(0);
        ResponseEntity<ValidationErrors> response = createPollReply(model, ValidationErrors.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void replyPoll_conUnPollIdInvalido_retornaValidationError() {

        PollReplyRequestModel model = TestUtil.createValidPollReply(poll);
        model.setPoll(0);
        ResponseEntity<ValidationErrors> response = createPollReply(model, ValidationErrors.class);
        assertTrue(response.getBody().getErrors().containsKey("poll"));
    }

    @Test
    public void replyPoll_conListaDePollRepliesVacia_retornaBadRequest() {

        PollReplyRequestModel model = TestUtil.createValidPollReply(poll);
        model.setPollReplies(null);
        ResponseEntity<ValidationErrors> response = createPollReply(model, ValidationErrors.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void replyPoll_conListaDePollRepliesVacia_retornaValidationErrors() {

        PollReplyRequestModel model = TestUtil.createValidPollReply(poll);
        model.setPollReplies(null);
        ResponseEntity<ValidationErrors> response = createPollReply(model, ValidationErrors.class);
        assertTrue(response.getBody().getErrors().containsKey("pollReplies"));
    }

    @Test
    public void replyPoll_conQuestionIdInvalido_retornaBadRequest() {

        PollReplyRequestModel model = TestUtil.createValidPollReply(poll);
        model.getPollReplies().get(0).setQuestionId(0);
        ResponseEntity<ValidationErrors> response = createPollReply(model, ValidationErrors.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void replyPoll_conQuestionIdInvalido_retornaValidationError() {

        PollReplyRequestModel model = TestUtil.createValidPollReply(poll);
        model.getPollReplies().get(0).setQuestionId(0);
        ResponseEntity<ValidationErrors> response = createPollReply(model, ValidationErrors.class);
        assertTrue(response.getBody().getErrors().containsKey("pollReplies[0].questionId"));
    }

    @Test
    public void replyPoll_conAnswerIdInvalido_retornaBadRequest() {

        PollReplyRequestModel model = TestUtil.createValidPollReply(poll);
        model.getPollReplies().get(0).setAnswerId(0);
        ResponseEntity<ValidationErrors> response = createPollReply(model, ValidationErrors.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void replyPoll_conAnswerIdInvalido_retornaValidationError() {

        PollReplyRequestModel model = TestUtil.createValidPollReply(poll);
        model.getPollReplies().get(0).setAnswerId(0);
        ResponseEntity<ValidationErrors> response = createPollReply(model, ValidationErrors.class);
        assertTrue(response.getBody().getErrors().containsKey("pollReplies[0].answerId"));
    }

    @Test
    public void replyPoll_conDatosValidos_retornaOK() {

        PollReplyRequestModel model = TestUtil.createValidPollReply(poll);
        ResponseEntity<Object> response = createPollReply(model, Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void replyPoll_conDatosValidos_saveToDatabase() {

        PollReplyRequestModel model = TestUtil.createValidPollReply(poll);
        createPollReply(model, Object.class);
        List<PollReplyEntity> replies = pollReplyRepository.findAll();
        assertEquals(replies.size(), 1);
    }

    public <T> ResponseEntity<T> createPollReply(PollReplyRequestModel data, Class<T> responseType) {
        return testRestTemplate.postForEntity(API_URL, data, responseType);
    }

}
