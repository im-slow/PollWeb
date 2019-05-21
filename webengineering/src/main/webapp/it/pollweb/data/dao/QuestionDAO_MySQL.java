/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.dao;

import it.pollweb.data.model.Question;
import it.pollweb.data.framework.data.DAO;
import it.pollweb.data.framework.data.DataException;
import it.pollweb.data.framework.data.DataLayer;
import it.pollweb.data.proxy.QuestionProxy;
import it.pollweb.data.proxy.UserProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andy4
 */
public class QuestionDAO_MySQL extends DAO implements QuestionDAO{

    private PreparedStatement sQuestions, sQuestionByID, sQuestionByPoll;
    private PreparedStatement iQuestion, uQuestion, dQuestion;
    private PreparedStatement uQuestionPosition;

    public QuestionDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();

            sQuestionByID = connection.prepareStatement("SELECT * FROM question WHERE ID=?");
            sQuestionByPoll = connection.prepareStatement("SELECT * FROM question WHERE IDpoll=?");
            sQuestions = connection.prepareStatement("SELECT ID FROM question");

            iQuestion = connection.prepareStatement("INSERT INTO question (positionNumber, uniqueCode, questionText, note, mandatory, questionType, minimum, maximum, questionOption) VALUES(?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            uQuestion = connection.prepareStatement("UPDATE question SET positionNumber=?, uniqueCode=?, questionText=?, note=?, mandatory=?, questionType=?, minimum=?, maximum=?, questionOption=? WHERE ID=?");
            dQuestion = connection.prepareStatement("DELETE FROM question WHERE ID=?");

            uQuestionPosition = connection.prepareStatement("UPDATE appartiene SET positionNumber=? WHERE ID=?");
        } catch (SQLException ex) {
            throw new DataException("Error initializing newspaper data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            sQuestionByID.close();
            sQuestionByPoll.close();
            sQuestions.close();
            iQuestion.close();
            uQuestion.close();
            dQuestion.close();
            uQuestion.close();
            uQuestionPosition.close();

        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public QuestionProxy createQuestion() {
        return new QuestionProxy(getDataLayer());
    }

    @Override
    public QuestionProxy createQuestion(ResultSet rs) throws DataException {
        try {
            QuestionProxy a = createQuestion();
            a.setKey(rs.getInt("ID"));
            a.setPositionNumber(rs.getInt("position"));
            a.setCode(rs.getString("code"));
            a.setQuestionText(rs.getString("text"));
            a.setNote(rs.getString("note"));
            a.setMandatory(rs.getBoolean("mandatory"));
            a.setQuestionType((Question.QuestionType) rs.getObject("type"));
            a.setMaximum(rs.getString("max"));
            a.setMinimum(rs.getString("min"));
            a.setOption(rs.getString("option"));
            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create author object form ResultSet", ex);
        }
    }

    @Override
    public QuestionProxy getQuestion(int question_key) throws DataException {
        try {
            sQuestionByID.setInt(1, question_key);
            try (ResultSet rs = sQuestionByID.executeQuery()) {
                if (rs.next()) {
                    return createQuestion(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load question by ID", ex);
        }
        return null;
    }

    @Override
    public List<Question> getQuestion() throws DataException {
        List<Question> result = new ArrayList();

        try (ResultSet rs = sQuestions.executeQuery()) {
            while (rs.next()) {
                result.add(getQuestion(rs.getInt("ID")));
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load questions", ex);
        }
        return result;
    }
    
}
