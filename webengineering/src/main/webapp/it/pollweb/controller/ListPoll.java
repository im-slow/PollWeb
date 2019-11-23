package it.pollweb.controller;

import framework.data.DataException;
import framework.result.SplitSlashesFmkExt;
import framework.result.TemplateManagerException;
import framework.result.TemplateResult;
import framework.security.SecurityLayer;
import it.pollweb.data.dao.PollWebDataLayer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListPoll extends PollWebBaseController {

    private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException {
        try {
            TemplateResult res = new TemplateResult(getServletContext());
            //aggiungiamo al template un wrapper che ci permette di chiamare la funzione stripSlashes
            //add to the template a wrapper object that allows to call the stripslashes function
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            request.setAttribute("polls", ((PollWebDataLayer)request.getAttribute("datalayer")).getPollDAO().getPoll());
            res.activate("listasondaggi.ftl.html", request, response);
        } catch (DataException ex) {
            request.setAttribute("message", "Data access exception: " + ex.getMessage());
            //action_error(request, response);
        }
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.setAttribute("page_title", "List Poll");

        try {
            action_default(request, response);
        } catch (IOException ex) {
            request.setAttribute("exception", ex);
            //action_error(request, response);

        } catch (TemplateManagerException ex) {
            request.setAttribute("exception", ex);
            //action_error(request, response);

        }
    }
}
