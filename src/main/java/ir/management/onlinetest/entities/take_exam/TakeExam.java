package ir.management.onlinetest.entities.take_exam;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.entities.Exam;
import ir.management.onlinetest.enums.StatusExam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class TakeExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float score;
    private float maxScore;
    private double leftTime;
    private StatusExam status;
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Temporal(TemporalType.DATE)
    private Date published;

    @Temporal(TemporalType.TIME)
    private Date startedAt;
    @Temporal(TemporalType.TIME)
    private Date finishedAt;
    private String content;

    @ManyToOne
    @JoinColumn
    private Account account;
    @ManyToOne
    @JoinColumn
    private Exam exam;
}
