package it.pollweb.data.dao;

import framework.data.DataException;
import framework.data.DataLayer;
import it.pollweb.data.model.*;

import javax.sql.DataSource;
import java.sql.SQLException;

public class PollWebDataLayer extends DataLayer {

    public PollWebDataLayer(DataSource datasource) throws SQLException {
        super(datasource);
    }

    @Override
    public void init() throws DataException {
        //registriamo i nostri dao
        //register our daos
        registerDAO(Answer.class, new AnswerDAO_MySQL(this));
        registerDAO(Instance.class, new InstanceDAO_MySQL(this));
        registerDAO(Poll.class, new PollDAO_MySQL(this));
        registerDAO(Question.class, new QuestionDAO_MySQL(this));
        registerDAO(User.class, new UserDAO_MySQL(this));
    }

    //helpers
    public AnswerDAO getAnswerDAO() {
        return (AnswerDAO) getDAO(Answer.class);
    }

    public InstanceDAO getInstanceDAO() {
        return (InstanceDAO) getDAO(Instance.class);
    }

    public PollDAO getPollDAO() {
        return (PollDAO) getDAO(Poll.class);
    }

    public QuestionDAO getQuestionDAO() {
        return (QuestionDAO) getDAO(Question.class);
    }

    public UserDAO getUserDAO() {
        return (UserDAO) getDAO(User.class);
    }
}
