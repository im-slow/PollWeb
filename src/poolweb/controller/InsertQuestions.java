package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.impl.PollImpl;
import poolweb.data.impl.QuestionImpl;
import poolweb.data.model.Question;
import poolweb.framework.data.DataException;
import poolweb.framework.result.FailureResult;
import poolweb.framework.result.SplitSlashesFmkExt;
import poolweb.framework.result.TemplateManagerException;
import poolweb.framework.result.TemplateResult;
import poolweb.util.ParserAnswer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeSet;

import static poolweb.util.ParserAnswer.parserAnswer;
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
                TreeSet<String> indexes = new TreeSet<>();
                Map<String, String[]> parmap = request.getParameterMap();
                for(Map.Entry<String, String[]> entry : parmap.entrySet()) {
                    indexes.add((entry.getKey().substring(entry.getKey().length() - 1)));
                }
                for(String idx: indexes) {
                    // -- Here take the parameters for create a single Question Obj -- //
                    Question q;
                    q = ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().createQuestion();
                    if (q != null) {
                        q.setQuestionText(request.getParameter("questname" +idx));
                        q.setNote(request.getParameter("info" +idx));
                        q.setMaximum(request.getParameter("max" +idx));
                        q.setMinimum(request.getParameter("min" +idx));
                        q.setQAnswer(parserAnswer(request.getParameterValues("domanda"+idx)));
                        q.setCode(randomQuestCode());
//                        q.setPoll(); da gestire
                        q.setQuestionType(Question.QuestionType.valueOf("MULTIPLECHOISE")); //take from code
                        q.setMandatory(true); //take from code
                        q.setPositionNumber(3); //take from code
                        ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().storeQuestion(q);
                        // take request.getParametersNama("domanda1"); as Array. Then passe this to parsing function
//                    createPollAnswer(name, info, min, max, domande);
                        response.sendRedirect("/profilo"); //Redirect or
                    }
                }
            } else {
                request.setAttribute("message", "Cannot update article: insufficient parameters");
                action_error(request, response);

            }
        } catch (DataException ex) {
            request.setAttribute("message", "Cannot create Question");
            action_error(request, response);
        }
    }

    private void createPollAnswer(String name, String info, String min, String max, String[] quests) {
        System.out.println(name + " " +info + " " + min + " " +max);
//        for(String quest: quests) {
//            System.out.print(quest +" ");
//        }
        System.out.println(parserAnswer(quests));
        for(String s : parserAnswer(parserAnswer(quests))) {
            System.out.println(s);
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