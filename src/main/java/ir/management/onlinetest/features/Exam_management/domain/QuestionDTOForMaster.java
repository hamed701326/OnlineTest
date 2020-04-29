package ir.management.onlinetest.features.Exam_management.domain;

import ir.management.onlinetest.entities.question.Question;
import ir.management.onlinetest.entities.take_exam.TakeAnswer;
import ir.management.onlinetest.entities.take_exam.TakeExam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTOForMaster {
    private Long takeAnswerId;
    private String questionContent;
    private String studentAnswer;
    private String masterAnswer;
    private float score;
    public QuestionDTOForMaster(TakeAnswer takeAnswer){
        Question question=takeAnswer.getQuestion();
        this.takeAnswerId=takeAnswer.getId();
        this.questionContent=question.getContent();
        this.studentAnswer=takeAnswer.getContent();
        this.masterAnswer=question.getAnswer();
        this.score=question.getScore();
    }
}
