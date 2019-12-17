package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Poll;
import poolweb.data.model.Question;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.framework.result.FailureResult;
import poolweb.framework.result.SplitSlashesFmkExt;
import poolweb.framework.result.TemplateManagerException;
import poolweb.framework.result.TemplateResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;
import static poolweb.framework.security.SecurityLayer.checkSession;

public class CreateAnswer extends PoolWebBaseController {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession s = checkSession(request);
            if (s != null) {
                action_user(request, response, s);
            } else {
                action_answer(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_answer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            TemplateResult res = new TemplateResult(getServletContext());
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            Poll poll = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getPollByID(parseInt(request.getParameter("id")));
            System.out.println(parseInt(request.getParameter("id")));
            System.out.println(poll.getPollstatus());
            if (poll != null) {
                List<Question> question = ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().getQuestionByPollID(poll.getID());
                request.setAttribute("page_title", "Rispondi al Sondaggio"); //Titolo da iniettare nel template con freeMarker
                request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
                request.setAttribute("question", question);
                request.setAttribute("poll", poll);
                res.activate("new_answer.ftl", request, response);
            } else {
                request.setAttribute("message", "Unable to load Users");
                action_error(request, response);
            }
        } catch (DataException ex) {
            request.setAttribute("message", "Data access exception: " + ex.getMessage());
            action_error(request, response);
        } catch (TemplateManagerException e) {
            e.printStackTrace();
        }
    }

    private void action_user(HttpServletRequest request, HttpServletResponse response, HttpSession s) {
        try {
            TemplateResult res = new TemplateResult(getServletContext());
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            Poll poll = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getPollByID(parseInt(request.getParameter("id")));
            User user = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser((int) s.getAttribute("userid"));
            if (poll != null) {
                List<Question> q = ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().getQuestionByPollID(poll.getID());
                if (poll.getUser().getID() == user.getID()) {
                    if (user != null  && !poll.getOpenstatus()) {
                        request.setAttribute("page_title", "Aggiungi Domande");
                        request.setAttribute("question", q);
                        request.setAttribute("pollID", request.getParameter("id"));
                        res.activate("new_questions.ftl", request, response);
                    } else if (poll.getOpenstatus()) {
                        request.setAttribute("page_title", "Rispondi");
                        request.setAttribute("poll", poll);
                        request.setAttribute("question", q);
                        res.activate("new_answer.ftl", request, response);
                    }
                } else {
                    request.setAttribute("message", "Non sei autorizzato a modificare questo sondaggio");
                    action_error(request, response);
                }
            } else {
                request.setAttribute("message", "Ops.. Il sondaggio non è presente nel sistema");
                action_error(request, response);
            }
        } catch (DataException | TemplateManagerException e) {
            request.setAttribute("message", "C'e stato un problema nel recupero del sondaggio");
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
