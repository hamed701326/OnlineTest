package ir.management.onlinetest.entities.question;

import ir.management.onlinetest.entities.question.Question;
import ir.management.onlinetest.enums.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("choice-question")
public class ChoiceQuestion extends Question {
    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn
    private List<Answer> answers;

    public ChoiceQuestion(List<Answer> answers,
                   String title,
                   String content,
                   Level level,
                   Float score
                   ){
        super(null,
                title,
                content,
                null,
                score,
                level);

        super
                .setAnswer(
                        answers
                                .stream()
                                .filter(Answer::isCorrect)
                                .findFirst().orElse(new Answer()).getContent()
                );
        this.answers=answers;

    }
}
