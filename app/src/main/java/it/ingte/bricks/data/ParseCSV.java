package it.ingte.bricks.data;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by paolo on 21/12/17.
 */

public class ParseCSV {

    private File input;
    private boolean hasHeader;
    private String delimiter;

    /**
     * Crea un parser per leggere il csdv passato come parametro
     * @param input File csv da leggere
     * @param hasHeader TRUE se ha un header
     * @param delimiter Indica il carattere/stringa utilizzato come delimitatore nel file
     */
    public ParseCSV(File input, boolean hasHeader, String delimiter) {
        this.input = input;
        this.hasHeader = hasHeader;
        this.delimiter = delimiter;
    }

    /**
     * Ritorna la l'oggetto records che contiene tutte le righe lette dal csv
     * @return l'oggetto records che contiene tutte le righe lette dal csv
     */
    public Records getRecords() {
        Scanner sc;
        Records myrecords = new Records();

        try {
           sc = new Scanner(input);

           //Se ha l'header saltalo
           if(hasHeader) sc.next();

           while(sc.hasNext()) myrecords.add(new Record(sc.next().split(delimiter)));

           sc.close();
        } catch(FileNotFoundException f) {
            Log.e("File", "Input file not found");
        } catch(Exception e) {
            Log.e("Record","Bad columns");
        }

        return myrecords;
    }
}
