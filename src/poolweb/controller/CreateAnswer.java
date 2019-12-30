package poolweb.controller;

import org.apache.commons.lang3.StringUtils;
import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.impl.UserImpl;
import poolweb.data.model.*;
import poolweb.data.proxy.UserProxy;
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
import static poolweb.util.ParserAnswer.randomQuestCode;

public class CreateAnswer extends PoolWebBaseController {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession s = checkSession(request);
            if (s != null) {
                if (request.getParameter("nome") != null) {
                    try {
                        Instance i;
                        User u;
                        Poll p;
                        Role r;
                        p = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getPollByID(parseInt(request.getParameter("IDpoll")));
                        u = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().createUser();
                        i = ((PoolWebDataLayer) request.getAttribute("datalayer")).getInstanceDAO().createInstance();
                        r = ((PoolWebDataLayer) request.getAttribute("datalayer")).getRoleDAO().getRoleByID(3);
                        if (u != null && i != null && r != null && p != null) {
                            u.setEmail(request.getParameter("nome"));
                            u.setName(StringUtils.substringBefore(request.getParameter("nome"), "@"));
                            u.setPassword(request.getParameter("password"));
                            u.setRole(r);
                            System.out.println(u.getRole()+" "+u.getID()+" "+u.getEmail());
                            ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().storeUser(u);
                            i.setUser(u);
                            i.setPoll(p);
                            i.setUserStatus(true);
                            ((PoolWebDataLayer) request.getAttribute("datalayer")).getInstanceDAO().storeInstance(i);
                            ((PoolWebDataLayer) request.getAttribute("datalayer")).getRoleDAO().storeUserRole(u);
                        } else {
                            System.out.println("non va");
                        }
                    } catch (DataException e){
                        e.printStackTrace();;
                    }
                }
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
            if (poll != null && poll.getPollstatus()) {
                request.setAttribute("page_title", "Rispondi al Sondaggio"); //Titolo da iniettare nel template con freeMarker
                request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
                List<Question> question = ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().getQuestionByPollID(poll.getID());
                request.setAttribute("question", question);
                request.setAttribute("poll", poll);
                res.activate("new_answer.ftl", request, response);
            } else {
                request.setAttribute("message", "Errore caricamento del sondaggio");
                request.setAttribute("submessage", "Riprova più tardi");
                action_error(request, response);
            }
        } catch (DataException ex) {
            request.setAttribute("message", "Errore caricamento del sondaggio");
            request.setAttribute("submessage", "Riprova più tardi");
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
            if (user != null) {
                if (poll != null) {
                    List<Question> q = ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().getQuestionByPollID(poll.getID());
                    if (user.getID() == poll.getUser().getID()) {
                        if (poll.getPollstatus()) {
                            request.setAttribute("page_title", "Gestisci Sondaggio");
                            request.setAttribute("question", q);
                            request.setAttribute("poll", poll);
                            res.activate("menage_poll.ftl", request, response);
                        } else {
                            request.setAttribute("page_title", "Aggiungi Domande");
                            request.setAttribute("question", q);
                            request.setAttribute("pollID", request.getParameter("id"));
                            res.activate("new_questions.ftl", request, response);
                        }
                    } else {
                        if (poll.getPollstatus()) {
                            request.setAttribute("page_title", "Rispondi");
                            request.setAttribute("poll", poll);
                            request.setAttribute("question", q);
                            res.activate("new_answer.ftl", request, response);
                        } else {
                            request.setAttribute("message", "Non hai il permesso per accedere a questa sezione");
                            action_error(request, response);
                        }
                    }
                } else {
                    request.setAttribute("message", "Errore caricamento sondaggio");
                    request.setAttribute("submessage", "Non siamo riusciti a recuperare il sondaggio");
                    action_error(request, response);
                }
            } else {
                request.setAttribute("message", "Sessione scaduta, riprova");
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
