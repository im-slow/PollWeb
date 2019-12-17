package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Poll;
import poolweb.data.model.Question;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.framework.result.FailureResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.lang.Integer.parseInt;
import static poolweb.framework.security.SecurityLayer.checkSession;
import static poolweb.util.ParserAnswer.randomQuestCode;

public class InsertQuestions extends PoolWebBaseController{

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession s = checkSession(request);
            if (!request.getParameterMap().isEmpty() && s != null) {
                action_poll(request, response, s);
            } else {
                action_error(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_poll(HttpServletRequest request, HttpServletResponse response, HttpSession s) throws IOException, ServletException {
        try {
            User user = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser((int) s.getAttribute("userid"));
            Poll poll = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getPollByID(parseInt(request.getParameter("idpoll")));
            if (user.getID() == poll.getUser().getID()) {
                if (request.getParameterMap() != null) {
                    for(int idx = 1; idx <= Integer.parseInt(request.getParameter("questnumbers")); idx++) {
                        // -- Here take the parameters for create a single Question Obj -- //
                        Question q;
                        q = ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().createQuestion();
                        if (q != null) {
                            if (request.getParameter("id" +idx) != null) {
                                q.setID(Integer.parseInt(request.getParameter("id" +idx)));
                            }
                            q.setQuestionText(request.getParameter("questname" +idx));
                            q.setNote(request.getParameter("info" +idx));
                            q.setMaximum(request.getParameter("max" +idx));
                            q.setMinimum(request.getParameter("min" +idx));
                            if (request.getParameter("domanda"+idx) != null ) {
                                q.setQAnswer(request.getParameterValues("domanda"+idx));
                            }
                            if (request.getParameter("isobbligo"+idx) != null) {
                                q.setMandatory(true);
                            }
                            q.setCode(randomQuestCode());
                            q.setPoll(poll);
                            q.setQuestionType(Question.QuestionType.valueOf(request.getParameter("questionType"+idx))); //take from code
                            q.setPosition(Integer.parseInt(request.getParameter("numberquest"+idx)));
                            ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().storeQuestion(q);
                        }
                    }
                    action_write(response);
                } else {
                    request.setAttribute("message", "Errore aggiornamento domande");
                    action_error(request, response);
                }
            } else {
                request.setAttribute("message", "Non sei autorizzato a modificare questo sondaggio");
                action_error(request, response);
            }
        } catch (DataException ex) {
            request.setAttribute("message", "Cannot create Question");
            action_error(request, response);
        }
    }

    private void action_write(HttpServletResponse response) throws IOException {
        response.sendRedirect("/inserimentoriuscito");
    }

    private void action_error(HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        } else {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }
}
