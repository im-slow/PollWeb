package poolweb.data.impl;

import poolweb.data.model.Answer;
import poolweb.data.model.Question;

public class AnswerImpl implements Answer{

    private int ID;
    private String answer;
    private Question question;

    public AnswerImpl() {
        this.ID = 0;
        this.answer = "";
        this.question = null;
    }

    @Override
    public int getID(){
        return ID;
    }

    @Override
    public String getAnswer(){
        return answer;
    }

    @Override
    public Question getQuestion() { return question; }

    @Override
    public void setID(int ID){
        this.ID = ID;
    }

    @Override
    public void setAnswer(String answer){
        this.answer = answer;
    }

    @Override
    public void setQuestion(Question question) { this.question = question; }
}
