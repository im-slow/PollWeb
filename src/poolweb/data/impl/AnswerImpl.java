package poolweb.data.impl;

import poolweb.data.model.Answer;

public class AnswerImpl implements Answer{

    private int ID;
    private String answer;

    public AnswerImpl() {
        this.ID = 0;
        this.answer = "";
    }

    public int getID(){
        return ID;
    }

    public String getAnswer(){
        return answer;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }
}
