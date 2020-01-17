package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Answer;
import poolweb.data.model.Poll;
import poolweb.data.model.Question;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.framework.result.FailureResult;
import poolweb.framework.result.SplitSlashesFmkExt;
import poolweb.framework.result.TemplateManagerException;
import poolweb.framework.result.TemplateResult;
import poolweb.util.GenerateCSV;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.tree.TreeCellEditor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static poolweb.framework.security.SecurityLayer.checkSession;

@WebServlet(name = "GetAnswer")
public class GetAnswer extends PoolWebBaseController {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        /* Possibile Implementare Controlli sui dati in input nella request, come validate, sanitaze o controlli su input mancanti*/
        try {
            HttpSession s = checkSession(request);
            if (s != null && request.getParameter("id") != null) {
                action_user(request, response, s);
            } else {
                request.setAttribute("message", "Dati non sufficienti");
                action_error(request, response);
            }
        } catch (IOException ioexc) {
            ioexc.printStackTrace();
        }
    }

    private void action_user(HttpServletRequest request, HttpServletResponse response, HttpSession s) throws IOException, ServletException {
        try {
            User us = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser((int) s.getAttribute("userid"));
            Poll pl = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getPollByID(Integer.parseInt(request.getParameter("id")));
            List<Question> qs = ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().getQuestionByPollID(pl.getID());
            if (us != null && pl != null && qs != null &&us.getID() == pl.getUser().getID()) {
                // Creazione di una lista di lista di stringhe: domanda, risposta
                List<List<String>> result = new ArrayList<List<String>>();
                for (Question q : qs) {
                    List<Answer> aList = ((PoolWebDataLayer) request.getAttribute("datalayer")).getAnswerDAO().getAllAnswerByQuestionID(q.getID());
                    for (Answer a: aList) {
                        List<String> answerString = new ArrayList<>();
                        answerString.add(q.getQuestionText());
                        answerString.add(a.getAnswer());
                        result.add(answerString);
                    }
                }
                if (!result.isEmpty()) {
                    // Genera CSV con le risposte al sondaggio
                    GenerateCSV.newCSV(result, pl);
                    action_write(request, response);
                } else {
                    request.setAttribute("message", "Il CSV non è stato  esportato");
                    request.setAttribute("submessage", "Non sono presenti domande");
                    action_error(request, response);
                }
            } else {
                request.setAttribute("message", "Non sei autorizzato a esportare le risposte");
            }
        } catch (DataException e) {
            request.setAttribute("message", "Errore modifica stato del sondaggio");
            action_error(request, response);
            e.printStackTrace();
        } catch (TemplateManagerException e) {
            request.setAttribute("message", "Errore generico");
            action_error(request, response);
        }
    }

    //Necessario per gestire le return di errori
    private void action_error(HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        } else {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }

    //Necessario per gestire le return di errori
    private void action_write(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {
        request.setAttribute("page_title", "CSV salvato");
        TemplateResult res = new TemplateResult(getServletContext());
        request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
        request.setAttribute("message", "Il CSV è stato salvato con successo");
        res.activate("success.ftl", request, response);
    }

}
