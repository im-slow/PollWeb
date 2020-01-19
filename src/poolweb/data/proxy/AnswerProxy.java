package poolweb.data.proxy;

import poolweb.data.dao.UserDAO;
import poolweb.data.impl.AnswerImpl;
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
    public void setID(int ID) {
        super.setID(ID);
        this.dirty = true;
    }

    @Override
    public void setQuestionID(int id) {
        super.setQuestionID(id);
        this.dirty = true;
    }

    @Override
    public void setAnswer(String answer){
        super.setAnswer(answer);
        this.dirty = true;
    }

    public void setQuestionKey(int question_key){
        this.question_key = question_key;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }
}
