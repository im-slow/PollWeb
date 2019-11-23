/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.dao;

import it.pollweb.data.model.Question;
import framework.data.DAO;
import framework.data.DataException;
import framework.data.DataLayer;
import it.pollweb.data.proxy.QuestionProxy;

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

            iQuestion = connection.prepareStatement("INSERT INTO question (positionNumber, uniqueCode, questionText, note, mandatory, questionType, minimum, maximum, questionOption, IDpoll) VALUES(?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            uQuestion = connection.prepareStatement("UPDATE question SET positionNumber=?, uniqueCode=?, questionText=?, note=?, mandatory=?, questionType=?, minimum=?, maximum=?, questionOption=?, IDpoll WHERE ID=?");
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
            a.setPollKey(rs.getInt("IDpoll"));
            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create question object form ResultSet", ex);
        }
    }

    @Override
    public QuestionProxy getQuestionByID(int question_key) throws DataException {
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
    public List<Question> getQuestionsByPoll(int poll_key) throws DataException {
        List<Question> result = new ArrayList();
        try {
            sQuestionByPoll.setInt(1, poll_key);
            try (ResultSet rs = sQuestionByPoll.executeQuery()) {
                while (rs.next()) {
                    result.add(getQuestionByID(rs.getInt("ID")));
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load questions", ex);
        }
        return result;
    }

    @Override
    public List<Question> getQuestions() throws DataException {
        List<Question> result = new ArrayList();

        try (ResultSet rs = sQuestions.executeQuery()) {
            while (rs.next()) {
                result.add(getQuestionByID(rs.getInt("ID")));
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load questions", ex);
        }
        return result;
    }

    @Override
    public void storeQuestion(Question question) throws DataException {
        int key = question.getKey();
        try {
            if (question.getKey() > 0) { //update
                //non facciamo nulla se l'oggetto è un proxy e indica di non aver subito modifiche
                //do not store the object if it is a proxy and does not indicate any modification
                if (question instanceof QuestionProxy && !((QuestionProxy) question).isDirty()) {
                    return;
                }
                uQuestion.setInt(1, question.getPositionNumber());
                uQuestion.setString(2, question.getCode());
                uQuestion.setString(3, question.getQuestionText());
                uQuestion.setString(4, question.getNote());
                uQuestion.setBoolean(5, question.getMandatory());
                uQuestion.setString(6, question.getQuestionType().name());
                uQuestion.setString(7, question.getMinimum());
                uQuestion.setString(8, question.getMaximum());
                uQuestion.setString(9, question.getOption());
                //uQuestion.setInt(10, question.get); TODO
                uQuestion.setInt(11, question.getKey());
                uQuestion.executeUpdate();
            } else { //insert
                iQuestion.setInt(1, question.getPositionNumber());
                iQuestion.setString(2, question.getCode());
                iQuestion.setString(3, question.getQuestionText());
                iQuestion.setString(4, question.getNote());
                iQuestion.setBoolean(5, question.getMandatory());
                iQuestion.setString(6, question.getQuestionType().name());
                iQuestion.setString(7, question.getMinimum());
                iQuestion.setString(8, question.getMaximum());
                iQuestion.setString(9, question.getOption());
                //iQuestion.setInt(10, question.); TODO

                if (iQuestion.executeUpdate() == 1) {
                    //per leggere la chiave generata dal database
                    //per il record appena inserito, usiamo il metodo
                    //getGeneratedKeys sullo statement.
                    //to read the generated record key from the database
                    //we use the getGeneratedKeys method on the same statement
                    try (ResultSet keys = iQuestion.getGeneratedKeys()) {
                        //il valore restituito è un ResultSet con un record
                        //per ciascuna chiave generata (uno solo nel nostro caso)
                        //the returned value is a ResultSet with a distinct record for
                        //each generated key (only one in our case)
                        if (keys.next()) {
                            //i campi del record sono le componenti della chiave
                            //(nel nostro caso, un solo intero)
                            //the record fields are the key componenets
                            //(a single integer in our case)
                            key = keys.getInt(1);
                        }
                    }
                    //aggiornaimo la chiave in caso di inserimento
                    //after an insert, uopdate the object key
                    question.setKey(key);
                }
            }

//            //se possibile, restituiamo l'oggetto appena inserito RICARICATO
//            //dal database tramite le API del modello. In tal
//            //modo terremo conto di ogni modifica apportata
//            //durante la fase di inserimento
//            //if possible, we return the just-inserted object RELOADED from the
//            //database through our API. In this way, the resulting
//            //object will ambed any data correction performed by
//            //the DBMS
//            if (key > 0) {
//                article.copyFrom(getResponsible(key));
//            }
            //se abbiamo un proxy, resettiamo il suo attributo dirty
            //if we have a proxy, reset its dirty attribute
            if (question instanceof QuestionProxy) {
                ((QuestionProxy) question).setDirty(false);
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to store poll", ex);
        }
    }
    
}
