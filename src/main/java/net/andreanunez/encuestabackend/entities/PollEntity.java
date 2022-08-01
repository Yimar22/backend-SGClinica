package net.andreanunez.encuestabackend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "polls")
@Data
@Table(indexes = @Index(columnList = "pollId", name = "index_pollid", unique = true)) // indice para el id público
public class PollEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // id público
    @Column(nullable = false)
    private String pollId;

    @Column(nullable = false, length = 255)
    private String content; // titulo de la encuesta

    @Column
    private boolean opened;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // una encuesta puede tener muchas preguntas
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "poll")
    private List<QuestionEntity> questions = new ArrayList<>();

    // Una encuesta puede tener muchas poll reply
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "poll")
    private List<PollReplyEntity> replies = new ArrayList<>();
}
