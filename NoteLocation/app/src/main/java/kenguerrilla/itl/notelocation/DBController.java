package kenguerrilla.itl.notelocation;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by KenGuerrilla on 2018/1/18.
 */

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



    class DBController {

    private static final String KG_LOG_TITLE = "KG ------ ";

    private MyDBHelper dbHelper;
    private Cursor cursor;

    private ArrayList<Note> noteArray = new ArrayList<>();


    DBController(Context context){

        dbHelper = MyDBHelper.getInstance(context);

    }


    Cursor getNoteItemCursor() {

        cursor = dbHelper.getReadableDatabase().query("noteItem",
                null,null,
                null,null,
                null,null);

        return cursor;
    }

    void deleteNoteItem(String tableName, String id){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(tableName,"_id =" + id,null);
        db.close();

    }


    ArrayList<Note> getNoteArray(){

        noteArray.clear(); // 確保更新資料前 noteArray 為空，避免混入舊資料。

        cursor = getNoteItemCursor();
        cursor.moveToFirst();

        do{
            noteArray.add(
                    new Note(   cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getInt(8),
                            cursor.getInt(9)
                    )

            );

        } while (cursor.moveToNext());

        Log.d(KG_LOG_TITLE,"Array have " + noteArray.size() +" Item");
        return noteArray;
    }

}
