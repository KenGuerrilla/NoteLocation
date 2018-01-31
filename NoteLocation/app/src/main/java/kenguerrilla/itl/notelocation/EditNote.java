package kenguerrilla.itl.notelocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class EditNote extends AppCompatActivity {

    //boolean alarmSetUp = true;
    boolean gpsStatusBoolean = false;

    public static final int NEW_NOTE_INDEX = -1;
    public static final String NEW_NOTE_ID = "-1";
    public static final String KG_LOG_TITLE = "KG ------ ";

    Toolbar editPageToolbar;
    TextView editToolbarCancelView;

    TextView tvSetGPS;
    EditText etEditTitle, etEditPlace, etEditNote;
    Switch shGPS;

    private NoteBook noteBook;

    //private int noteArrayIndex = -1;

    private String noteID = "-1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        initNote();
        initView();
        printNote();
        initOnClickListener();

    }


    public void initNote() {

        noteBook = NoteBook.getInstance(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        try {

            if (bundle.getString("NOTE_ID") != null) {

                noteID = bundle.getString("NOTE_ID");

                Log.d(KG_LOG_TITLE, "NOTE ID: " + noteID);
            }

        }
        catch (NullPointerException ex){

            Log.d(KG_LOG_TITLE,"!!! Exception !!! EditNote.java -- printNote Throw NullPointer: " + ex);
            Toast.makeText(this,"Data Error with Exception",Toast.LENGTH_SHORT);

        }
        catch (Exception ex){

            Log.d(KG_LOG_TITLE,"!!! Exception !!! EditNote.java -- printNote Throw Exception: " + ex);
            Toast.makeText(this,"Error with Exception",Toast.LENGTH_SHORT);

        }

    }

    public void initView() {

        tvSetGPS = findViewById(R.id.tv_gps_set);
        etEditTitle = findViewById(R.id.et_edit_title);
        etEditPlace = findViewById(R.id.et_edit_place);
        etEditNote = findViewById(R.id.et_edit_note);
        shGPS = findViewById(R.id.sh_gps);

        editPageToolbar = findViewById(R.id.edit_page_toolbar);
        setSupportActionBar(editPageToolbar);
        editPageToolbar.setTitle(null);

        editToolbarCancelView = findViewById(R.id.tv_edit_toolbar_cancel);

    }

    private void initOnClickListener() {

        editToolbarCancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

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

            Toast.makeText(EditNote.this,"Press Done",Toast.LENGTH_SHORT).show();

            packUpNoteToNoteBook();

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Activity 切換的方式還要再調整
    private void changeActivity(){

        Intent intent = new Intent();

        intent.setClass(EditNote.this ,MainList.class );

        startActivity(intent);

    }


    private void packUpNoteToNoteBook() {

        String nullString = "";

        if(nullString.equals(etEditTitle.getText().toString())){

            Toast.makeText(this, "Title Can't be Null", Toast.LENGTH_SHORT).show();

        }
        else{

            if(noteID.equals(NEW_NOTE_ID)){

                noteBook.addNoteItem(   etEditTitle.getText().toString(),
                        etEditPlace.getText().toString(),
                        etEditNote.getText().toString(),
                        gpsStatusBoolean);

            }
            else{

                noteBook.updateNoteItemById(    noteID,
                        etEditTitle.getText().toString(),
                        etEditPlace.getText().toString(),
                        etEditNote.getText().toString(),
                        gpsStatusBoolean);

            }

            changeActivity();

        }



    }

    public void printNote() {

        try {

            if (!(noteID.equals(NEW_NOTE_ID))) {

                Log.d(KG_LOG_TITLE, "Print Note ID: " + noteID);

                etEditTitle.setText(noteBook.getNoteById(noteID).getTitle());
                etEditPlace.setText(noteBook.getNoteById(noteID).getPlace());
                etEditNote.setText(noteBook.getNoteById(noteID).getNote());

                if (noteBook.getNoteById(noteID).isGpsCheck()) {
                    tvSetGPS.setText(R.string.test_string_gps_status_on);
                } else {
                    tvSetGPS.setText(R.string.test_string_gps_status_off);
                }
            }

        }
        catch (NullPointerException ex){

            Log.d(KG_LOG_TITLE,"!!! Exception !!! EditNote.java -- printNote Throw NullPointer: " + ex);
            Toast.makeText(this,"Data Error with Exception",Toast.LENGTH_SHORT);

        }
        catch (Exception ex){

            Log.d(KG_LOG_TITLE,"!!! Exception !!! EditNote.java -- printNote Throw Exception: " + ex);
            Toast.makeText(this,"Error with Exception",Toast.LENGTH_SHORT);

        }

    }

}
