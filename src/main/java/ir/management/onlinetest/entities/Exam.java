package ir.management.onlinetest.entities;

import ir.management.onlinetest.entities.question.Question;
import ir.management.onlinetest.enums.StatusExam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String details;
    private Float score;
    private double  timeRequired;
    private int numberOfQuestion;
    @Enumerated(EnumType.STRING)
    private StatusExam status;

    @Temporal(TemporalType.DATE)
    private Date createDate;
    @ManyToOne
    @JoinColumn
    private Course course;

    @ManyToOne
    @JoinColumn()
    private Account account;
//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable( name = "exam_question",
//            joinColumns = {@JoinColumn(name = "exam_id")},
//            inverseJoinColumns = {@JoinColumn(name = "question_id")}
//    )
//    List<Question> questions;
    @ElementCollection
    @JoinTable(name="exam_question")
    @MapKeyJoinColumn(name = "question_id")
    @Column(name = "score")
    private Map<Question, Float> questions=new HashMap<>();



}
