package it.ingte.bricks;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    // Data Base Name.
    private static final String DATABASE_NAME = "Dbricks.db";
    // Data Base Version.
    private static final int DATABASE_VERSION = 1;
    private static String DB_PATH = "/data/data/it.ingte.bricks/databases/";
    private static SQLiteDatabase sqliteDB;
    private Context context;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context Parameters of super() are    1. Context
     *                2. Data Base Name.
     *                3. Cursor Factory.
     *                4. Data Base Version.
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * By calling this method and empty database will be created into the default system path
     * of your application so we are gonna be able to overwrite that database with our database.
     */
    public void createDataBase() throws IOException {
        //check if the database exists
        boolean databaseExist = checkDataBase();

        if (!databaseExist) {
            this.getWritableDatabase();
            copyDataBase();
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    public boolean checkDataBase() {
        File databaseFile = new File(DB_PATH + DATABASE_NAME);
        return databaseFile.exists();
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transferring byte stream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    /**
     * This method opens the data base connection.
     * First it create the path up till data base of the device.
     * Then create connection with data base.
     */
    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DATABASE_NAME;
        sqliteDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public ArrayList<Info> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        //Stringhe che conterranno i dati clean
        String cap, town, province;

        //Vettori dove vado ad inserire i valori splittati (non inizializzo perchè poi lo faccio dinamicamente)
        String[] vectCap, vectTown, vectProvince;

        ArrayList<Info> info = new ArrayList<Info>();
        Cursor result = db.rawQuery("select * from record", null);
        while (result.moveToNext()) {
            //Prendo la stringa letta dalla colonna per intero
            cap = result.getString(10);
            //Genero il vettore dove ogni slot contiene un CAP
            vectCap = cap.split(":");

            //Scorro il vettore dei CAP
            for (int i = 0; i < vectCap.length; i++) {
                //Creo una stringa con tutti i CAP separati da virgole
                cap = cap + ", " + vectCap[i];
                //Mostro cosa leggo
                Log.i("Debug", "Ho trovato questo CAP: " + vectCap[i]);
            }

            //Rimuovo la prima virgola (in teoria funziona così)
            cap = cap.substring(1);

            //Per il resto è praticamente un copia e incolla
            //Codice compilato e testato al volo con emulatore


            town = result.getString(11);
            town = town.split(":")[0];


            province = result.getString(12);
            province = province.split(":")[0];

            info.add(new Info(result.getString(0), result.getString(1), result.getString(2),
                    result.getString(3), result.getString(4),
                    result.getString(5), result.getString(6), result.getString(7), result.getFloat(8),
                    result.getDouble(9), cap, town, province, result.getString(14),
                    result.getString(15), result.getInt(16)));

        }
        return info;

    }

    /**
     * This Method is used to close the data base connection.
     */
    @Override
    public synchronized void close() {
        if (sqliteDB != null)
            sqliteDB.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = "CREATE TABLE record (_id INTEGER PRIMARY KEY AUTOINCREMENT)";
        db.execSQL(q);
        Log.d("split", String.format("INPUT: %s", db.getPath()));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
