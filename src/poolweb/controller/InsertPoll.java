package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Poll;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static poolweb.framework.security.SecurityLayer.checkSession;

public class InsertPoll extends PoolWebBaseController  {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession s = checkSession(request);
            if (s!= null) {
                action_poll(request, response, s);
            } else {
                action_redirect(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_poll(HttpServletRequest request, HttpServletResponse response, HttpSession s) throws IOException {
        try {
            if (request.getParameterMap() != null) {
                Poll p;
                User user;
                p = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().createPoll();
                user = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser((int) s.getAttribute("userid"));
                if (p != null) {
                    p.setTitle(request.getParameter("nomedomanda"));
                    p.setOpentext(request.getParameter("testoapertura"));
                    p.setClosetext(request.getParameter("testochiusura"));
                    p.setOpenStatus(Boolean.parseBoolean(request.getParameter("testochiusura"))); //aperto o chiuso
                    p.setPollstatus(Boolean.parseBoolean(request.getParameter("statuspoll")));
                    p.setURL(Integer.toString(p.getID()));
                    p.setUser(user);
                    ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().storePoll(p);
                    action_write(request, response);
                }
                else {
                    request.setAttribute("message", "Errore aggiornamento del sondaggio");
                    action_error(request, response);
                }
            }
        } catch (DataException e) {
            request.setAttribute("message", "Errore creazione del sondaggio");
            action_error(request, response);
        }
    }



    private void action_write(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/inserimentoriuscito");
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

    private void action_error(HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        } else {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }
}
