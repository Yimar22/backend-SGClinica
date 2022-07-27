package net.andreanunez.encuestabackend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import net.andreanunez.encuestabackend.enums.QuestionType;

@Entity(name = "questions")
@Data
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String content;

    @Column
    private int questionOrder;

    @Column
    private QuestionType type;

    // podemos tener muchas preguntas dentro de una encuesta
    @ManyToOne
    @JoinColumn(name = "poll_id")
    private PollEntity poll;

    // Una pregunta puede tener muchas respuestas
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "question")
    private List<AnswerEntity> answers = new ArrayList<>();
}
