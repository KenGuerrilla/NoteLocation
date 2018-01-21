package kenguerrilla.itl.notelocation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

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

    public void deleteNoteById(String tableName, String id){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(tableName,"_id =" + id,null);
        db.close();

    }

    public long addNote(String title, String place, String date, String note, int gpsSetUp){

        ContentValues cValue = new ContentValues();

        cValue.put("title",title);
        cValue.put("place",place);
        cValue.put("date", date);
        cValue.put("note",note);
        cValue.put("gpsCheck",gpsSetUp);

        long id = dbHelper.getWritableDatabase().insert("noteItem",null,cValue);

        Log.d(KG_LOG_TITLE,"Id : " + id+" ");
        Log.d(KG_LOG_TITLE,"gpsSetUp : " + gpsSetUp);

        if (id > 0){
            Log.d(KG_LOG_TITLE,"ID 為 " + id +"新增成功");
        }else{
            Log.d(KG_LOG_TITLE,"新增失敗");
        }
        return id;
    }


}
