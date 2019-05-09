package main.webapp.servlet;

import main.webapp.HTMLHelpers;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Salutami extends javax.servlet.http.HttpServlet {

    private Calendar startup;
    private final SimpleDateFormat sdf = new SimpleDateFormat();

    protected void action_saluta_anonimo(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HTMLHelpers.printPageHeader(out, "Salutami!");
            out.println("<p>Hello!</p>");
            out.println("<form method=\"get\" action=\"salutami\">");
            out.println("<p>What is your name?");
            out.println("<input type=\"text\" name=\"n\"/>");
            out.println("<input type=\"submit\" name=\"s\" value=\"Hello!\"/>");
            out.println("</p>");
            out.println("</form>");
            out.println("<p><small>I'm greeting all users since " + sdf.format(startup.getTime()) + "</small></p>");
            HTMLHelpers.printPageFooter(out);
        } finally {
            out.close();
        }
    }

    private void action_saluta_noto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("n");
        if (nome == null || nome.isEmpty()) {
            nome = "Unknown";
        } else {
            //qui dovremmo "sanitizzare" il parametro
            //ad esempio con https://github.com/OWASP/java-html-sanitizer
            //ma usiamo il nostro sanitizzatore "di base" direttamente in fase di output
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HTMLHelpers.printPageHeader(out, "Salutami!");
            out.println("<p>Hello, " + HTMLHelpers.sanitizeHTMLOutput(nome) + "!</p>");
            HTMLHelpers.printPageFooter(out);
        } finally {
            out.close();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            //HttpSession session = request.getSession(true); //Inizializzazione della Sessione!
            String n = request.getParameter("n");
            if (n == null || n.isEmpty()) {
                //session.setAttribute("n", request.getParameter("n")); Creazione id di sessione
                action_saluta_anonimo(response, request);
            }
            else action_saluta_noto(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        startup = Calendar.getInstance();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        processRequest(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        processRequest(request, response);
    }
}
