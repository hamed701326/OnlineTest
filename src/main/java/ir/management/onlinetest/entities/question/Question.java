package ir.management.onlinetest.entities.question;

import lombok.Getter;
import lombok.Setter;

@Setter
public class Question {
    private String text;
    private String answer;
    public boolean checkAnswer(String response){
        return response.equals(answer);
    }
    public void display(){
        System.out.println(text);
    }
}
