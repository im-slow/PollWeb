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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;

import static poolweb.framework.security.SecurityLayer.*;

public class LogIn extends PoolWebBaseController {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession s = checkSession(request);
            if (s!=null){
                action_update(request, response, s);
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
            User newUser = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser(email, HashingMaps(password));
            if (newUser!= null) {
                createSession(request, newUser.getName(), newUser.getID());
                request.setAttribute("page_title", "Profilo");
                request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
                List<Poll> usersPoll = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getPollByUserID(newUser.getID());
                request.setAttribute("user", newUser);
                request.setAttribute("userPoll", usersPoll);
                res.activate("profile.ftl", request, response);
            } else {
                request.setAttribute("page_title", "Accedi");
                request.setAttribute("login_error", "Username o password errati");
                res.activate("login.ftl", request, response);
            }
//            if (usersPoll != null) {
//                request.setAttribute("usersPoll", usersPoll);
//                res.activate("profile.ftl", request, response);
//            } else {
//                request.setAttribute("message", "Nessun Sondaggio disponibile");
//                action_error(request, response);
//            }
//            res.activate("profile.ftl", request, response);

        } catch (DataException e) {
            e.printStackTrace();
        }
    }

    private void action_update(HttpServletRequest request, HttpServletResponse response, HttpSession s) throws IOException, ServletException, TemplateManagerException {
        try {
            TemplateResult res = new TemplateResult(getServletContext());
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            User currentuser = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser((int) s.getAttribute("userid"));
            request.setAttribute("page_title", "Profilo");
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            List<Poll> usersPoll = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getPollByUserID(currentuser.getID());
            request.setAttribute("user", currentuser);
            if (usersPoll != null) {
                request.setAttribute("userPoll", usersPoll);
            } else {
                request.setAttribute("message", "Error during User loading");
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
