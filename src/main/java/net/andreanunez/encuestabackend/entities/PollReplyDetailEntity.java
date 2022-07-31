package net.andreanunez.encuestabackend.entities;

import javax.persistence.Entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity(name = "poll_reply_details")
@Data
public class PollReplyDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long questionId;
    private long answerId;
    private String answerContent;

    // una poll reply puede tener muchas poll reply details
    @ManyToOne
    @JoinColumn(name = "poll_reply_id")
    private PollReplyEntity pollReply;
}
