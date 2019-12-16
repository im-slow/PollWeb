package poolweb.data.dao;

import poolweb.data.model.Answer;
import poolweb.data.model.Poll;
import poolweb.data.model.Question;
import poolweb.data.proxy.AnswerProxy;
import poolweb.data.proxy.QuestionProxy;
import poolweb.framework.data.DAO;
import poolweb.framework.data.DataException;
import poolweb.framework.data.DataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static poolweb.util.ParserAnswer.parserAnswer;

public class AnswerDAO_MySQL extends DAO implements AnswerDAO {

    private final String SELECT_ALL_ANSWER_ID = "SELECT ID FROM answer";
    private final String SELECT_ANSWER_BY_ID = "SELECT * FROM answer WHERE ID = ?";
    private final String SELECT_ANSWER_BY_QUESTION_ID = "SELECT * FROM answer WHERE IDquestion = ?";
    private final String INSERT_ANSWER = "INSERT INTO answer (answer, IDquestion)" +
            "VALUES (?, ?)";
    private final String UPDATE_ANSWER = "UPDATE answer SET answer=?, IDquestion=?" +
            "WHERE ID=?";

    private PreparedStatement allAnswer;
    private PreparedStatement answerByID;
    private PreparedStatement answerByQuestionID;
    private PreparedStatement insertAnswer;
    private PreparedStatement updateAnswer;

    public AnswerDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();

            super.init();
            allAnswer = connection.prepareStatement(SELECT_ALL_ANSWER_ID);
            answerByID = connection.prepareStatement(SELECT_ANSWER_BY_ID);
            answerByQuestionID = connection.prepareStatement(SELECT_ANSWER_BY_QUESTION_ID);

        } catch (SQLException ex) {
            throw new DataException("Error initializing newspaper data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            allAnswer.close();
            answerByID.close();
            answerByQuestionID.close();

        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public AnswerProxy createAnswer() {
        return new AnswerProxy(getDataLayer());
    }

    @Override
    public AnswerProxy createAnswer(ResultSet rs) throws DataException {
        try{
            AnswerProxy a = createAnswer();
            a.setID(rs.getInt("ID"));
            a.setAnswer(rs.getString("answer"));
            a.setQuestionKey(rs.getInt("IDquestion"));
            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create answer object form ResultSet", ex);
        }
    }

    @Override
    public Answer getAnswerByID(int ID) throws DataException {
        try {
            answerByID.setInt(1, ID);
            try (ResultSet rs = answerByID.executeQuery()) {
                if (rs.next()) {
                    return createAnswer(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load answer by ID", ex);
        }
        return null;
    }

    @Override
    public Answer getAnswerByQuestionID(int questionID) throws DataException {
        try {
            answerByQuestionID.setInt(1, questionID);
            try (ResultSet rs = answerByQuestionID.executeQuery()) {
                if (rs.next()) {
                    return createAnswer(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load answer by questionID", ex);
        }
        return null;
    }


    @Override
    public List<Answer> getAllAnswerByQuestionID() throws DataException {
        List<Answer> result = new ArrayList<Answer>();
        try (ResultSet rs = answerByQuestionID.executeQuery()) {
            while(rs.next()) {
                result.add(getAnswerByQuestionID(rs.getInt("ID")));
            }
            return result;
        } catch (SQLException ex) {
            throw new DataException("Unable to load answers by questionID", ex);
        }
    }

    @Override
    public List<Answer> getAllAnswer() throws DataException {
        List<Answer> result = new ArrayList<Answer>();
        try (ResultSet rs = allAnswer.executeQuery()) {
            while(rs.next()) {
                result.add(getAnswerByID(rs.getInt("ID")));
            }
            return result;
        } catch (SQLException ex) {
            throw new DataException("Unable to load answers by questionID", ex);
        }
    }

    @Override
    public void storeAnswer(Answer answer) throws DataException {
        int id = answer.getID(); //if u want to check Poll's owner
        try {
            if (answer.getID() > 0) {
                if (answer instanceof AnswerProxy && ((AnswerProxy) answer).isDirty()) {
                    return;
                }
                //here is for the update of the all parametes, for specific update need to create new PreparedStatement
                updateAnswer.setString(1, answer.getAnswer());
            } else {
                insertAnswer.setString(1, answer.getAnswer());
                insertAnswer.setInt(2, 3);
                if (insertAnswer.executeUpdate() == 1) {
                    try (ResultSet rs = insertAnswer.getGeneratedKeys()) {
                        if (rs.next()) {
                            id = rs.getInt(1);
                        }
                    }
                    answer.setID(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

