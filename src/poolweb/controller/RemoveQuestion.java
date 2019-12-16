package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
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
            if (request.getParameter("id") != null && s != null) {
                action_poll(request, response);
            } else {
                action_error(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_poll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().removeQuestion(id);
        } catch (DataException e) {
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
}
