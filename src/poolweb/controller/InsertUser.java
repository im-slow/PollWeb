package poolweb.controller;

import org.apache.commons.lang3.StringUtils;
import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Instance;
import poolweb.data.model.Poll;
import poolweb.data.model.Role;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.util.GenerateTxtMail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static java.lang.Integer.parseInt;
import static poolweb.framework.security.SecurityLayer.HashingMaps;
import static poolweb.framework.security.SecurityLayer.checkSession;

public class InsertUser extends PoolWebBaseController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession s = checkSession(request);
            if (request.getParameter("IDpoll") != null && request.getParameter("nome") != null && request.getParameter("password") != null && s != null) {
                action_user(request, response, s);
            } else {
                request.setAttribute("message", "Parametri non sufficienti");
                action_error(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_user(HttpServletRequest request, HttpServletResponse response, HttpSession s) throws IOException, ServletException {
        try {
            int IDpoll = parseInt(request.getParameter("IDpoll"));
            Poll p = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getPollByID(IDpoll);
            User u = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().createUser();
            Instance i = ((PoolWebDataLayer) request.getAttribute("datalayer")).getInstanceDAO().createInstance();
            Role r = ((PoolWebDataLayer) request.getAttribute("datalayer")).getRoleDAO().getRoleByID(3);
            if (p != null && u != null && i != null && r != null) {
                u.setEmail(request.getParameter("nome"));
                u.setName(StringUtils.substringBefore(request.getParameter("nome"), "@"));
                u.setPassword(HashingMaps(request.getParameter("password")));
                u.setRole(r);
                i.setUser(u);
                i.setPoll(p);
                i.setUserStatus(true);
                ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().storeUser(u);
                ((PoolWebDataLayer) request.getAttribute("datalayer")).getInstanceDAO().storeInstance(i);
                ((PoolWebDataLayer) request.getAttribute("datalayer")).getRoleDAO().storeUserRole(u);
                GenerateTxtMail.newMail(u.getName(), u.getEmail(), u.getPassword(), u.getRole().getDescription(), p.getURL());
                action_write(response, IDpoll);
            } else {
                request.setAttribute("message", "Errore nell'aggiunta dell'utente");
                action_error(request, response);
            }
        } catch (DataException | NoSuchAlgorithmException e) {
            action_error(request, response);
        }
    }

    private void action_write(HttpServletResponse response, int id) throws IOException {
        // response.getWriter().write("{success: true}");
        response.sendRedirect("/rispondisondaggio?id="+id);
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
