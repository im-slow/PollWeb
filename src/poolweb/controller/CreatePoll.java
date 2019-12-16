package poolweb.controller;

import poolweb.framework.result.FailureResult;
import poolweb.framework.result.SplitSlashesFmkExt;
import poolweb.framework.result.TemplateManagerException;
import poolweb.framework.result.TemplateResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static poolweb.framework.security.SecurityLayer.checkSession;

public class CreatePoll extends PoolWebBaseController  {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession s = checkSession(request);
            if (s!= null) {
                action_poll(request, response);
            } else {
                action_redirect(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_poll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            if (true) {
                request.setAttribute("page_title", "Crea Sondaggio");
                TemplateResult res = new TemplateResult(getServletContext());
                request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
                res.activate("new_poll.ftl", request, response);
            } else {
                request.setAttribute("message", "Unable to load Users");
                action_error(request, response);
            }
        } catch (TemplateManagerException e) {
            e.printStackTrace();
        }
    }

    private void action_redirect(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        try {
            request.setAttribute("urlrequest", request.getRequestURL());
            RequestDispatcher rd = request.getRequestDispatcher("/accedi");
            rd.forward(request, response);
        } catch (ServletException e) {
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
