package ir.management.onlinetest.features.Exam_management.domain;

import ir.management.onlinetest.entities.Exam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamDTO {
    public ExamDTO(Exam exam){
        this.id= exam.getId();
        this.title= exam.getTitle();
        this.details= exam.getDetails();
        this.timeRequired= exam.getTimeRequired();
        this.createBy= exam.getAccount().getUserName();
        this.numberOfQuestion= exam.getNumberOfQuestion();
    }
    private Long id;
    private String title;
    private String details;
    private Double  timeRequired;
    private String createBy;
    private int numberOfQuestion;

}
