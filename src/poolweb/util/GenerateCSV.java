package poolweb.util;

import poolweb.data.dao.PoolWebDataLayer;
import poolweb.data.model.Answer;
import poolweb.data.model.Poll;
import poolweb.data.model.Question;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GenerateCSV {

    private GenerateCSV(){ }

    public static void newCSV(List<List<String>> result, String pollTitle) throws IOException {
        File file = new File(pollTitle+".csv");
        if(!file.exists()) {
            file.createNewFile();
        }
        FileWriter csvWriter = new FileWriter(file);
        csvWriter.append("Domanda");
        csvWriter.append(",");
        csvWriter.append("Risposta");
        csvWriter.append("\n");

        for (List<String> rowData : result) {
            csvWriter.append(String.join(",", rowData));
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

}
