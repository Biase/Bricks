package it.ingte.bricks;

import java.io.File;

import it.ingte.bricks.data.ParseCSV;
import it.ingte.bricks.data.Records;

/**
 * Created by paolo on 21/12/17.
 */

public class Preload extends Thread {

    private Records rows;
    private String path = "./dati.csv";

    @Override
    public void run() {
        rows = new ParseCSV(new File(path), true, ";").getRecords();
    }

    /**
     *
     * @return
     */
    public Records getRows() {
        return rows;
    }
}
