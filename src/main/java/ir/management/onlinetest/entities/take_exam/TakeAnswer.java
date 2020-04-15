package ir.management.onlinetest.entities.take_exam;

import ir.management.onlinetest.entities.question.Answer;
import ir.management.onlinetest.entities.question.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TakeAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private boolean active;
    private float score;
    private  float mainScore;
    @Temporal(TemporalType.TIME)
    private Date createAt;
    @Temporal(TemporalType.TIME)
    private Date updateAt;

    @ManyToOne
    @JoinColumn
    private TakeExam takeExam;

    @ManyToOne
    @JoinColumn
    private Answer answer;
    @ManyToOne
    @JoinColumn
    private Question question;

}
