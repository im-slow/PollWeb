package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.framework.result.FailureResult;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "PoolWebBaseController")
public abstract class PoolWebBaseController extends HttpServlet {

    @Resource(name = "jdbc/webdb2")
    private DataSource ds;

    protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException;

    private void processBaseRequest(HttpServletRequest request, HttpServletResponse response) {
        try (PoolWebDataLayer datalayer = new PoolWebDataLayer(ds)) {
            datalayer.init();
            request.setAttribute("datalayer", datalayer);
            request.setCharacterEncoding("UTF-8");
            processRequest(request, response);
        } catch (Exception ex) {
            request.setAttribute("message", "Errore interno");
            request.setAttribute("submessage", "Riprova più tardi");
            action_error(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processBaseRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processBaseRequest(request, response);
    }

    private void action_error(HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        } else {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }


}
