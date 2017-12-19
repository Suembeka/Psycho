package uz.suem.psycho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {

    private static String DB_NAME = "database.db";
    private static final int DB_VERSION = 1; // версия базы данных
    private static final String TABLE = "saved_results";

    private String KEY_ID = "id";
    private String KEY_NAME = "name";
    private String KEY_TEST_NAME = "test_name";
    private String KEY_RESULT = "result";
    private String KEY_NOTES = "notes";
    private String KEY_TIME = "time";

    SQLiteDatabase database;

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Context myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String CREATE_TABLE = "CREATE TABLE "+TABLE +"("+KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
//                KEY_NAME +" TEXT," +KEY_TEST_NAME+" TEXT,"+KEY_RESULT+" TEXT,"+KEY_NOTES+" TEXT,"+KEY_TIME+" TEXT);";
//
//        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    void addNewNote(String name, String test_name, String result, String notes, String time) {
        database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_TEST_NAME, test_name);
        values.put(KEY_RESULT, result);
        values.put(KEY_NOTES, notes);
        values.put(KEY_TIME, time);

        database.insert(TABLE, null, values);
        database.close();
    }

    void deleteRow(Integer id){
        database = this.getWritableDatabase();
        database.execSQL("DELETE FROM "+TABLE+" WHERE "+KEY_ID +" = "+id+";");
        database.close();
    }

    Cursor selectAll(){
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE, null);
        //database.close();
        return cursor;
    }
}
