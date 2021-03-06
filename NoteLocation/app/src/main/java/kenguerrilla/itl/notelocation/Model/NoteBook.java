package kenguerrilla.itl.notelocation.Model;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
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

    private MyDBHelper dbHelper;
    private ArrayList<Note> noteListArray;

    String noteCreateTime, noteCreateDate;

    private NoteBook(Context context){

        noteListArray = new ArrayList<>();
        dbHelper = MyDBHelper.getInstance(context);
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

    public Note getNoteByArrayIndex(int i){
        return noteListArray.get(i);
    }



    // 設計一個可以走訪陣列比對指定ID的方法
    public Note getNoteById(String id){

        for(Note notePointer : noteListArray){

            if(notePointer.getDataBaseId().equals(id)){

                //Log.d(KG_LOG_TITLE,"DATABASE ID: " + notePointer.getDataBaseId());

                return notePointer;
            }
        }
        return null;
    }


    public void deleteNoteItemById(String id){

        Log.d(KG_LOG_TITLE,"NoteBook Delete id: " + id );
        dbHelper.deleteNoteById(NOTE_TABLE_NAME,id);
        updateNoteArrayList();

    }

    public void updateNoteItemById(String id, String title, String place, String note, boolean gpsStatusBoolean){

        String date = dateGetter();
        int gpsStatusInt = booleanToInt(gpsStatusBoolean);
        Log.d(KG_LOG_TITLE,"NoteBook --- UpdateNoteItemByID, ID: " + id + " -- Title: " + title);
        long longId = dbHelper.updateNoteById(NOTE_TABLE_NAME, id, title, place, date, note, gpsStatusInt);
        Log.d(KG_LOG_TITLE,"Update Status ID :" + longId);
        updateNoteArrayList();

    }

    public void addNoteItem(String title, String place, String note, boolean gpsStatusBoolean){

        String date = dateGetter();
        long longId = dbHelper.addNote(title, place, date, note, booleanToInt(gpsStatusBoolean));
        Log.d(KG_LOG_TITLE,"Add Status ID :" + longId);
        updateNoteArrayList();

    }

    private void updateNoteArrayList() {
        noteListArray = getNoteArray(dbHelper.getNoteItemCursor());
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

        noteListArray.clear();
        cursor.moveToFirst();

        if(cursor.getCount() != 0){
            do{
                noteListArray.add(
                        new Note(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getString(7),
                                intToBoolean(cursor.getInt(8)),
                                intToBoolean(cursor.getInt(9))
                        )
                );


            } while (cursor.moveToNext());
        }

        Log.d(KG_LOG_TITLE,"Array have " + noteListArray.size() +" Item");
        return noteListArray;
    }


    // Integer to Boolean
    private boolean intToBoolean(int i){
        return i > 0;
    }

    // Boolean to Integer
    private int booleanToInt(boolean b){
        return b ? 1 : 0 ;
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
        private boolean alarmCheck;
        private boolean gpsCheck;

        public Note(    String dataBaseId, String title,
                        String note,String place,
                        String date, String alarm,
                        String longitude, String latitude,
                        boolean alarmCheck, boolean gpsCheck
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

        public String getGpsStatusToString(){
            if(gpsCheck){
                return "V";
            }
            else{
                return "X";
            }
        }


        public String getDataBaseId() {
            return dataBaseId;
        }

        public String getTitle() {
            return title;
        }

        void setTitle(String title) {
            this.title = title;
        }

        public String getNote() {
            return note;
        }

        void setNote(String note) {
            this.note = note;
        }

        public String getDate() {
            return date;
        }

        void setDate(String date) {
            this.date = date;
        }

        public String getPlace() {
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

        boolean isAlarmCheck() {
            return alarmCheck;
        }

        void setAlarmCheck(boolean alarmCheck) {
            this.alarmCheck = alarmCheck;
        }

        public boolean isGpsCheck() {
            return gpsCheck;
        }

        void setGpsCheck(boolean gpsCheck) {
            this.gpsCheck = gpsCheck;
        }


    }


}
