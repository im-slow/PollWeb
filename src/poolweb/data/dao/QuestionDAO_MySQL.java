package poolweb.data.dao;

import poolweb.data.model.Question;
import poolweb.data.proxy.QuestionProxy;
import poolweb.data.proxy.UserProxy;
import poolweb.framework.data.DAO;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO_MySQL extends DAO implements QuestionDAO {

    private final String SELECT_ALL_QUESTION = "SELECT id FROM question";
    private final String SELECT_QUESTION_BY_ID = "SELECT * FROM question WHERE id = ?";
    private final String SELECT_QUESTION_BY_IDPOLL = "SELECT * FROM question WHERE IDpoll=?";

    private PreparedStatement allQuestion;
    private PreparedStatement questionByID;
    private PreparedStatement questionByIDpoll;

    public QuestionDAO_MySQL(DataLayer d) { super(d);}

    @Override
    public void init() throws DataException {
        try {
            super.init();
            allQuestion = connection.prepareStatement(SELECT_ALL_QUESTION);
            questionByID = connection.prepareStatement(SELECT_QUESTION_BY_ID);
            questionByIDpoll = connection.prepareStatement(SELECT_QUESTION_BY_IDPOLL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            allQuestion.close();
            questionByID.close();
            questionByIDpoll.close();
        } catch (SQLException ex){
            //
        }
        super.destroy();
    }

    @Override
    public Question getQuestionByID(int ID) throws DataException {
        try {
            questionByID.setInt(1, ID);
            try (ResultSet rs = questionByID.executeQuery()) {
                if (rs.next()) {
                    return createQuestion(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load Question by ID", ex);
        }
        return null;
    }

    @Override
    public List<Question> getQuestionByPollID(int IDpoll) throws DataException {
        List<Question> result = new ArrayList<>();
        try {
            questionByIDpoll.setInt(1, IDpoll);
            try (ResultSet rs = questionByIDpoll.executeQuery()) {
                while (rs.next()) {
                    result.add(createQuestion(rs));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load poll by PollID", ex);
        }
        return result;
    }

    @Override
    public List<Question> getQuestions() throws DataException {
        List<Question> result = new ArrayList<>();
        try {
            try (ResultSet rs = allQuestion.executeQuery()) {
                while (rs.next()) {
                    result.add(getQuestionByID(rs.getInt("ID")));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load a list of Question", ex);
        }
        return result;
    }

    @Override
    public QuestionProxy createQuestion() {
        return new QuestionProxy(getDataLayer());
    }

    @Override
    public QuestionProxy createQuestion(ResultSet rs) throws DataException {
        try {
            QuestionProxy a = createQuestion();
            a.setID(rs.getInt("ID"));
            a.setPositionNumber(rs.getInt("positionNumber"));
            a.setCode(rs.getString("uniqueCode"));
            a.setQuestionText(rs.getString("questionText"));
            a.setNote(rs.getString("note"));
            a.setMandatory(rs.getBoolean("mandatory"));
            a.setQuestionType(Question.QuestionType.valueOf(rs.getString("questionType")));
            a.setMinimum(rs.getString("minimum"));
            a.setMinimum(rs.getString("maximum"));
            a.setQAnswer(rs.getString("qAnswer"));
            a.setPollKey(rs.getInt("IDpoll"));
            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create Question object from ResultSet", ex);
        }
    }

}
