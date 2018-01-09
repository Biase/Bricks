package it.ingte.bricks;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by paolo on 26/12/17.
 */

public class Records {

    private List<Record> records;
    private File source;
    private boolean hasHeader;
    private Context ctx;


    //ladrato da internet
    final static String fileName = "dati.csv";

    /**
     *
     * @param context
     * @param hasHeader
     */
    public Records(Context context, boolean hasHeader) {
        this.hasHeader = hasHeader;
        this.ctx = context;

        String tokens[];
        String currLine;
        Record temp;

        int cursor = 0;

        records = new LinkedList();

        try {
            InputStream is = ctx.getAssets().open(fileName);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            if(!this.hasHeader) currLine = reader.readLine();

            while ((currLine = reader.readLine()) != null) {
                if (cursor != 0) {
                    tokens = currLine.split(";");
                    temp = (new Record(tokens));
                    records.add(temp);
                }
                cursor++;
            }

            reader.close();
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @return
     */
    public List getRecords() {
        return records;
    }


    /**
     *
     * @return Array that contains the short description of record
     */
    public String [] getOnlyCode() {
        String [] codes = new String[records.size()];

        int cursor = 0;

        for (Record rec : records) {
            codes[cursor] = rec.toString();
            cursor++;
        }

        return codes;
    }
}
