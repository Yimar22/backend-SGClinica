package net.andreanunez.encuestabackend;

import java.util.ArrayList;
import java.util.Random;

import net.andreanunez.encuestabackend.models.requests.AnswerCreationRequestModel;
import net.andreanunez.encuestabackend.models.requests.PollCreationRequestModel;
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
