package kenguerrilla.itl.notelocation;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by KenGuerrilla on 2018/1/19.
 */

public class NoteBook {

    public static final String KG_LOG_TITLE = "KG ------ ";
    private static final String NOTE_TABLE_NAME = "noteItem";

    private static NoteBook instance = null;

    private DBController dbController;
    private ArrayList<Note> noteListArray;

    String noteCreateTime, noteCreateDate;

    private NoteBook(Context context){

        noteListArray = new ArrayList<>();
        dbController = new DBController(context);
        updateNoteArrayList();

    }


    public static NoteBook getInstance(Context context){

        if(instance == null){
            instance = new NoteBook(context);
        }
        return instance;
    }


    public int getNoteBookSize(){
        return noteListArray.size();
    }

    public Note getNoteBookItem(int i){
        return noteListArray.get(i);
    }

    public void deleteNoteItem(String id){
        dbController.deleteNoteById(NOTE_TABLE_NAME,id);
        updateNoteArrayList();
    }

    public void addNoteItem(String title, String place, String note, int gpsSetUp){

        String date = dateGetter();
        dbController.addNote(title, place, date, note, gpsSetUp);
        updateNoteArrayList();

    }

    private void updateNoteArrayList() {
        noteListArray = getNoteArray(dbController.getNoteItemCursor());
    }

    private String timeGetter(){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdft = new SimpleDateFormat("HH-mm");
        noteCreateTime = sdft.format(c.getTime());
        Log.d("Get time", noteCreateTime);
        return noteCreateTime;

    }


    private String dateGetter(){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        noteCreateDate = sdfd.format(c.getTime());
        Log.d("KG Log ------- Get Date",noteCreateDate);
        return noteCreateDate;
    }


    public ArrayList<Note> getNoteArray(Cursor cursor){

        noteListArray.clear(); // 確保更新資料前 noteArray 為空，避免混入舊資料。
        cursor.moveToFirst();

        do{
            noteListArray.add(
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

        Log.d(KG_LOG_TITLE,"Array have " + noteListArray.size() +" Item");
        return noteListArray;
    }


    public class Note {

        private String dataBaseId;
        private String title;
        private String note;
        private String place;
        private String date;
        private String alarm;
        private String longitude;
        private String latitude;
        private int alarmCheck;
        private int gpsCheck;

        public Note(    String dataBaseId, String title,
                        String note,String place,
                        String date, String alarm,
                        String longitude, String latitude,
                        int alarmCheck, int gpsCheck
        ){

            this.dataBaseId = dataBaseId;
            this.title = title;
            this.note = note;
            this.place = place;
            this.date = date;
            this.alarm = alarm;
            this.longitude = longitude;
            this.latitude = latitude;
            this.alarmCheck = alarmCheck;
            this.gpsCheck = gpsCheck;

        }


        String getDataBaseId() {
            return dataBaseId;
        }

        String getTitle() {
            return title;
        }

        void setTitle(String title) {
            this.title = title;
        }

        String getNote() {
            return note;
        }

        void setNote(String note) {
            this.note = note;
        }

        String getDate() {
            return date;
        }

        void setDate(String date) {
            this.date = date;
        }

        String getPlace() {
            return place;
        }

        void setPlace(String place) {
            this.place = place;
        }

        String getAlarm() {
            return alarm;
        }

        void setAlarm(String alarm) {
            this.alarm = alarm;
        }

        String getLongitude() {
            return longitude;
        }

        void setLongitude(String logitude) {
            this.longitude = logitude;
        }

        String getLatitude() {
            return latitude;
        }

        void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        int isAlarmCheck() {
            return alarmCheck;
        }

        void setAlarmCheck(int alarmCheck) {
            this.alarmCheck = alarmCheck;
        }

        int isGpsCheck() {
            return gpsCheck;
        }

        void setGpsCheck(int gpsCheck) {
            this.gpsCheck = gpsCheck;
        }


    }


}
