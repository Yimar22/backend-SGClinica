package net.andreanunez.encuestabackend.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@EntityListeners(AuditingEntityListener.class) // para dejar generar la fecha automáticamente
@Entity(name = "poll_replies")
@Data
public class PollReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String user;

    @CreatedDate
    private Date createdAt;

    // Una encuesta puede tener muchas poll reply
    @ManyToOne
    @JoinColumn(name = "poll_id")
    private PollEntity poll;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "pollReply")
    private List<PollReplyDetailEntity> pollReplies = new ArrayList<>();
}
