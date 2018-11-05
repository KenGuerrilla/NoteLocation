package kenguerrilla.itl.notelocation.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import kenguerrilla.itl.notelocation.Model.NoteBook;
import kenguerrilla.itl.notelocation.R;

public class NoteInfo extends AppCompatActivity {

    public static final String KG_LOG_TITLE = "KG ------ ";

    TextView infoTitle, infoPlace, infoDate, infoGpsStatus, infoNote, infoToolbarCancelView;

    private String noteId = "-1";

    private int noteArrayIndex = -1;

    NoteBook noteBook;

    Toolbar infoToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_info);

        initNote();
        initView();
        initOnClickListener();
        printNote();

    }

    private void initOnClickListener() {

        infoToolbarCancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(KG_LOG_TITLE,"press Cancel");
                finish();

            }
        });

    }

    public void initNote() {

        noteBook = NoteBook.getInstance(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        noteId = bundle.getString("NOTE_ID");
        noteArrayIndex = bundle.getInt("NOTE_ARRAY_POSITION");

    }


    public void initView() {

        infoTitle = findViewById(R.id.tv_info_title);
        infoPlace = findViewById(R.id.tv_info_place);
        infoDate = findViewById(R.id.tv_info_date);
        infoGpsStatus = findViewById(R.id.tv_info_gps_status);
        infoNote = findViewById(R.id.tv_info_note);

        infoToolbar = findViewById(R.id.info_page_toolbar);
        setSupportActionBar(infoToolbar);
        infoToolbar.setTitle(null);

        infoToolbarCancelView = findViewById(R.id.tv_info_toolbar_cancel);

        infoGpsStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NoteInfo.this,MapsActivity.class);
                startActivity(intent);
            }
        });


    }


    public void printNote() {

        if(noteId.equals("-1") || (noteArrayIndex == -1)){

            Log.e(KG_LOG_TITLE,"Error, Item ID: " + noteId);

            Log.e(KG_LOG_TITLE,"Error, Note Array Index: " +noteArrayIndex);

        }
        else{

            NoteBook.Note note = noteBook.getNoteByArrayIndex(noteArrayIndex);

            infoTitle.setText(note.getTitle());
            infoPlace.setText(note.getPlace());
            infoDate.setText(note.getDate());
            infoNote.setText(note.getNote());

            if(note.isGpsCheck()){
                infoGpsStatus.setText(R.string.test_string_gps_status_on);
            }
            else{
                infoGpsStatus.setText(R.string.test_string_gps_status_off
                );
            }

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {

            editNote();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void editNote(){

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(this,EditNote.class);

        Log.d(KG_LOG_TITLE,"Note Info Intent note ID:"+noteId);

        bundle.putString("NOTE_ID",noteId);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
