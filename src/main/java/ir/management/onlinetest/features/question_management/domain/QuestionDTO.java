package ir.management.onlinetest.features.question_management.domain;

import ir.management.onlinetest.entities.question.Answer;
import ir.management.onlinetest.entities.question.ChoiceQuestion;
import ir.management.onlinetest.entities.question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Setter
@Getter
@NoArgsConstructor
public class QuestionDTO {
    private Long id;
    private String content,title;
    private List<Answer> answers;
    private Float score;
    private String type;
    public QuestionDTO(Question question){
        this.id=question.getId();
        this.title= question.getTitle();
        this.content=question.getContent();
        this.type="FreeQuestion";
        if(question instanceof ChoiceQuestion){
            this.answers=((ChoiceQuestion)question).getAnswers();
            this.type="ChoiceQuestion";
        }
        this.score=question.getScore();
    }
}