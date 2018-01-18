package kenguerrilla.itl.notelocation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by KenGuerrilla on 2018/1/10.
 */

public class MyDBHelper extends SQLiteOpenHelper {


    private final static String DATABASE = "notes.db";
    private final static int DATABASE_VER = 1;


    private static MyDBHelper instance = null;


    public static MyDBHelper getInstance(Context context){

        if(instance == null){

            instance = new MyDBHelper(context,DATABASE,null,DATABASE_VER);

        }

        return instance;
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
}
