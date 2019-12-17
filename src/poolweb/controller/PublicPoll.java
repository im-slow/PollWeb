package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.framework.data.DataException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet(name = "PublicPoll")
public class PublicPoll extends PoolWebBaseController {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        /* Possibile Implementare Controlli sui dati in input nella request, come validate, sanitaze o controlli su input mancanti*/
        try {
            action_user(request, response);
        } catch (IOException ioexc) {
            ioexc.printStackTrace();
        }
    }

    private void action_user(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().closeStatus(parseInt(request.getParameter("id")));
        } catch(DataException e) {
            e.printStackTrace();
        }
    }

    //Necessario per gestire le return di errori
    private void action_error(HttpServletRequest request, HttpServletResponse response) {

    }

}
