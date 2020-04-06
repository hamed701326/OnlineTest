package ir.management.onlinetest.entities.question;

import ir.management.onlinetest.enums.Level;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@NoArgsConstructor
@Entity
@DiscriminatorValue("freeResponse-question")
public class FreeResponseQuestion extends Question {


    public FreeResponseQuestion(String title, String content,String answer ,Level level, float score) {
        super(null,title,content,answer,score,level);
    }
}
