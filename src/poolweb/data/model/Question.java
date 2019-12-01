package poolweb.data.model;

public interface Question {

    public static enum QuestionType {
        SHORTTEXT,
        LONGTEXT,
        NUMBER,
        DATE,
        SINGLECHOISE,
        MULTIPLECHOISE;
    }

    int getID();

    String getCode();

    String getQuestionText();

    String getNote();

    QuestionType getQuestionType();

    boolean getMandatory();

    int getPositionNumber();

    String getMinimum();

    String getMaximum();

    String getQAnswer();

    Poll getPoll();

    void setID(int ID);

    void setCode(String code);

    void setQuestionText(String text);

    void setNote(String note);

    void setQuestionType(QuestionType type);

    void setMandatory(boolean mandatory);

    void setPositionNumber(int position);

    void setMinimum(String min);

    void setMaximum(String max);

    void setQAnswer(String qAnswer);

    void setPoll(Poll poll);

}
