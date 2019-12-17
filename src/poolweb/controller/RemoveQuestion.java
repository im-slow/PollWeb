package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Poll;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.framework.result.FailureResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static poolweb.framework.security.SecurityLayer.checkSession;

public class RemoveQuestion extends PoolWebBaseController{

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession s = checkSession(request);
            if (request.getParameter("id") != null && request.getParameter("pollID") != null && s != null) {
                action_poll(request, response, s);
            } else {
                request.setAttribute("message", "Parametri non sufficienti");
                action_error(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_poll(HttpServletRequest request, HttpServletResponse response, HttpSession s) throws IOException, ServletException {
        try {
            User us = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser((int) s.getAttribute("userid"));
            Poll pl = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getPollByID(Integer.parseInt(request.getParameter("pollID")));
            if (pl != null && us.getID() == pl.getUser().getID()) {
                int id = Integer.parseInt(request.getParameter("id"));
                ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().removeQuestion(id);
                action_write(response);
            } else {
                request.setAttribute("message", "Non sei autorizzato a modificare il sondaggio");
                action_error(request, response);
            }
        } catch (DataException e) {
            action_error(request, response);
        }
    }

    private void action_write(HttpServletResponse response) throws IOException {
        response.getWriter().write("{success: true}");
    }

    //Necessario per gestire le return di errori
    private void action_error(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, (String) request.getAttribute("message"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
