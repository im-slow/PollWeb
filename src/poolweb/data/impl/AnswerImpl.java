package poolweb.data.impl;

import poolweb.data.model.Answer;

public class AnswerImpl implements Answer{

    private int ID;
    private String answer;
    private int questionID;

    public AnswerImpl() {
        this.ID = 0;
        this.answer = "";
        this.questionID = 0;
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
    public int getQuestionID() {
        return questionID;
    }

    @Override
    public void setID(int ID){
        this.ID = ID;
    }

    @Override
    public void setAnswer(String answer){
        this.answer = answer;
    }

    @Override
    public void setQuestionID(int id) {
        this.questionID = id;
    }
}
