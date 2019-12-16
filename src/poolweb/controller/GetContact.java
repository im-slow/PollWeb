package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Answer;
import poolweb.data.model.Poll;
import poolweb.data.model.Question;
import poolweb.framework.data.DataException;
import poolweb.framework.result.FailureResult;
import poolweb.framework.result.SplitSlashesFmkExt;
import poolweb.framework.result.TemplateManagerException;
import poolweb.framework.result.TemplateResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetContact")
public class GetContact extends PoolWebBaseController {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        /* Possibile Implementare Controlli sui dati in input nella request, come validate, sanitaze o controlli su input mancanti*/
        try {
            action_contact(request, response);
        } catch (IOException ioexc) {
            ioexc.printStackTrace();
        }
    }

    private void action_contact(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {

            if (true) {
                request.setAttribute("page_title", "Contact"); //Titolo da iniettare nel template con freeMarker
                TemplateResult res = new TemplateResult(getServletContext());
                request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
                res.activate("contact.ftl", request, response);
            } else {
                request.setAttribute("message", "Unable to load Contact");
                action_error(request, response);
            }
        } catch (TemplateManagerException e) {
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
