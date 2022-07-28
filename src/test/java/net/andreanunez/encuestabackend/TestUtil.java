package net.andreanunez.encuestabackend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import net.andreanunez.encuestabackend.entities.AnswerEntity;
import net.andreanunez.encuestabackend.entities.PollEntity;
import net.andreanunez.encuestabackend.entities.QuestionEntity;
import net.andreanunez.encuestabackend.entities.UserEntity;
import net.andreanunez.encuestabackend.enums.QuestionType;
import net.andreanunez.encuestabackend.models.requests.AnswerCreationRequestModel;
import net.andreanunez.encuestabackend.models.requests.PollCreationRequestModel;
import net.andreanunez.encuestabackend.models.requests.PollReplyDetailRequestModel;
import net.andreanunez.encuestabackend.models.requests.PollReplyRequestModel;
import net.andreanunez.encuestabackend.models.requests.QuestionCreationRequestModel;
import net.andreanunez.encuestabackend.models.requests.UserRegisterRequestModel;

public class TestUtil {
    public static UserRegisterRequestModel createValidUser() {

        UserRegisterRequestModel user = new UserRegisterRequestModel();
        user.setEmail(generateRandomString(16) + "@gmail.com");
        user.setName(generateRandomString(8));
        user.setPassword(generateRandomString(8));
        return user;
    }

    public static PollCreationRequestModel createValidPoll() {
        ArrayList<AnswerCreationRequestModel> answers = new ArrayList<>();
        AnswerCreationRequestModel answer = new AnswerCreationRequestModel();
        answer.setContent(generateRandomString(16));
        answers.add(answer);

        ArrayList<QuestionCreationRequestModel> questions = new ArrayList<>();
        QuestionCreationRequestModel question = new QuestionCreationRequestModel();
        question.setContent(generateRandomString(16));
        question.setQuestionOrder(1);
        question.setType("CHECBOX");
        question.setAnswers(answers);
        questions.add(question);

        PollCreationRequestModel poll = new PollCreationRequestModel();
        poll.setContent(generateRandomString(16));
        poll.setOpened(true);
        poll.setQuestions(questions);

        return poll;

    }

    public static PollEntity createValidPollEntity(UserEntity user) {

        PollEntity poll = new PollEntity();
        poll.setContent(generateRandomString(16));
        poll.setOpened(true);
        poll.setPollId(UUID.randomUUID().toString());

        ArrayList<QuestionEntity> questions = new ArrayList<>();
        QuestionEntity question = new QuestionEntity();
        question.setContent(generateRandomString(16));
        question.setQuestionOrder(1);
        question.setType(QuestionType.CHECKBOX);
        question.setPoll(poll);
        questions.add(question);

        ArrayList<AnswerEntity> answers = new ArrayList<>();
        AnswerEntity answer = new AnswerEntity();
        answer.setContent(generateRandomString(16));
        answer.setQuestion(question);
        answers.add(answer);

        question.setAnswers(answers);
        poll.setQuestions(questions);
        return poll;

    }

    public static PollReplyRequestModel createValidPollReply(PollEntity poll) {
        List<PollReplyDetailRequestModel> pollReplies = new ArrayList<>();
        PollReplyDetailRequestModel reply = new PollReplyDetailRequestModel();
        reply.setQuestionId(poll.getQuestions().get(0).getId());
        reply.setAnswerId(poll.getQuestions().get(0).getAnswers().get(0).getId());
        pollReplies.add(reply);

        PollReplyRequestModel model = new PollReplyRequestModel();
        model.setPoll(poll.getId());
        model.setUser(generateRandomString(8));
        model.setPollReplies(pollReplies);
        return model;
    }

    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }

        return sb.toString();

    }
}
