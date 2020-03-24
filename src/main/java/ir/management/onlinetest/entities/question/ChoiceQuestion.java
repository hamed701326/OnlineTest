package ir.management.onlinetest.entities.question;

import ir.management.onlinetest.entities.question.Question;

import java.util.ArrayList;

public class ChoiceQuestion extends Question {
    private ArrayList<String> choices;
    public void addChoice(String choice,boolean correct){
        choices.add(choice);
        if(correct){
            setAnswer(""+choices.size());
        }
    }
}
