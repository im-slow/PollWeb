package poolweb.data.dao;

import poolweb.data.model.*;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;
import javax.sql.DataSource;
import java.sql.SQLException;

public class PoolWebDataLayer extends DataLayer {

    public PoolWebDataLayer(DataSource dataSource) throws SQLException {
        super(dataSource);
    }

    @Override
    public void init() throws DataException {
        registerDAO(User.class, new UserDAO_MySQL(this));
        registerDAO(Poll.class, new PollDAO_MySQL(this));
        registerDAO(Question.class, new QuestionDAO_MySQL(this));
        registerDAO(Answer.class, new AnswerDAO_MySQL(this));
        registerDAO(Role.class, new RoleDAO_MySQL(this));
    }

    public UserDAO getUserDAO() { return ((UserDAO) getDAO(User.class)); }
    public PollDAO getPollDAO() { return ((PollDAO) getDAO(Poll.class)); }
    public QuestionDAO getQuestionDAO() { return ((QuestionDAO) getDAO(Question.class)); }
    public AnswerDAO getAnswerDAO() { return  ((AnswerDAO) getDAO(Answer.class)); }
    public RoleDAO getRoleDAO() { return ((RoleDAO) getDAO(Role.class)); }
}
