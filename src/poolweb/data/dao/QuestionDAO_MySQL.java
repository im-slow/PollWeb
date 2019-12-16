package poolweb.data.dao;

import poolweb.data.model.Question;
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

public class QuestionDAO_MySQL extends DAO implements QuestionDAO {

    private final String SELECT_ALL_QUESTION = "SELECT id FROM question";
    private final String SELECT_QUESTION_BY_ID = "SELECT * FROM question WHERE id = ?";
    private final String SELECT_QUESTION_BY_IDPOLL = "SELECT * FROM question WHERE IDpoll=? ORDER BY positionNumber ASC";
    private final String INSERT_QUESTION = "INSERT INTO question (positionNumber, uniqueCode, questionText, note, mandatory, questionType, minimum, maximum, qAnswer, IDpoll) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_QUESTION = "UPDATE question SET positionNumber=?, uniqueCode=?, questionText=?, note=?, mandatory=?, questionType=?, minimum=?, maximum=?, qAnswer=?" +
            "WHERE ID=?";
    private final String REMOVE_QUESTION = "DELETE FROM question WHERE ID=?";

    private PreparedStatement allQuestion;
    private PreparedStatement questionByID;
    private PreparedStatement questionByIDpoll;
    private PreparedStatement insertQuestion;
    private PreparedStatement updateQuestion;
    private PreparedStatement removeQuestion;

    public QuestionDAO_MySQL(DataLayer d) { super(d);}

    @Override
    public void init() throws DataException {
        try {
            super.init();
            allQuestion = connection.prepareStatement(SELECT_ALL_QUESTION);
            questionByID = connection.prepareStatement(SELECT_QUESTION_BY_ID);
            questionByIDpoll = connection.prepareStatement(SELECT_QUESTION_BY_IDPOLL);
            insertQuestion = connection.prepareStatement(INSERT_QUESTION, Statement.RETURN_GENERATED_KEYS);
            updateQuestion = connection.prepareStatement(UPDATE_QUESTION);
            removeQuestion = connection.prepareStatement(REMOVE_QUESTION);
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
            insertQuestion.close();
            updateQuestion.close();
            removeQuestion.close();
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
    public void storeQuestion(Question question) throws DataException {
        int id = question.getID(); //if u want to check Poll's owner
        try {
            if (question.getID() > 0) {
                if (question instanceof QuestionProxy && !((QuestionProxy) question).isDirty()) {
                    return;
                }
                //here is for the update of the all parametes, for specific update need to create new PreparedStatement
                updateQuestion.setInt(1, question.getPosition());
                updateQuestion.setString(2, question.getCode());
                updateQuestion.setString(3, question.getQuestionText());
                updateQuestion.setString(4, question.getNote());
                updateQuestion.setBoolean(5, question.getMandatory());
                updateQuestion.setString(6, question.getQuestionType().toString());
                updateQuestion.setString(7, question.getMinimum());
                updateQuestion.setString(8, question.getMaximum());
                updateQuestion.setString(9, parserAnswer(question.getQAnswer()));
                updateQuestion.setInt(10, question.getID());
                updateQuestion.executeUpdate();
            } else {
                insertQuestion.setInt(1, question.getPosition());
                insertQuestion.setString(2, question.getCode());
                insertQuestion.setString(3, question.getQuestionText());
                insertQuestion.setString(4, question.getNote());
                insertQuestion.setBoolean(5, question.getMandatory());
                insertQuestion.setString(6, question.getQuestionType().toString());
                insertQuestion.setString(7, question.getMinimum());
                insertQuestion.setString(8, question.getMaximum());
                insertQuestion.setString(9, parserAnswer(question.getQAnswer()));
                insertQuestion.setInt(10, 3);
                if (insertQuestion.executeUpdate() == 1) {
                    try (ResultSet rs = insertQuestion.getGeneratedKeys()) {
                        if (rs.next()) {
                            id = rs.getInt(1);
                        }
                    }
                    question.setID(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeQuestion(int id) throws DataException {
        try {
            removeQuestion.setInt(1, id);
            removeQuestion.execute();
        } catch (SQLException ex) {
            throw new DataException("Unable to remove Question", ex);
        }
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
            a.setPosition(rs.getInt("positionNumber"));
            a.setCode(rs.getString("uniqueCode"));
            a.setQuestionText(rs.getString("questionText"));
            a.setNote(rs.getString("note"));
            a.setMandatory(rs.getBoolean("mandatory"));
            a.setQuestionType(Question.QuestionType.valueOf(rs.getString("questionType")));
            a.setMinimum(rs.getString("minimum"));
            a.setMaximum(rs.getString("maximum"));
            a.setQAnswer(parserAnswer(rs.getString("qAnswer")));
            a.setPollKey(rs.getInt("IDpoll"));
            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create Question object from ResultSet", ex);
        }
    }

}
