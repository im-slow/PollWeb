package poolweb.data.dao;

import poolweb.data.model.Question;
import poolweb.framework.data.DataException;

import java.sql.ResultSet;
import java.util.List;

public interface QuestionDAO {

    public List<Question> getQuestions() throws DataException;

    public Question createQuestion() throws DataException;

    public Question createQuestion(ResultSet res) throws DataException;

    public Question getQuestionByID(int ID) throws DataException;

    public List<Question> getQuestionByPollID(int IDpoll) throws DataException;

    public void storeQuestion(Question q) throws DataException;

    public void removeQuestion(int id) throws DataException;

    int getCount() throws DataException;

}
