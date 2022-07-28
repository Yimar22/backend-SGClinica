package net.andreanunez.encuestabackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import net.andreanunez.encuestabackend.entities.PollEntity;
import net.andreanunez.encuestabackend.entities.UserEntity;
import net.andreanunez.encuestabackend.models.requests.PollCreationRequestModel;
import net.andreanunez.encuestabackend.models.requests.UserLoginRequestModel;
import net.andreanunez.encuestabackend.models.requests.UserRegisterRequestModel;
import net.andreanunez.encuestabackend.models.responses.PollRest;
import net.andreanunez.encuestabackend.models.responses.ValidationErrors;
import net.andreanunez.encuestabackend.repositories.PollRepository;
import net.andreanunez.encuestabackend.repositories.UserRepository;
import net.andreanunez.encuestabackend.services.PollService;
import net.andreanunez.encuestabackend.services.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class PollControllerTest {

    private static final String API_URL = "/polls";

    private static final String API_LOGIN_URL = "/users/login";

    private String token = "";
    private UserEntity user = null;
    @Autowired
    TestRestTemplate testRestTemplate; // para poder enviar una petición

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PollRepository pollRepository;

    @Autowired
    PollService pollService;

    @BeforeAll
    public void initializeObjects() {
        UserRegisterRequestModel user = TestUtil.createValidUser();
        this.user = userService.createUser(user);
        UserLoginRequestModel model = new UserLoginRequestModel();
        model.setEmail(user.getEmail());
        model.setPassword(user.getPassword());
        ResponseEntity<Map<String, String>> response = login(model,
                new ParameterizedTypeReference<Map<String, String>>() {
                });
        Map<String, String> body = response.getBody();
        this.token = body.get("token");
    }

    @AfterEach
    public void cleanup() {
        pollRepository.deleteAll();
    }

    @AfterAll
    public void cleanupAfter() {
        userRepository.deleteAll();
    }

    @Test
    public void createPoll_sinAutenticacion_retornaForbidden() {
        ResponseEntity<Object> response = createPoll(new PollCreationRequestModel(), Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);

    }

    @Test
    public void createPoll_conAutenticacionSinDatos_retornaBadRequest() {
        ResponseEntity<ValidationErrors> response = createPoll(
                new PollCreationRequestModel(), new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test
    public void createPoll_conAutenticacionSinElContenidoDeLaEncuesta_retornaBadRequest() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test
    public void createPoll_conAutenticacionSinElContenidoDeLaEncuesta_retornaValidationErrorParaElContenido() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.setContent("");
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertTrue(response.getBody().getErrors().containsKey("content"));

    }

    @Test
    public void createPoll_conAutenticacionSinPreguntasParaLaEncuesta_retornaBadRequest() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.setQuestions(null);
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test
    public void createPoll_conAutenticacionSinPreguntasParaLaEncuesta_retornaValidationErrorParaPreguntas() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.setQuestions(null);
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertTrue(response.getBody().getErrors().containsKey("questions"));

    }

    @Test
    public void createPoll_conAutenticacionConPreguntaValidaSinContenido_retornaBadRequest() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).setContent("");
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test
    public void createPoll_conAutenticacionConPreguntaValidaSinContenido_retornaValidationErrorParaContenidoDePregunta() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).setContent("");
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertTrue(response.getBody().getErrors().containsKey("questions[0].content"));

    }

    @Test
    public void createPoll_conAutenticacionConPreguntaValidaConOrdenIncorrecto_retornaBadRequest() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).setQuestionOrder(0);
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test
    public void createPoll_conAutenticacionConPreguntaValidaConOrdenIncorrecto_retornaValidationErrorParaOrdenDePregunta() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).setQuestionOrder(0);
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertTrue(response.getBody().getErrors().containsKey("questions[0].questionOrder"));
    }

    @Test
    public void createPoll_conAutenticacionConPreguntaValidaConQuestionTypeIncorrecto_retornaBadRequest() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).setType("hola");
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test
    public void createPoll_conAutenticacionConPreguntaValidaConQuestionTypeIncorrecto_retornaValidationErrorParaQurstionType() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).setType("hola");
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertTrue(response.getBody().getErrors().containsKey("questions[0].type"));

    }

    @Test
    public void createPoll_conAutenticacionConPreguntaValidaSinRespuestas_retornaBadRequest() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).setAnswers(null);
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test
    public void createPoll_conAutenticacionConPreguntaValidaSinRespuestas_retornaValidationErrorParaListaDeRespuestas() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).setAnswers(null);
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertTrue(response.getBody().getErrors().containsKey("questions[0].answers"));

    }

    @Test
    public void createPoll_conAutenticacionSinContenidoEnLaRespuesta_retornaBadRequest() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).getAnswers().get(0).setContent("");
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test
    public void createPoll_conAutenticacionSinContenidoEnLaRespuesta_retornaValidationErrorParaContenidoDeRespuesta() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        poll.getQuestions().get(0).getAnswers().get(0).setContent("");
        ResponseEntity<ValidationErrors> response = createPoll(
                poll, new ParameterizedTypeReference<ValidationErrors>() {
                });
        assertTrue(response.getBody().getErrors().containsKey("questions[0].answers[0].content"));

    }

    @Test
    public void createPoll_conAutenticacionConEncuestaValida_retornaOK() {
        PollCreationRequestModel poll = TestUtil.createValidPoll();
        ResponseEntity<Object> response = createPoll(
                poll, new ParameterizedTypeReference<Object>() {
                });
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    /*
     * @Test
     * public void
     * createPoll_conAutenticacionConEncuestaValida_retornaCreatedPollId() {
     * PollCreationRequestModel poll = TestUtil.createValidPoll();
     * ResponseEntity<Map<String, String>> response = createPoll(
     * poll, new ParameterizedTypeReference<Map<String, String>>() {
     * });
     * Map<String, String> body = response.getBody();
     * assertTrue(body.containsKey("pollId"));
     * 
     * }
     * 
     * @Test
     * public void
     * createPoll_conAutenticacionConEncuestaValida_guardaEnBaseDeDatos() {
     * PollCreationRequestModel poll = TestUtil.createValidPoll();
     * ResponseEntity<Map<String, String>> response = createPoll(
     * poll, new ParameterizedTypeReference<Map<String, String>>() {
     * });
     * Map<String, String> body = response.getBody();
     * PollEntity pollDB = pollRepository.findByPollId(body.get("pollId"));
     * assertNotNull(pollDB);
     * 
     * }
     */

    @Test
    public void getPollWithQuestions_cuandoLaEncuestaNoExisteEnLaBaseDeDatos_retornaInternalServerError() {

        ResponseEntity<Object> responseEntity = getPollWithQuestions(API_URL + "/uuid/questions", Object.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void getPollWithQuestions_cuandoLaEncuestaExisteEnLaBaseDeDatos_retornaOK() {
        PollCreationRequestModel model = TestUtil.createValidPoll();
        String uuid = pollService.createPoll(model, user.getEmail());
        ResponseEntity<Object> responseEntity = getPollWithQuestions(API_URL + "/" + uuid + "/questions", Object.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getPollWithQuestions_cuandoLaEncuestaExisteEnLaBaseDeDatos_retornaPollRest() {
        PollCreationRequestModel model = TestUtil.createValidPoll();
        String uuid = pollService.createPoll(model, user.getEmail());
        ResponseEntity<PollRest> responseEntity = getPollWithQuestions(API_URL + "/" + uuid + "/questions",
                PollRest.class);
        assertEquals(uuid, responseEntity.getBody().getPollId());
    }

    public <T> ResponseEntity<T> getPollWithQuestions(String url, Class<T> responseType) {
        return testRestTemplate.getForEntity(url, responseType);
    }

    public <T> ResponseEntity<T> login(UserLoginRequestModel data, ParameterizedTypeReference<T> responseType) {
        HttpEntity<UserLoginRequestModel> entity = new HttpEntity<UserLoginRequestModel>(data, new HttpHeaders());
        return testRestTemplate.exchange(API_LOGIN_URL, HttpMethod.POST, entity, responseType);
    }

    public <T> ResponseEntity<T> createPoll(PollCreationRequestModel data, ParameterizedTypeReference<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<PollCreationRequestModel> entity = new HttpEntity<PollCreationRequestModel>(data, headers);
        return testRestTemplate.exchange(API_URL, HttpMethod.POST, entity, responseType);
    }

    public <T> ResponseEntity<T> createPoll(PollCreationRequestModel data, Class<T> responseType) {
        return testRestTemplate.postForEntity(API_URL, data, responseType);
    }

}
