package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.*;
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
            if (poll != null && poll.getStatePoll() == 1) {
                if(!poll.getOpenstatus()) { // se il sondaggio è chiuso
                    request.setAttribute("message", "Questo sondaggio è privato, non puoi rispondere!");
                    request.setAttribute("submessage", "Effettua il login o contatta il responsabile: "+poll.getUser().getEmail());
                    action_error(request, response);
                } else {
                    request.setAttribute("page_title", "Rispondi al Sondaggio"); //Titolo da iniettare nel template con freeMarker
                    request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
                    List<Question> question = ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().getQuestionByPollID(poll.getID());
                    request.setAttribute("question", question);
                    request.setAttribute("poll", poll);
                    res.activate("new_answer.ftl", request, response);
                }
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
                        if (poll.getStatePoll() == 0) {
                            request.setAttribute("page_title", "Aggiungi Domande");
                            request.setAttribute("question", q);
                            request.setAttribute("pollID", request.getParameter("id"));
                            res.activate("new_questions.ftl", request, response);
                        } else {
                            request.setAttribute("page_title", "Gestisci Sondaggio");
                            request.setAttribute("question", q);
                            request.setAttribute("poll", poll);
                            res.activate("menage_poll.ftl", request, response);
                        }
                    } else {
                        if (poll.getStatePoll() == 1) {
                            if (!poll.getOpenstatus()) {
                                try { // va in eccezione se non trova un instanza che associa l'utente al sondaggio
                                    Instance ist = ((PoolWebDataLayer) request.getAttribute("datalayer")).getInstanceDAO().getInstanceByOKey(user, poll);
                                    if (ist != null) {
                                        if (ist.getUserStatus()) {
                                            request.setAttribute("page_title", "Rispondi");
                                            request.setAttribute("poll", poll);
                                            request.setAttribute("question", q);
                                            res.activate("new_answer.ftl", request, response);
                                        } else {
                                            request.setAttribute("message", "Hai già risposto a questo sondaggio!");
                                            action_error(request, response);
                                        }
                                    } else {
                                        request.setAttribute("message", "Il sondaggio è privato");
                                        request.setAttribute("submessage", "Non hai il permesso per rispondere");
                                        action_error(request, response);
                                    }
                                } catch (DataException e){
                                    request.setAttribute("message", "Questo sondaggio è privato, non puoi rispondere!");
                                    request.setAttribute("submessage", "Contatta il responsabile: " + poll.getUser().getEmail());
                                    action_error(request, response);
                                }
                            } else {
                                request.setAttribute("page_title", "Rispondi");
                                request.setAttribute("poll", poll);
                                request.setAttribute("question", q);
                                res.activate("new_answer.ftl", request, response);
                            }
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
            e.printStackTrace();
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