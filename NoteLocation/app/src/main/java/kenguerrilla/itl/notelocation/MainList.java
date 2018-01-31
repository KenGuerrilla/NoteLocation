package kenguerrilla.itl.notelocation;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;


public class MainList extends AppCompatActivity {

    public static final String KG_LOG_TITLE = "KG ------ ";

    public static final String NEW_NOTE = "-1";

    FloatingActionButton fab;
    Toolbar toolbar;

    RecyclerView noteRecyclerView;

    NoteBook noteBook = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        findViewId();
        setSupportActionBar(toolbar);
        initialize();

        setNoteRecyclerViewAdapter();
        setOnClickListener();

    }

    private void initialize() {

        noteBook = NoteBook.getInstance(this);

    }

    private void setNoteRecyclerViewAdapter(){

        NoteRecyclerViewAdapter adapter = new NoteRecyclerViewAdapter(this);

        noteRecyclerView.setAdapter(adapter);

        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



    private void findViewId() {

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        noteRecyclerView = findViewById(R.id.main_recycler_view);

    }


    private void setOnClickListener() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent.setClass(MainList.this, EditNote.class);
                bundle.putString("NOTE_ID",NEW_NOTE);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            justFunDialog();
            return true;
        }

        if(id == R.id.action_about){

            aboutMeDialog();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void aboutMeDialog(){

        final AlertDialog.Builder aboutDialog = new AlertDialog.Builder(this);
        aboutDialog.setTitle(R.string.about_me_title);
        aboutDialog.setMessage(R.string.about_me_msg);
        aboutDialog.setCancelable(false);

        aboutDialog.setPositiveButton(R.string.button_OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        aboutDialog.show();

    }

    private void justFunDialog(){

        final AlertDialog.Builder aboutDialog = new AlertDialog.Builder(this);
        aboutDialog.setTitle(R.string.fun_dialog_title);
        aboutDialog.setMessage(R.string.fun_dialog_msg);
        aboutDialog.setCancelable(false);

        aboutDialog.setPositiveButton(R.string.fun_dialog_ans, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        aboutDialog.show();

    }

}
