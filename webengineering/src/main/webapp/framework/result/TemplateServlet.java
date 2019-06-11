package framework.result;

import freemarker.core.HTMLOutputFormat;
import freemarker.template.*;
import it.pollweb.data.dao.PollWebDataLayer;
import framework.data.DataException;
import it.pollweb.data.model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author IngegneriaDelWeb
 */
public class TemplateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        //impostiamo l'encoding di default per l'input e l'output
        //set the default input and outpout encoding
        cfg.setOutputEncoding("UTF-8");
        cfg.setDefaultEncoding("UTF-8");
        //impostiamo la directory (relativa al contesto) da cui caricare i templates
        //set the (context relative) directory for template loading
        cfg.setServletContextForTemplateLoading(getServletContext(), "./templates");
        //impostiamo un handler per gli errori nei template - utile per il debug
        //set an error handler for debug purposes
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        //impostiamo il gestore degli oggetti - trasformerà in hash i Java beans
        //set the object handler that allows us to "view" Java beans as hashes
        DefaultObjectWrapperBuilder owb = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28);
        owb.setForceLegacyNonListCollections(false);
        owb.setDefaultDateType(TemplateDateModel.DATETIME);
        cfg.setObjectWrapper(owb.build());
        //impostiamo il tipo di output: in questo modo freemarker abiliterà il necessario escaping
        //set the output format, so that freemarker will enable the correspondoing escaping
        cfg.setOutputFormat(HTMLOutputFormat.INSTANCE);

        /*
        User u = new UserImpl();
        u.setName("pippo");
        u.setEmail("pippo@gmail.com");
        u.setPassword("12345678");
        */

        User u = null;
        try {
            u = ((PollWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser(1);
        } catch (DataException e) {
            e.printStackTrace();
            System.out.println("il metodo é sbagliato");
        }



        Map<String, Object> datamodel = new HashMap<String, Object>();
        datamodel.put("utente", u);

        Template t = cfg.getTemplate("index.ftl.html");
        try {
            t.process(datamodel, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

