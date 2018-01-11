package it.ingte.bricks;

/**
 * Created by marco on 10/01/18.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.io.IOException;


public class DBmanager {
    private DBHelper dbhelper;

    public DBmanager(Context ctx) {
        dbhelper = new DBHelper(ctx);
        try {
            dbhelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public SQLiteDatabase getDatabaseAccess() {
        return dbhelper.getReadableDatabase();
    }


}

