package poolweb.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import poolweb.data.dao.PoolWebDataLayer;
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

import static poolweb.framework.security.SecurityLayer.HashingMaps;
import static poolweb.framework.security.SecurityLayer.checkSession;

public class InsertRes extends PoolWebBaseController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession s = checkSession(request);
            if (s != null && request.getParameter("email") != null) {
                    if(request.getParameter("email").matches("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}")) {
                        action_user(request, response, s);
                    } else {
                        request.setAttribute("message", "Email non valida, l'utente non è stato creato");
                        action_error(request, response);
                    }
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
            User u = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().createUser();
            Role r = ((PoolWebDataLayer) request.getAttribute("datalayer")).getRoleDAO().getRoleByID(2);
            if (u != null && r != null) {
                u.setEmail(request.getParameter("email"));
                u.setName(StringUtils.substringBefore(request.getParameter("email"), "@"));
                u.setPassword(HashingMaps(RandomStringUtils.randomAscii(32)));
                u.setRole(r);
                ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().storeUser(u);
                ((PoolWebDataLayer) request.getAttribute("datalayer")).getRoleDAO().storeUserRole(u);
                GenerateTxtMail.newMail(u.getName(), u.getEmail(), u.getPassword(), u.getRole().getDescription());
                action_write(response);
            } else {
                request.setAttribute("message", "Errore nell'aggiunta dell'utente");
                action_error(request, response);
            }
        } catch (DataException | NoSuchAlgorithmException e) {
            action_error(request, response);
        }
    }

    private void action_write(HttpServletResponse response) throws IOException {
        // response.getWriter().write("{success: true}");
        response.sendRedirect("/profilo");
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
