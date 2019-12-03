package poolweb.util;

import org.apache.commons.lang3.RandomStringUtils;

public final class ParserAnswer {

    private ParserAnswer(){ } // metodo vuoto per non inizializzare la classe

    // metodo per decodifica da db a model
    public static String[] parserAnswer(String qAnswer) {
        return qAnswer.split("£;");
    }

    // metodo per codifica da model a db
    public static String parserAnswer(String[] qAnswer) {
        StringBuilder answer = new StringBuilder();
        for (String s : qAnswer) {
            answer.append(s).append("£;");
        }
        return answer.toString();
    }

    public static String randomQuestCode() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

//    public Map<Integer, List<String>> parserQuestion(List<Question> q){
//        Map<Integer, List<String>> map = new TreeMap<>();
//        Integer i = 1;
//        for (Question split : q) {
//            map.put(i, parserAnswer(split.getQAnswer()));
//            i++;
//        }
//        System.out.println(map);
//        return map;
//    }

}
