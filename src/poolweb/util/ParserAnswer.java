package poolweb.util;

import org.apache.commons.lang3.RandomStringUtils;
import poolweb.data.model.Question;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class ParserAnswer {

    private ParserAnswer(){ } // metodo vuoto per non inizializzare la classe

    // metodo per decodifica da db a model
    public static String[] parserAnswer(String qAnswer) {
        return qAnswer.split("#;");
    }

    // metodo per codifica da model a db
    public static String parserAnswer(String[] qAnswer) {
        StringBuilder answer = new StringBuilder();
        for (String s : qAnswer) {
            answer.append(s).append("#;");
        }
        return answer.toString();
    }

    public static String randomQuestCode() {
        return RandomStringUtils.randomAlphabetic(5);
    }

//    public static Map<Integer, List<String>> parserQuestion(List<Question> q){
//        Map<Integer, List<String>> map = new TreeMap<>();
//        Integer i = 1;
//        for (Question split : q) {
//            map.put(i, Arrays.asList(parserAnswer(split.getQAnswer())));
//            i++;
//        }
//        System.out.println(map);
//        return map;
//    }



}
