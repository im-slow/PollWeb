package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Question;
import poolweb.framework.data.DataException;
import poolweb.framework.result.FailureResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static poolweb.util.ParserAnswer.randomQuestCode;

public class InsertQuestions extends PoolWebBaseController{

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            if (!request.getParameterMap().isEmpty()) {
                action_poll(request, response);
            } else {
                action_error(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_poll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
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
                        q.setQAnswer(request.getParameterValues("domanda"+idx));
                        q.setCode(randomQuestCode());
//                        q.setPoll(); da gestire, recuperare l'ID del poll creato precedentemente
                        q.setQuestionType(Question.QuestionType.valueOf("MULTIPLECHOISE")); //take from code
                        q.setMandatory(Boolean.parseBoolean(request.getParameter("isobbligo"+idx)));
                        q.setMandatory(Boolean.parseBoolean(request.getParameter("isobbligo"+idx)));
                        q.setPosition(Integer.parseInt(request.getParameter("numberquest"+idx)));
                        ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().storeQuestion(q);
                    }
                }
                action_write(response);
            } else {
                request.setAttribute("message", "Cannot update article: insufficient parameters");
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

    //Necessario per gestire le return di errori
    private void action_error(HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        } else {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }
}
