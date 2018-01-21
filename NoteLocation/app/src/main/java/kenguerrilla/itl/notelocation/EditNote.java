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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class EditNote extends AppCompatActivity {


    //boolean alarmSetUp = true;
    boolean gpsSetUp = false;

    Toolbar editPageToolbar;
    TextView editPageToolbarCancelView;

    TextView tvSetGPS;
    EditText etEditTitle, etEditPlace, etEditNote;
    Switch shGPS;

    private NoteBook noteBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        noteBook = NoteBook.getInstance(this);

        setupToolBar();

        initView();

        initOnClickListener();

    }


    private void initView() {

        tvSetGPS = findViewById(R.id.tv_gps_set);
        etEditTitle = findViewById(R.id.et_edit_title);
        etEditPlace = findViewById(R.id.et_edit_place);
        etEditNote = findViewById(R.id.et_edit_note);
        shGPS = findViewById(R.id.sh_gps);



    }

    private void initOnClickListener() {

        tvSetGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // GPS Function

            }
        });



        shGPS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){


                }else{


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

            Toast.makeText(EditNote.this,"Done",Toast.LENGTH_SHORT).show();

            packUpNoteToNoteBook();

            changeActivity();

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

                changeActivity();

            }
        });


    }

    // Activity 切換的方式還要再調整
    private void changeActivity(){

        Intent intent = new Intent();

        intent.setClass(EditNote.this ,MainList.class );

        startActivity(intent);

    }


    private void packUpNoteToNoteBook() {

        noteBook.addNoteItem(   etEditTitle.getText().toString(),
                                etEditPlace.getText().toString(),
                                etEditNote.getText().toString(),
                                gpsSetUp);

    }

}
