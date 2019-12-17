package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Answer;
import poolweb.framework.data.DataException;
import poolweb.framework.result.FailureResult;
import poolweb.framework.result.SplitSlashesFmkExt;
import poolweb.framework.result.TemplateManagerException;
import poolweb.framework.result.TemplateResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;

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
//                TreeSet<String> indexes = new TreeSet<>();
//                Map<String, String[]> parmap = request.getParameterMap();
//                for(Map.Entry<String, String[]> entry : parmap.entrySet()) {
//                    indexes.add((entry.getKey().substring(entry.getKey().length() - 1)));
//                }
//                for(String idx: indexes) {
//                    // -- Here take the parameters for create a single Question Obj -- //
//                    Answer a = ((PoolWebDataLayer) request.getAttribute("datalayer")).getAnswerDAO().createAnswer();
//                    if (a != null) {
//                        a.setAnswer(request.getParameter("questname" +idx));
//                        ((PoolWebDataLayer) request.getAttribute("datalayer")).getAnswerDAO().storeAnswer(a);
//                        action_write(request, response);
//                    }
//                }
            } else {
                request.setAttribute("message", "Cannot update article: insufficient parameters");
                action_error(request, response);

            }
        } catch (Exception ex) { //Data Excetion
            request.setAttribute("message", "Cannot create Question");
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
