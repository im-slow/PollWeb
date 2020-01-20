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

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static poolweb.framework.security.SecurityLayer.checkSession;

@WebServlet(name = "ShowAnswers")
public class ShowAnswers extends PoolWebBaseController {

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
            List<Question> qst = ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().getQuestionByPollID(Integer.parseInt(request.getParameter("id")));
            List<Answer> a = new ArrayList<>();
            for (Question q: qst) {
                List<Answer> answerQ = ((PoolWebDataLayer) request.getAttribute("datalayer")).getAnswerDAO().getAllAnswerByQuestionID(q.getID());
                for(Answer ans: answerQ){
                    a.add(ans);
                }
            }
            if (us != null && pl != null && us.getID() == pl.getUser().getID()) {
                request.setAttribute("poll", pl);
                request.setAttribute("answer", a);
                request.setAttribute("page_title", "Risposte Sondaggio"); //Titolo da iniettare nel template con freeMarker
                TemplateResult res = new TemplateResult(getServletContext());
                request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
                res.activate("show_answers.html.ftl", request, response);
            } else {
                request.setAttribute("message", "Non sei autorizzato a visualizzare le risposte di questo sondaggio");
            }
        } catch (DataException e) {
            request.setAttribute("message", "Errore visualizzazione risposte del sondaggio");
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

}
