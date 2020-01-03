package poolweb.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class GenerateTxtMail {

    private GenerateTxtMail(){ }

    public static void newMail(String name, String email, String password, String role){
        try {
            File file = new File(email+".txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            pw.println("Gentile "+name+",");
            pw.println("A seguito della sua richiesta é stato iscritto con successo al sito PollWeb");
            pw.println("La password per accedere al suo account è: \"" + password +"\",");
            pw.println(role);
            pw.println("Cordiali saluti,");
            pw.println("PollWeb.");
            pw.close();
            System.out.println("L'email é stata salvata in: " +file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void newMail(String name, String email, String password, String role, String url){
        try {
            File file = new File(email+".txt");
            if(!file.exists()) {
                file.createNewFile();
            }

            PrintWriter pw = new PrintWriter(file);
            pw.println("Gentile "+name+",");
            pw.println("A seguito della sua richiesta é stato iscritto con successo al sito PollWeb");
            pw.println("La password per accedere al suo account è: " + "\""+password+"\"");
            pw.println(role+" Può riuspondere al seguente sondaggio: "+url);
            pw.println("Cordiali saluti,");
            pw.println("PollWeb.");
            pw.close();
            System.out.println("L'email é stata salvata in: " +file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
