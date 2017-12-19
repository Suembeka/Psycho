package uz.suem.psycho;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.sql.SQLException;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static String DB_NAME = "database.db";
    private static final int SCHEMA = 1; // версия базы данных
    private static final String TABLE = "saved_results";

    SQLiteDatabase database;
    private Context myContext;

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext = context;

        DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    void createDataBase() throws SQLException {
        if (database == null) {
            try {
                this.getReadableDatabase();
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            openDataBase();
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = null;
        try {
            myInput = myContext.getAssets().open(DB_NAME);
            String outFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[10];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
            openDataBase();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    void addNewNote(String name, String test_name, String result, String notes, String time) {
        database.execSQL("INSERT INTO " + TABLE + " (name, test_name, result, notes, time) VALUES ('" + name + "', '" + test_name + "', '" + result + "', '" + notes + "', '" + time + "');");
    }

//    Integer getCount(){
//        return selectAll().getCount();
//    }

    void deleteRow(Integer id){
        database.execSQL("DELETE FROM "+TABLE+" WHERE id = "+id+";");
    }

    Cursor selectAll(){
        return database.rawQuery("SELECT * FROM "+TABLE+";", null);
    }
}
