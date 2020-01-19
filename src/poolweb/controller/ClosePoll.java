package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Poll;
import poolweb.data.model.Question;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.framework.result.FailureResult;
import poolweb.framework.result.SplitSlashesFmkExt;
import poolweb.framework.result.TemplateManagerException;
import poolweb.framework.result.TemplateResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static poolweb.framework.security.SecurityLayer.checkSession;

@WebServlet(name = "ClosePoll")
public class ClosePoll extends PoolWebBaseController {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        /* Possibile Implementare Controlli sui dati in input nella request, come validate, sanitaze o controlli su input mancanti*/
        try {
            HttpSession s = checkSession(request);
            if (s != null && request.getParameter("id") != null) {
                action_user(request, response, s);
            } else {
                request.setAttribute("message", "Dati non sufficienti");
                action_error(request, response);
            }
        } catch (IOException ioexc) {
            ioexc.printStackTrace();
        }
    }

    private void action_user(HttpServletRequest request, HttpServletResponse response, HttpSession s) throws IOException, ServletException {
        try {
            User us = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser((int) s.getAttribute("userid"));
            Poll pl = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getPollByID(Integer.parseInt(request.getParameter("id")));
            if (us != null && pl != null && us.getID() == pl.getUser().getID()) {
                    ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().closeStatus(2 ,pl.getID());
                    action_write(request, response);
            } else {
                request.setAttribute("message", "Non sei autorizzato a modificare questo poll");
            }
        } catch (DataException e) {
            request.setAttribute("message", "Errore modifica stato del sondaggio");
            action_error(request, response);
            e.printStackTrace();
        } catch (TemplateManagerException e) {
            request.setAttribute("message", "Errore generico");
            action_error(request, response);
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

    //Necessario per gestire le return di errori
    private void action_write(HttpServletRequest request, HttpServletResponse response) throws TemplateManagerException {

        request.setAttribute("page_title", "Sondaggio Pubblicato");
        TemplateResult res = new TemplateResult(getServletContext());
        request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
        request.setAttribute("message", "Il sondaggio è stato concluso con successo");
        res.activate("success.ftl", request, response);
    }

}
