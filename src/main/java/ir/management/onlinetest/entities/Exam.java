package ir.management.onlinetest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String details;
    private double  timeRequired;
    private int numberOfQuestion;
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @ManyToOne
    @JoinColumn
    private Course course;

    @ManyToOne
    @JoinColumn()
    private Account account;


}
