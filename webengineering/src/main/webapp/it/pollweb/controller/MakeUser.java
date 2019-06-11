package it.pollweb.controller;

import it.pollweb.data.dao.PollWebDataLayer;
import it.pollweb.data.framework.data.DataException;
import it.pollweb.data.framework.result.FailureResult;
import it.pollweb.data.framework.result.SplitSlashesFmkExt;
import it.pollweb.data.framework.result.TemplateManagerException;
import it.pollweb.data.framework.result.TemplateResult;
import it.pollweb.data.framework.security.SecurityLayer;
import it.pollweb.data.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeUser extends PollWebBaseController {

    private void action_error(HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        } else {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }

    private void action_user(HttpServletRequest request, HttpServletResponse response, int k) throws IOException, ServletException, TemplateManagerException {
        try {
            User user = ((PollWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser(k);
            if (user != null) {
                request.setAttribute("user", user);
                //request.setAttribute("page_title", "Read Article");
                //verr� usato automaticamente il template di outline spcificato tra i context parameters
                //the outlne template specified through the context parameters will be added by the TemplateResult to the specified template
                TemplateResult res = new TemplateResult(getServletContext());
                //aggiungiamo al template un wrapper che ci permette di chiamare la funzione stripSlashes
                //add to the template a wrapper object that allows to call the stripslashes function
                request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
                res.activate("article.ftl.html", request, response);
            } else {
                request.setAttribute("message", "Unable to load user");
                action_error(request, response);
            }
        } catch (DataException ex) {
            request.setAttribute("message", "Data access exception: " + ex.getMessage());
            action_error(request, response);
        }
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        int k;
        try {
            k = SecurityLayer.checkNumeric(request.getParameter("k"));
            action_user(request, response, k);
        } catch (NumberFormatException ex) {
            request.setAttribute("message", "Article key not specified");
            action_error(request, response);
        } catch (IOException ex) {
            request.setAttribute("exception", ex);
            action_error(request, response);

        } catch (TemplateManagerException ex) {
            request.setAttribute("exception", ex);
            action_error(request, response);

        }
    }

    @Override
    public String getServletInfo() {
        return "Render article servlet";
    }// </editor-fold>
}
