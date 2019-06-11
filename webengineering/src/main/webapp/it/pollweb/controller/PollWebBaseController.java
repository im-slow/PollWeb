package it.pollweb.controller;

import it.pollweb.data.dao.PollWebDataLayer;
import framework.result.FailureResult;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

public abstract class PollWebBaseController extends HttpServlet {

    @Resource(name = "jdbc/webdb2")
    private DataSource ds;

    protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException;

    private void processBaseRequest(HttpServletRequest request, HttpServletResponse response) {
        try(PollWebDataLayer datalayer = new PollWebDataLayer(ds)) {
            datalayer.init();
            request.setAttribute("datalayer", datalayer);
            processRequest(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            (new FailureResult(getServletContext())).activate(
                    (ex.getMessage() !=null || ex.getCause() == null) ? ex.getMessage() : ex.getCause().getMessage(), request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processBaseRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processBaseRequest(request, response);
    }

}
