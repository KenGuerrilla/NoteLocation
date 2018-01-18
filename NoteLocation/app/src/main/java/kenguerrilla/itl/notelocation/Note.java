package kenguerrilla.itl.notelocation;

/**
 * Created by KenGuerrilla on 2017/12/24.
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
