package poolweb.data.impl;

import poolweb.data.model.Poll;
import poolweb.data.model.Question;

public class QuestionImpl implements Question {

    private int ID;
    private String code;
    private String text;
    private String note;
    private boolean mandatory;
    private QuestionType type;
    private int position;
    private String min;
    private String max;
    private String[] qAnswer;
    private Poll poll;


    public QuestionImpl() {
        ID = 0;
        code = "";
        text = "";
        note = "";
        mandatory = false;
        type = null;
        position = 0;
        min = "";
        max = "";
        qAnswer = new String[0];
        poll = null;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getCode(){
        return code;
    }

    @Override
    public String getQuestionText(){
        return text;
    }

    @Override
    public String getNote(){
        return note;
    }

    @Override
    public QuestionType getQuestionType(){
        return type;
    }

    @Override
    public boolean getMandatory(){
        return mandatory;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public String getMinimum() {
        return min;
    }

    @Override
    public String getMaximum() {
        return max;
    }

    @Override
    public String[] getQAnswer() {
        return qAnswer;
    }

    @Override
    public Poll getPoll(){
        return poll;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public void setCode(String code){
        this.code = code;
    }

    @Override
    public void setQuestionText(String text){
        this.text = text;
    }

    @Override
    public void setNote(String note){
        this.note = note;
    }

    @Override
    public void setQuestionType(QuestionType type){
        this.type = type;
    }

    @Override
    public void setMandatory(boolean mandatory){
        this.mandatory = mandatory;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void setMinimum(String min) {
        this.min = min;
    }

    @Override
    public void setMaximum(String max) {
        this.max = max;
    }

    @Override
    public void setQAnswer(String[] qAnswer) {
        this.qAnswer = qAnswer;
    }

    @Override
    public void setPoll(Poll poll){
        this.poll = poll;
    }

}