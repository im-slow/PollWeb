package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Poll;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.framework.result.FailureResult;
import poolweb.framework.result.SplitSlashesFmkExt;
import poolweb.framework.result.TemplateManagerException;
import poolweb.framework.result.TemplateResult;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import poolweb.framework.security.SecurityLayer;
import javax.servlet.http.HttpSession;

import static poolweb.framework.security.SecurityLayer.checkSession;
import static poolweb.framework.security.SecurityLayer.createSession;

public class LogIn extends PoolWebBaseController {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            if (checkSession(request)!=null){
                action_update(request, response);
            } else if((request.getParameter("email")!=null)&&(request.getParameter("password")!=null)) {
                action_write(request, response, request.getParameter("email"), request.getParameter("password"));
            } else {
                action_default(request, response);
            }
        } catch (NumberFormatException ex) {
            request.setAttribute("message", "Invalid number submitted");
            action_error(request, response);
        } catch (IOException ex) {
            request.setAttribute("exception", ex);
            action_error(request, response);
        } catch (TemplateManagerException ex) {
            request.setAttribute("exception", ex);
            action_error(request, response);
        }
    }

    private void action_default(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession s = checkSession(request);
            if(s!=null){
                System.out.println("loggato");
            } else {
                System.out.println("non loggato");
            }
            if (true) {
                request.setAttribute("page_title", "Accedi"); //Titolo da iniettare nel template con freeMarker
                TemplateResult res = new TemplateResult(getServletContext());
                request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
                res.activate("login.ftl", request, response);
            } else {
                request.setAttribute("message", "Unable to load Users");
                action_error(request, response);
            }
        } catch (TemplateManagerException e) {
            e.printStackTrace();
        }
    }

    private void action_write(HttpServletRequest request, HttpServletResponse response, String email, String password) throws IOException, ServletException, TemplateManagerException {
        try {
            TemplateResult res = new TemplateResult(getServletContext());
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            User newUser = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser(email, password);
            String username = newUser.getName();
            int id = newUser.getID();
            createSession(request, username, id);
            request.setAttribute("page_title", "Profilo");
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            List<Poll> usersPoll = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getAllPoll(newUser.getID());
            request.setAttribute("newUser", newUser);
            if (usersPoll != null) {
                request.setAttribute("usersPoll", usersPoll);
                res.activate("profile.ftl", request, response);
            } else {
                request.setAttribute("message", "Nessun Sondaggio disponibile");
                action_error(request, response);
            }
            res.activate("profile.ftl", request, response);

        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    private void action_update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TemplateManagerException {
        try {
            HttpSession s = checkSession(request);
            System.out.println(s.getAttribute("userid"));
            TemplateResult res = new TemplateResult(getServletContext());
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            User newUser = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser((int) s.getAttribute("userid"));
            String username = newUser.getName();
            int id = newUser.getID();
            request.setAttribute("page_title", "Profilo");
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            List<Poll> usersPoll = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getAllPoll(newUser.getID());
            request.setAttribute("newUser", newUser);
            if (usersPoll != null) {
                request.setAttribute("usersPoll", usersPoll);
                res.activate("profile.ftl", request, response);
            } else {
                request.setAttribute("message", "Nessun Sondaggio disponibile");
                action_error(request, response);
            }
            res.activate("profile.ftl", request, response);

        } catch (DataException e) {
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
