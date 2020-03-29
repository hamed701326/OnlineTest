package ir.management.onlinetest.entities.question;

import ir.management.onlinetest.enums.Level;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    private String content;
    private String answer;
    private float score;
    @Enumerated(EnumType.STRING)
    private Level level;
    public boolean checkAnswer(String response){
        return response.equals(answer);
    }
    public void display(){
        System.out.println(content);
    }
}
