package poolweb.controller;

import poolweb.framework.result.SplitSlashesFmkExt;
import poolweb.framework.result.TemplateManagerException;
import poolweb.framework.result.TemplateResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertSucceeded extends PoolWebBaseController{

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        action_write(request, response);
    }

    private void action_write(HttpServletRequest request, HttpServletResponse response) {
        try {
            TemplateResult res = new TemplateResult(getServletContext());
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            request.setAttribute("page_title", "Inserimento Riuscito");
            request.setAttribute("strip_slashes", new SplitSlashesFmkExt());
            res.activate("status_insert.ftl", request, response);
        } catch (TemplateManagerException e) {
            e.printStackTrace();
        }
    }

}
