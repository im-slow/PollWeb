package poolweb.data.proxy;

import poolweb.data.dao.QuestionDAO;
import poolweb.data.dao.UserDAO;
import poolweb.data.impl.AnswerImpl;
import poolweb.data.model.Question;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AnswerProxy extends AnswerImpl {
    protected boolean dirty;
    protected int question_key;
    protected DataLayer dataLayer;

    public AnswerProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.dirty = false;
        this.question_key = 0;
    }

    @Override
    public Question getQuestion() {
        if (super.getQuestion() == null && question_key > 0) {
            try {
                super.setQuestion(((QuestionDAO) dataLayer.getDAO(Question.class)).getQuestionByID(question_key));
            } catch (DataException ex) {
                Logger.getLogger(QuestionProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.getQuestion();
    }

    @Override
    public void setID(int ID) {
        super.setID(ID);
        this.dirty = true;
    }

    @Override
    public void setAnswer(String answer){
        super.setAnswer(answer);
        this.dirty = true;
    }

    public void setQuestionKey(int question_key){
        this.question_key = question_key;
        super.setQuestion(null);
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }
}
