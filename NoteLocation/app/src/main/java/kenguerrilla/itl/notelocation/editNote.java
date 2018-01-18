package kenguerrilla.itl.notelocation;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class editNote extends AppCompatActivity {

    private MyDBHelper dbHelper;

    String title, note, place, date, longitude="0", latitude="0";

    String noteCreatTime, noteCreatDate;

    int alarmSetUp = 0;
    int gpsSetUp = 0;

    Toolbar editPageToolbar;
    TextView editPageToolbarCancelView;

    TextView tvSetGPS;
    EditText etEditTitle, etEditPlace, etEditNote;
    CheckBox cbGPS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        dbHelper = MyDBHelper.getInstance(this);

        setupToolBar();

        initView();

        initOnClickListener();

    }


    private void initView() {

        tvSetGPS = findViewById(R.id.tv_gps_set);
        etEditTitle = findViewById(R.id.et_edit_title);
        etEditPlace = findViewById(R.id.et_edit_place);
        etEditNote = findViewById(R.id.et_edit_note);
        cbGPS = findViewById(R.id.cb_GPS);

    }

    private void initOnClickListener() {

        tvSetGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // GPS Function

            }
        });

        cbGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cbGPS.isChecked()){

                    gpsSetUp = 1;

                }
                else{

                    gpsSetUp = 0;

                }

            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {

            Toast.makeText(editNote.this,"Done",Toast.LENGTH_SHORT).show();

            packUpNoteToSQLite();

            backToMainListWithNothing();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setupToolBar(){

        editPageToolbar = findViewById(R.id.edit_page_toolbar);
        setSupportActionBar(editPageToolbar);
        editPageToolbar.setTitle(null);

        editPageToolbarCancelView = findViewById(R.id.cancel_text_view);

        editPageToolbarCancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backToMainListWithNothing();

            }
        });


    }

    private void backToMainListWithNothing(){

        Intent intent = new Intent();

        intent.setClass(editNote.this ,MainList.class );

        startActivity(intent);

    }

            /*
        ======== TABLE ========
        id          -- INTEGER
        title       -- VARCHAR
        note        -- VARCHAR
        place       -- VARCHAR
        date        -- VARCHAR
        alarm       -- VARCHAR
        longitude   -- VARCHAR
        latitude    -- VARCHAR
        alarmCheck  -- INTEGER
        gpsCheck    -- INTEGER
        =======================
         */

    private void packUpNoteToSQLite() {

        ContentValues cValue = new ContentValues();

        title = etEditTitle.getText().toString();
        note = etEditNote.getText().toString();
        place = etEditPlace.getText().toString();

        date = dateGetter();

        cValue.put("title",title);
        cValue.put("place",place);
        cValue.put("date",date);
        cValue.put("note",note);
        cValue.put("gpsCheck",gpsSetUp);

        long id = dbHelper.getWritableDatabase().insert("noteItem",null,cValue);

        Log.d("Add Value, id: ",id+" ");

        if (id > 0){
            Toast.makeText(this, "新增完成", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "新增失敗", Toast.LENGTH_SHORT).show();
        }

    }


    private String timeGetter(){

        Calendar c = Calendar.getInstance();

        SimpleDateFormat sdft = new SimpleDateFormat("HH-mm");

        noteCreatTime = sdft.format(c.getTime());

        Log.d("Get time", noteCreatTime);

        return noteCreatTime;

    }


    private String dateGetter(){

        Calendar c = Calendar.getInstance();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");

        noteCreatDate = sdfd.format(c.getTime());

        Log.d("KG Log ------- Get Date",noteCreatDate);

        return noteCreatDate;
    }


}
