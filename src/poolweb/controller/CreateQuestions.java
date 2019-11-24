package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Poll;
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

public class CreateQuestions extends PoolWebBaseController{

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            action_poll(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_poll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            if (true) {
                request.setAttribute("page_title", "Aggiungi Domande"); //Titolo da iniettare nel template con freeMarker
                TemplateResult res = new TemplateResult(getServletContext());
                request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
                res.activate("new_questions.ftl", request, response);
            } else {
                request.setAttribute("message", "Unable to create questions");
                action_error(request, response);
            }
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
