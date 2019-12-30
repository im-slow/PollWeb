package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Answer;
import poolweb.data.model.Question;
import poolweb.framework.data.DataException;
import poolweb.framework.result.FailureResult;
import poolweb.framework.result.SplitSlashesFmkExt;
import poolweb.framework.result.TemplateManagerException;
import poolweb.framework.result.TemplateResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InsertAnswer extends PoolWebBaseController {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            if (!request.getParameterMap().isEmpty()) {
                action_answer(request, response);
            } else {
                action_error(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_answer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            if (request.getParameterMap() != null) {
                List<Question> qst = ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().getQuestionByPollID(Integer.parseInt(request.getParameter("idpoll")));
                //create new Answer object for each question
                if (qst != null) {
                    for (Question q: qst) {
                        int position = q.getPosition();
                        switch (q.getQuestionType()) {
                            case DATE:
                                break;
                            case LONGTEXT:

                        }
                        // new Answer= AnswerDAO_MySQL
                        Answer a = ((PoolWebDataLayer) request.getAttribute("datalayer")).getAnswerDAO().createAnswer();
                        if (q.getMandatory()) {
                            if (request.getAttribute("answer"+position) != null) {
                                a.setAnswer((String) request.getAttribute("answer"+position));
                            } else {
                                request.setAttribute("message", "Errore dati di compilazione");
                                request.setAttribute("submessage", "E' necessario rispondere alle domande obbligatorie");
                                action_error(request, response);
                            }
                        }

                    }
                }
            } else {
                request.setAttribute("message", "Cannot update article: insufficient parameters");
                action_error(request, response);

            }
        } catch (NumberFormatException | DataException ex) {
            request.setAttribute("message", "Errore dati in input");
            action_error(request, response);
        }
    }

    private void action_write(HttpServletRequest request, HttpServletResponse response) {
        try {
            TemplateResult res = new TemplateResult(getServletContext());
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            request.setAttribute("page_title", "Inserimento Riuscito");
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            res.activate("success.ftl", request, response);
        } catch (TemplateManagerException e) {
            e.printStackTrace();
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
