package poolweb.data.dao;

import poolweb.data.model.Answer;
import poolweb.data.model.Poll;
import poolweb.data.model.Question;
import poolweb.framework.data.DataException;

import java.sql.ResultSet;
import java.util.List;

public interface AnswerDAO {

    Answer createAnswer();

    Answer createAnswer(ResultSet rs) throws DataException;

    Answer getAnswerByID(int ID) throws DataException;

    Answer getAnswerByQuestionID(int ID) throws DataException;

    List<Answer> getAllAnswer() throws DataException;

}
