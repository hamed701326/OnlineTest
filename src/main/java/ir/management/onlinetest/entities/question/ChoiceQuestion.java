package ir.management.onlinetest.entities.question;

import ir.management.onlinetest.entities.question.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@DiscriminatorValue("choice-question")
public class ChoiceQuestion extends Question {
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private List<Answer> answers;

    public void setAnswer() {
        super.setAnswer(answers
                .stream()
                .filter(Answer::isCorrect)
                .findFirst().orElse(new Answer()).getContent());
    }
}
