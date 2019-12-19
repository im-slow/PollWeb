package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Poll;
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
import java.util.List;

@WebServlet(name = "GetUser")
public class GetUser extends PoolWebBaseController {

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
            List<Poll> poll = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getPopoularPoll();
            int pCount = ((PoolWebDataLayer) request.getAttribute("datalayer")).getPollDAO().getCount();
            int qCount = ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().getCount();
            int aCount = ((PoolWebDataLayer) request.getAttribute("datalayer")).getAnswerDAO().getCount();
            int uCount = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getCount();

            if (poll != null) {
                request.setAttribute("poll", poll);
                request.setAttribute("pCount", pCount);
                request.setAttribute("uCount", uCount);
                request.setAttribute("qCount", qCount);
                request.setAttribute("aCount", aCount);
                request.setAttribute("page_title", "Home");
                TemplateResult res = new TemplateResult(getServletContext());
                request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
                res.activate("home.ftl", request, response);
            } else {
                request.setAttribute("message", "Unable to load Users");
                action_error(request, response);
            }
        } catch (DataException ex) {
            ex.printStackTrace();
            request.setAttribute("message", "Data access exception: " + ex.getMessage());
            action_error(request, response);
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
