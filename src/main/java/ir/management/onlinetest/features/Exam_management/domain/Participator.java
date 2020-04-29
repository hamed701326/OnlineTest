package ir.management.onlinetest.features.Exam_management.domain;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.entities.take_exam.TakeExam;
import ir.management.onlinetest.enums.StatusExam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Participator {
    private Long id;
    private String userName;
    private Float score;
    private StatusExam status;
    public Participator(TakeExam takeExam){
        this.id=takeExam.getId();
        this.userName=takeExam.getAccount().getUserName();
        this.score=takeExam.getScore();
        this.status=takeExam.getStatus();
    }
}
