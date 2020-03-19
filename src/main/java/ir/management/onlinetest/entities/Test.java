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
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String details;
    private float  timeRequired;
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @ManyToOne
    @JoinColumn
    private Course course;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Account account;


}
