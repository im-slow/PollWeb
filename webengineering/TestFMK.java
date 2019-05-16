/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andy4
 */
public class TestFMK {
    //cfg utilizzare come argomento del metodo la versione di FMK
    //default e output encoding della stessa versione del template (UTF-8)
    //servlet context contiene la directory del template
    
   // Template t = cfg.gettemplate("template da caricare")
   // eccezioni sulla struttura del template
   // Map<String,Object> data = new HashMap();
   // data.put(k,v)
   //
   //
   // t.process(data model da passare al template)
    
   // Map<String,Object> persona1 = new HashMap();
   // persona1.put("nome","Tizio");
   // persona1.put("cognome", "Pallino");
   // data.put("p1", persona1);
    
   // data model
   // Article a = new ArticleImpl();
   // a.setTitle("titolo");
}

//nel template caricato
//<div> ${key!"string"} </div> se key non esiste scrive la stringa
//<div> ${key?capfirst} </div> mette la prima lettera maiuscola, le interpolation possono essere chiamate in sequenza
//<div> ${persona1?capfirst} </div> persona1 non puo essere convertita in stringa però
//<div> ${persona1.nome} </div> estrapola il nome

//passaggio model a FMK
//<div> ${a.title} </div> vede proprietà pubbliche (get e set) FMK vete la get senza get e con la lettera minucola, quindi chiama la get e ritorna il dato