package it.ingte.bricks.data;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Incapsula una mappa che contiene la combinazione chiave valore dove la chiave del record è l'identificatore univoco, mentre il valore è il record stesso
 *
 * Created by paolo on 21/12/17.
 */

public class Records {

    Map<String, Record> p;

    public Records() {
        p  = new HashMap();
    }

    /**
     * Permette l'aggiunta di un record
     * @param r
     */
    public void add(Record r) {
        p.put(r.getIdentifier(), r);
    }

    /**
     * Permette di leggere un record
     * @param id key del record
     * @return il record corrispondente alla key
     */
    public Record get(String id) {
        return p.get(id);
    }

    public Map<String, Record> getP() {
        return p;
    }
}