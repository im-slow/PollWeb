package poolweb.data.model;

public interface Answer {

    public int getID();

    public String getAnswer();

    public Question getQuestion();

    public void setID(int id);

    public void setAnswer(String answer);

    public void setQuestion(Question q);

}
