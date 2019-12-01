package poolweb.data.proxy;

import poolweb.data.dao.PollDAO;
import poolweb.data.impl.QuestionImpl;
import poolweb.data.model.Poll;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class QuestionProxy extends QuestionImpl {

    protected boolean dirty;
    protected int poll_key = 0;

    protected DataLayer dataLayer;

    public QuestionProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.dirty = false;
        this.poll_key = 0;
    }

    @Override
    public Poll getPoll() {
        if (super.getPoll() == null && poll_key > 0) {
            try {
                super.setPoll(((PollDAO) dataLayer.getDAO(Poll.class)).getPollByID(poll_key));
            } catch (DataException ex) {
                Logger.getLogger(UserProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return super.getPoll();
    }

    @Override
    public void setID(int ID) {
        super.setID(ID);
        this.dirty = true;
    }

    @Override
    public void setCode(String code){
        super.setCode(code);
        this.dirty = true;
    }

    @Override
    public void setQuestionText(String text){
        super.setQuestionText(text);
        this.dirty = true;
    }

    @Override
    public void setNote(String note){
        super.setNote(note);
        this.dirty = true;
    }

    @Override
    public void setQuestionType(QuestionType type){
        super.setQuestionType(type);
        this.dirty = true;
    }

    @Override
    public void setMandatory(boolean mandatory){
        super.setMandatory(mandatory);
        this.dirty = true;
    }

    @Override
    public void setPositionNumber(int position){
        super.setPositionNumber(position);
        this.dirty = true;
    }

    @Override
    public void setMinimum(String min){
        super.setMinimum(min);
        this.dirty = true;
    }

    @Override
    public void setMaximum(String max){
        super.setMaximum(max);
        this.dirty = true;
    }

    @Override
    public void setPoll(Poll poll){
        super.setPoll(poll);
        this.dirty = true;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setPollKey(int poll_key) {
        this.poll_key = poll_key;
        //resettiamo la cache del sondaggio
        super.setPoll(null);
    }
}
