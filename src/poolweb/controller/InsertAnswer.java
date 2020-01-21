package poolweb.controller;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Answer;
import poolweb.data.model.Instance;
import poolweb.data.model.Question;
import poolweb.data.model.User;
import poolweb.framework.data.DataException;
import poolweb.framework.result.FailureResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static poolweb.framework.security.SecurityLayer.checkSession;
import static poolweb.util.ParserAnswer.parserAnswer;

public class InsertAnswer extends PoolWebBaseController {
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            if (!request.getParameterMap().isEmpty()) {
                action_answer(request, response);
            } else {
                action_error(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_answer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (request.getParameterMap() != null) {
                List<Question> qst = ((PoolWebDataLayer) request.getAttribute("datalayer")).getQuestionDAO().getQuestionByPollID(Integer.parseInt(request.getParameter("idpoll")));
                HttpSession session = checkSession(request);
                Instance check = null;
                if (!qst.get(1).getPoll().getOpenstatus()) { // se il sondaggio è chiuso
                    if (session != null) { // è in sessione?
                        try { // se non esiste istanza o il sondaggio a cui è associata è diversa
                            User us = ((PoolWebDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser((int) session.getAttribute("userid"));
                            check = ((PoolWebDataLayer) request.getAttribute("datalayer")).getInstanceDAO().getInstanceByOKey(us, qst.get(1).getPoll()); // se non trova l'utente e il sondaggio associati all'istanza va in eccezione
                        } catch (DataException e) {
                                check = null;
                                request.setAttribute("message", "Questo sondaggio è privato, non puoi rispondere!");
                                request.setAttribute("submessage", "Contatta il responsabile: "+qst.get(1).getPoll().getUser().getEmail());
                                action_error(request, response);
                        }
                        if (!(check.getUserStatus())) { // se userStatus è false, l'utente ha già risposto
                            request.setAttribute("message", "Hai già risposto a questo sondaggio!");
                            action_error(request, response);
                        }
                    } else {
                        request.setAttribute("message", "Questo sondaggio è privato, non puoi rispondere!");
                        request.setAttribute("submessage", "Effettua il login o contatta il responsabile per averne l'accesso: "+qst.get(1).getPoll().getUser().getEmail());
                        action_error(request, response);
                    }
                }
                List<Answer> answrs = new ArrayList<Answer>();
                ListIterator<Question> listIter = qst.listIterator();
                boolean isvalid = true;
                while (listIter.hasNext() && isvalid) {
                    Question q = listIter.next();
                    int position = q.getPosition();
                    Answer a = ((PoolWebDataLayer) request.getAttribute("datalayer")).getAnswerDAO().createAnswer();
                    if (q.getMandatory()) {
                        String questanswer = request.getParameter("answer" + position);
                        if (questanswer == null || questanswer.equals("")) {
                            request.setAttribute("message", "Necessario rispondere alle domande obbligatorie");
                            action_error(request, response);
                            isvalid = false;
                        }
                    }
                    switch (q.getQuestionType()) {
                        case MULTIPLECHOISE:
                            String[] values = request.getParameterValues("answer"+position);
                            if (!q.getMandatory() && values == null) {
                                a.setAnswer("");
                                a.setQuestion(q);
                                answrs.add(a);
                            } else {
                                if (!checkInputValues(values, q.getQAnswer())) {
                                    request.setAttribute("message", "Errore dati in input");
                                    action_error(request, response);
                                    isvalid = false;
                                } else {
                                    int count = 0;
                                    for (String s : request.getParameterValues("answer"+position)) {
                                        count++;
                                    }
                                    if (count >= Integer.parseInt(q.getMinimum()) && count <= Integer.parseInt(q.getMaximum())) {
                                        a.setQuestion(q);
                                        a.setAnswer(parserAnswer(values));
                                        answrs.add(a);
                                    } else {
                                        request.setAttribute("message", "Rispettare il numero minimo e massimo di risposte");
                                        action_error(request, response);
                                        isvalid = false;
                                    }
                                }
                            }
                            break;
                        case DATE:
                            String questanswer2 = request.getParameter("answer"+position);
                            System.out.println("String: " + questanswer2);
                            Date dateQuestanswer = new SimpleDateFormat("dd/MM/yyyy").parse(questanswer2);
                            SimpleDateFormat finalFormat = new SimpleDateFormat("dd/MM/yyyy");
                            SimpleDateFormat startFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String convertMin = finalFormat.format(startFormat.parse(q.getMaximum()));
                            String convertMax = finalFormat.format(startFormat.parse(q.getMinimum()));
                            Date dateMin = finalFormat.parse(convertMax);
                            Date dateMax = finalFormat.parse(convertMin);
                            System.out.println("min: "+ dateMin + " max: " + dateMax + " mia: " + dateQuestanswer);
                            if (!q.getMandatory() && questanswer2.equals("")) {
                                a.setAnswer("");
                                a.setQuestion(q);
                                answrs.add(a);
                            } else {
                                if (dateQuestanswer.before(dateMax) && dateQuestanswer.after(dateMin) || dateQuestanswer.equals(dateMax) || dateQuestanswer.equals(dateMin)) {
                                    a.setAnswer(questanswer2);
                                    a.setQuestion(q);
                                    answrs.add(a);
                                } else {
                                    request.setAttribute("message", "La data non è inclusa tra minimo e/o massimo");
                                    action_error(request, response);
                                }
                            }
                            break;
                        case SINGLECHOISE:
                            String questanswer1 = request.getParameter("answer"+position);
                            if (!q.getMandatory() && questanswer1 == null) {
                                a.setAnswer("");
                                a.setQuestion(q);
                                answrs.add(a);
                            } else {
                                if (!Arrays.stream(q.getQAnswer()).anyMatch(questanswer1::equals)) {
                                    request.setAttribute("message", "Errore dati in input");
                                    action_error(request, response);
                                    isvalid = false;
                                } else {
                                    a.setQuestion(q);
                                    a.setAnswer(questanswer1);
                                    answrs.add(a);
                                }
                            }
                            break;
                        default:
                            String questanswer = request.getParameter("answer"+position);
                            if (!q.getMandatory() && questanswer.equals("")) {
                                a.setAnswer("");
                                a.setQuestion(q);
                                answrs.add(a);
                            } else {
                                if (questanswer.length() >= Integer.parseInt(q.getMinimum()) && questanswer.length() <= Integer.parseInt(q.getMaximum())) {
                                    a.setAnswer(questanswer);
                                    a.setQuestion(q);
                                    answrs.add(a);
                                } else {
                                    request.setAttribute("message", "La risposta non rispetta i valori di minimo o massimo dei caratteri");
                                    action_error(request, response);
                                }
                            }
                            break;
                    }
                }
                if (isvalid) {
                    action_write(request, response, answrs, check);
                    System.out.println("tuttok ok");
                }
            } else {
                request.setAttribute("message", "Problema interno al sistema");
                request.setAttribute("submessage", "Riprovare più tardi");
                action_error(request, response);
            }
        } catch (NumberFormatException | DataException | ParseException ex) {
            ex.printStackTrace();
            request.setAttribute("message", "Errore dati in input");
            action_error(request, response);
        }
    }

    private boolean checkInputValues(String[] values, String[] quest) {
        boolean isvalid = true;
        for (String qst: values) {
            if (!Arrays.stream(quest).anyMatch(qst::equals)) {
                isvalid = false;
            }
        }
        return isvalid;
    }

    private void action_write(HttpServletRequest request, HttpServletResponse response, List<Answer> answrs, Instance check) {
        try {
            for (Answer asw: answrs) {
                ((PoolWebDataLayer) request.getAttribute("datalayer")).getAnswerDAO().storeAnswer(asw);
            }
            if(check != null){ // se l'utente è stato caricato, il sondaggio è chiuso, quindi  segnala nell'istanza che l'utente non può più rispondere
                check.setUserStatus(false); // l'utente ha risposto e non può più rispondere.
                check.setSubmission(new Date());
                ((PoolWebDataLayer) request.getAttribute("datalayer")).getInstanceDAO().storeInstance(check);
            }
            response.sendRedirect("/inserimentoriuscito");
        } catch (DataException ex) {
            ex.printStackTrace();
            request.setAttribute("message", "Errore interno");
            request.setAttribute("submessage", "Problema durante l'inserimento");
            action_error(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void action_error(HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute("exception") != null) {
            (new FailureResult(getServletContext())).activate((Exception) request.getAttribute("exception"), request, response);
        } else {
            (new FailureResult(getServletContext())).activate((String) request.getAttribute("message"), request, response);
        }
    }
}
