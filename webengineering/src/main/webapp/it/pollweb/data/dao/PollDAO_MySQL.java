/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pollweb.data.dao;

import framework.data.DAO;
import framework.data.DataException;
import framework.data.DataLayer;
import it.pollweb.data.model.Poll;
import it.pollweb.data.model.Question;
import it.pollweb.data.proxy.PollProxy;

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
public class PollDAO_MySQL extends DAO implements PollDAO{

    private PreparedStatement sPollByID, sPolls, sPollByUser;
    private PreparedStatement iPoll, uPoll, dPoll;

    public PollDAO_MySQL(DataLayer d) {
        super(d);
    }

    @Override
    public void init() throws DataException {
        try {
            super.init();

            sPollByID = connection.prepareStatement("SELECT * FROM poll WHERE ID=?");
            sPollByUser = connection.prepareStatement("SELECT * FROM poll join utente WHERE email=?");
            sPolls = connection.prepareStatement("SELECT ID FROM poll");

            iPoll = connection.prepareStatement("INSERT INTO poll (idNum,title,openText,closeText,openPoll,statePoll,URLPoll,IDuser) VALUES(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            uPoll = connection.prepareStatement("UPDATE poll SET idNum=?,title=?,openText=?,closeText=?,openPoll=?,statePoll=?,URLPoll=?,IDuser=? WHERE ID=?");
            dPoll = connection.prepareStatement("DELETE FROM poll WHERE ID=?");

            //sResponsible = connection.prepareStatement("SELECT user.nome FROM user,poll WHERE user.ID=poll.IDuser");
            //sQuestions = connection.prepareStatement("SELECT question.* FROM question,poll WHERE question.IDpoll=poll.ID");

        } catch (SQLException ex) { throw new DataException("Error initializing PollWeb data layer", ex); }
    }

    @Override
    public void destroy() throws DataException {
        try {
            sPollByID.close();
            sPollByUser.close();
            sPolls.close();
            iPoll.close();
            uPoll.close();
            dPoll.close();

        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }

    @Override
    public PollProxy createPoll() {
        return new PollProxy(getDataLayer());
    }

    @Override
    public Poll createPoll(ResultSet rs) throws DataException {
        try {
            PollProxy p = createPoll();
            p.setKey(rs.getInt("ID"));
            p.setIDNum(rs.getInt("idNum"));
            p.setOpenPoll(rs.getBoolean("openPool"));
            p.setOpenText(rs.getString("openText"));
            p.setCloseText(rs.getString("closeText"));
            p.setQuestions(new ArrayList<Question>());
            p.setStatePoll(rs.getBoolean("statePoll"));
            p.setTitle(rs.getString("title"));
            p.setURLPoll(rs.getString("URLPoll"));
            p.setResponsibleKey(rs.getInt("IDuser"));

            return p;
        } catch (SQLException ex) {
            throw new DataException("Unable to create poll object form ResultSet", ex);
        }
    }

    @Override
    public Poll getPoll(int poll_key) throws DataException {
        try {
            sPollByID.setInt(1, poll_key);
            try (ResultSet rs = sPollByID.executeQuery()) {
                if (rs.next()) {
                    return createPoll(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load poll by ID", ex);
        }
        return null;
    }

    @Override
    public Poll getPoll(String email) throws DataException {
        try {
            sPollByUser.setString(1, email);
            try (ResultSet rs = sPollByUser.executeQuery()) {
                if (rs.next()) {
                    return createPoll(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load poll by email", ex);
        }
        return null;
    }

    @Override
    public List<Poll> getPoll() throws DataException {
        List<Poll> result = new ArrayList<Poll>();
        try (ResultSet rs = sPolls.executeQuery()) {
            while(rs.next()) {
                result.add(getPoll(rs.getInt("ID")));
            }
            return result;
        } catch (SQLException ex){
            throw new DataException("Unable to load polls", ex);
        }
    }

    @Override
    public void storePoll(Poll poll) throws DataException {
        int key = poll.getKey();
        try {
            if (poll.getKey() > 0) { //update
                //non facciamo nulla se l'oggetto è un proxy e indica di non aver subito modifiche
                //do not store the object if it is a proxy and does not indicate any modification
                if (poll instanceof PollProxy && !((PollProxy) poll).isDirty()) {
                    return;
                }
                uPoll.setInt(1, poll.getIDNum());
                uPoll.setString(2, poll.getTitle());
                uPoll.setString(3, poll.getOpenText());
                uPoll.setString(4, poll.getCloseText());
                uPoll.setBoolean(5, poll.getOpenPoll());
                uPoll.setBoolean(6, poll.getStatePoll());
                uPoll.setString(7, poll.getURLPoll());
                if (poll.getResponsible() != null) {
                    uPoll.setInt(8, poll.getResponsible().getKey());
                } else {
                    uPoll.setNull(8, java.sql.Types.INTEGER);
                }
                uPoll.setInt(6, poll.getKey());
                uPoll.executeUpdate();
            } else { //insert
                iPoll.setInt(1, poll.getIDNum());
                iPoll.setString(2, poll.getTitle());
                iPoll.setString(3, poll.getOpenText());
                iPoll.setString(4, poll.getCloseText());
                iPoll.setBoolean(5, poll.getOpenPoll());
                iPoll.setBoolean(6, poll.getStatePoll());
                iPoll.setString(7, poll.getURLPoll());
                if (poll.getResponsible() != null) {
                    iPoll.setInt(8, poll.getResponsible().getKey());
                } else {
                    iPoll.setNull(8, java.sql.Types.INTEGER);
                }
                if (iPoll.executeUpdate() == 1) {
                    //per leggere la chiave generata dal database
                    //per il record appena inserito, usiamo il metodo
                    //getGeneratedKeys sullo statement.
                    //to read the generated record key from the database
                    //we use the getGeneratedKeys method on the same statement
                    try (ResultSet keys = iPoll.getGeneratedKeys()) {
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
                    poll.setKey(key);
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
            if (poll instanceof PollProxy) {
                ((PollProxy) poll).setDirty(false);
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to store poll", ex);
        }
    }
    
}
