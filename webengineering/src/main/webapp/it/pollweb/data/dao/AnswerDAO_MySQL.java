/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.dao;

import framework.data.DAO;
import framework.data.DataException;
import framework.data.DataLayer;
import it.pollweb.data.model.Answer;
import it.pollweb.data.model.Poll;
import it.pollweb.data.proxy.AnswerProxy;

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
public class AnswerDAO_MySQL extends DAO implements AnswerDAO{

    private PreparedStatement sAnswer, sAnswerByID, sAnswersByQuestion, sAnswers;
    private PreparedStatement iAnswer, uAnswer, dAnswer;

    public AnswerDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();

            sAnswer = connection.prepareStatement("SELECT * FROM answer");
            sAnswerByID = connection.prepareStatement("SELECT * FROM answer WHERE ID=?");
            sAnswersByQuestion = connection.prepareStatement("SELECT * FROM answer WHERE IDquestion=?"); //?
            sAnswers = connection.prepareStatement("SELECT ID FROM answer");

            iAnswer = connection.prepareStatement("INSERT INTO answer (answer,IDpoll) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            uAnswer = connection.prepareStatement("UPDATE answer SET answer=?,IDquestion=? WHERE ID=?");
            dAnswer = connection.prepareStatement("DELETE FROM answer WHERE ID=?");

        } catch (SQLException ex) {
            throw new DataException("Error initializing newspaper data layer", ex);
        }
    }

    @Override
    public void destroy() throws DataException {
        try {
            sAnswer.close();
            sAnswerByID.close();
            sAnswersByQuestion.close();
            sAnswers.close();

            iAnswer.close();
            uAnswer.close();
            dAnswer.close();

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
            a.setKey(rs.getInt("ID"));
            a.setAnswer(rs.getString("answer"));

            return a;
        } catch (SQLException ex) {
            throw new DataException("Unable to create answer object form ResultSet", ex);
        }
    }

    @Override
    public Answer getAnswerByID(int answer_key) throws DataException {
        try {
            sAnswerByID.setInt(1, answer_key);
            try (ResultSet rs = sAnswerByID.executeQuery()) {
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
    public List<Answer> getAnswers() throws DataException {
        List<Answer> result = new ArrayList<Answer>();
        try (ResultSet rs = sAnswers.executeQuery()) {
            while(rs.next()) {
                result.add(getAnswerByID(rs.getInt("ID")));
            }
            return result;
        } catch (SQLException ex) {
            throw new DataException("Unable to load answers", ex);
        }
    }

    @Override
    public List<Answer> getAnswersByQuestion(int question_key) throws DataException {
        List<Answer> result = new ArrayList<Answer>();
        try {
            sAnswersByQuestion.setInt(1, question_key);
            try (ResultSet rs = sAnswersByQuestion.executeQuery()) {
                while (rs.next()) {
                    result.add(getAnswerByID(rs.getInt("ID")));
                }
                return result;
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load answer by question", ex);
        }
    }


    @Override
    public void storeAnswer(Answer answer) throws DataException {
        /*
        int key = answer.getKey();
        try {
            if (answer.getKey() > 0) { //update
                //non facciamo nulla se l'oggetto è un proxy e indica di non aver subito modifiche
                //do not store the object if it is a proxy and does not indicate any modification
                if (answer instanceof AnswerProxy && !((AnswerProxy) answer).isDirty()) {
                    return;
                }
                uAnswer.setInt(1, answer.getKey());
                uAnswer.setString(2, answer.getAnswer());
                //uAnswer.setInt(3, );
                uAnswer.executeUpdate();
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
        */
    }

}

