package kenguerrilla.itl.notelocation.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by KenGuerrilla on 2018/1/10.
 */

public class MyDBHelper extends SQLiteOpenHelper {


    private final static String DATABASE = "notes.db";
    private final static int DATABASE_VER = 1;

    private static final String KG_LOG_TITLE = "KG ------ ";

    private Cursor cursor;

    private static MyDBHelper dbInstance = null;


    public static MyDBHelper getInstance(Context context){

        if(dbInstance == null){

            dbInstance = new MyDBHelper(context,DATABASE,null,DATABASE_VER);

        }

        return dbInstance;
    }

    private MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*
        ======== TABLE ========
         1 - id          -- INTEGER
         2 - title       -- VARCHAR
         3 - note        -- VARCHAR
         4 - place       -- VARCHAR
         5 - date        -- VARCHAR
         6 - alarm       -- VARCHAR
         7 - longitude   -- VARCHAR
         8 - latitude    -- VARCHAR
         9 - alarmCheck  -- INTEGER
        10 - gpsCheck    -- INTEGER
        =======================
        */

/*
        db.execSQL("CREATE TABLE \"main\".\"noteItem\"" +
                        "(\"_id\" INTEGER PRIMARY KEY NOT NULL\n," +
                        "\"title\" VARCHAR NOT NULL , " +
                        " \"note\" VARCHAR, " +
                        " \"place\" VARCHAR, " +
                        " \"date\" VARCHAR, " +
                        " \"alarm\" VARCHAR, " +
                        " \"longitude\" VARCHAR, " +
                        " \"latidude\" VARCHAR, " +
                        " \"alarmCheck\" VARCHAR, " +
                        " \"gpsCheck\" VARCHAR)");
*/

        db.execSQL( "CREATE TABLE main.noteItem " +
                    "(_id INTEGER PRIMARY KEY NOT NULL, " +
                    "title VARCHAR NOT NULL, " +
                    "note VARCHAR," +
                    "place VARCHAR, " +
                    "date VARCHAR NOT NULL, " +
                    "alarm VARCHAR, " +
                    "longitude VARCHAR," +
                    "latitude VARCHAR, " +
                    "alarmCheck INTEGER, " +
                    "gpsCheck INTEGER)"
        );



        Log.d("My Database","Create DataBase");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    Cursor getNoteItemCursor() {

        cursor = getReadableDatabase().query("noteItem",
                null,null,
                null,null,
                null,null);

        return cursor;
    }

    public long deleteNoteById(String tableName, String id){

        SQLiteDatabase db = getWritableDatabase();
        return db.delete(tableName,"_id =" + id,null);
    }

    public long addNote(String title, String place, String date, String note, int gpsSetUp){

        ContentValues cValue = new ContentValues();

        cValue.put("title",title);
        cValue.put("place",place);
        cValue.put("date", date);
        cValue.put("note",note);
        cValue.put("gpsCheck",gpsSetUp);

        long id = getWritableDatabase().insert("noteItem",null, cValue);

        Log.d(KG_LOG_TITLE,"Id : " + id+" ");
        Log.d(KG_LOG_TITLE,"gpsSetUp : " + gpsSetUp);

        if (id > 0){
            Log.d(KG_LOG_TITLE,"ID 為 " + id +"新增成功");
        }else{
            Log.d(KG_LOG_TITLE,"新增失敗");
        }

        return id;
    }


    //test
    public long updateNoteById( String tableName,
                                String id,
                                String title,
                                String place,
                                String date,
                                String note,
                                int gpsSetUp){


        ContentValues cValue = new ContentValues();

        cValue.put("title",title);
        cValue.put("place",place);
        cValue.put("date", date);
        cValue.put("note",note);
        cValue.put("gpsCheck",gpsSetUp);

        Log.d(KG_LOG_TITLE,"MyDBHelper --- UpdateNoteItemByID, ID: " + id + " -- Title: " + title);

        return getWritableDatabase().update(tableName,cValue,"_ID" + "="+id,null);

    }

    public void close(){

        dbInstance.close();

    }

}
