package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Poll;
import poolweb.data.model.Role;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.framework.result.FailureResult;
import poolweb.framework.result.SplitSlashesFmkExt;
import poolweb.framework.result.TemplateManagerException;
import poolweb.framework.result.TemplateResult;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import static poolweb.framework.security.SecurityLayer.*;

public class Profile extends PoolWebBaseController {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession s = checkSession(request);
            if (s!= null) {
                action_user(request, response, s);
            } else {
                action_redirect(request, response);
            }
        } catch (IOException ioexc) {
            ioexc.printStackTrace();
        }
    }

    private void action_user(HttpServletRequest request, HttpServletResponse response, HttpSession s) throws IOException, ServletException {
        try {
            TemplateResult res = new TemplateResult(getServletContext());
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            User currentuser = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser((int) s.getAttribute("userid"));
            request.setAttribute("page_title", "Profilo");
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            List<Poll> usersPoll = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getPollByUserID(currentuser.getID());
            Role role = ((PoolWebDataLayer) request.getAttribute("datalayer")).getRoleDAO().getRoleByUser(currentuser);
            request.setAttribute("user", currentuser);
            // check errori
            if (usersPoll != null) {
                request.setAttribute("userPoll", usersPoll);
            } else {
                request.setAttribute("message", "Error during User loading");
                action_error(request, response);
            }
            if (role !=null) {
                request.setAttribute("role", role);
            } else {
                request.setAttribute("message", "Error during Role loading");
                action_error(request, response);
            }
            // tutto ok carica template
            res.activate("profile.ftl", request, response);
        } catch (DataException | TemplateManagerException e) {
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
