package ir.management.onlinetest.entities.question;

import ir.management.onlinetest.enums.Level;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class QuestionTest {

    @Test
    void checkAnswer() {
        ChoiceQuestion question=new ChoiceQuestion();
//        question.setContent("what is hibernate?");
//        question.setLevel(Level.EASY);
//        question.setScore(2);
//        question.setTitle("The concept of hibernate");
//        question.setAnswers(
//                Arrays.asList(
//                        new Answer(null,
//                                "map between java and database, convert object to row",
//                                true),
//                        new Answer(null,
//                                "relation between java and web",
//                                false),
//                        new Answer(null,
//                                "relation between every thing",
//                                false)
//                )
//        );
//
//        System.out.println(
//            question.checkAnswer("map between java and database, convert object to row")
//        );

    }
}