package poolweb.util;

import java.util.ArrayList;
import java.util.List;

public final class ParserAnswer {

    private ParserAnswer(){ } // metodo vuoto per non inizializzare la classe

    // metodo per decodifica da db a model
    public List<String> parserAnswer(String qAnswer) {
        List<String> answer = new ArrayList();
        int initStr = 0;
        for(int i = 0; i < qAnswer.length()-1; i++) { //il ciclo si ferma un carattere prima dalla fine dato che il simbolo di fine domanda dura 2 char ('$;')
            char c = qAnswer.charAt(i); // assegna i caratteri i e i+1 a due caratteri
            char d = qAnswer.charAt(i+1);
            if (c == '$' && d == ';') { // verifica se i due caratteri sono '$;'
                answer.add(qAnswer.substring(initStr, i-1)); // aggiungi alla lista la substr
                initStr = i+2;
            }
        }
        return answer;
    }

    // metodo per codifica da model a db
    public String parserAnswer(List<String> qAnswer) {
        String answer = "";
        for (String s : qAnswer) {
            answer = s + "$;";
        }
        return answer;
    }

}
