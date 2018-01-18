package kenguerrilla.itl.notelocation;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

    FloatingActionButton fab;
    Toolbar toolbar;
    ListView noteListView;

    NoteListAdapter nlAdapter = null;

    private int listViewPosition;

    NoteBook noteBook = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        findViewId();
        setSupportActionBar(toolbar);

        initialize();
        setNoteListAdapter();
        setOnClickListener();

    }

    private void initialize() {

        noteBook = NoteBook.getInstance(this);

    }


    private void setNoteListAdapter() {

        nlAdapter = new NoteListAdapter(this);

        noteListView.setAdapter(nlAdapter);

    }


    private void findViewId() {

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        noteListView = findViewById(R.id.list_view_note);

    }

    private void setOnClickListener() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent();

                intent.setClass(MainList.this, EditNote.class);

                startActivity(intent);

            }
        });


        // On Click
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(KG_LOG_TITLE,"Data Base ID :" + nlAdapter.getItem(position).getDataBaseId());

                Log.d(KG_LOG_TITLE,"Note Title :" + nlAdapter.getItem(position).getTitle());

            }
        });


        // On Long Click
        noteListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                PopupMenu popupmenu = new PopupMenu(MainList.this, view);
                popupmenu.inflate(R.menu.main_list_popup_menu);

                listViewPosition = position;
                popupmenu.show();

                Log.d("KG ------ :","Long Click");

                popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){

                            case R.id.popup_edit:

                                Toast.makeText(MainList.this,"Press Edit",Toast.LENGTH_LONG).show();

                                break;

                            case R.id.popup_turn_off:

                                Toast.makeText(MainList.this,"Press Turn Off",Toast.LENGTH_LONG).show();

                                break;

                            case R.id.popup_delete:

                                Log.d(KG_LOG_TITLE,"Delete ID : "+ nlAdapter.getItem(listViewPosition).getDataBaseId());

                                // 取得 Note 物件 ID，再透過 NoteBook 物件執行刪除程序
                                //dbController.deleteNoteItem(NOTE_TABLE_NAME, nlAdapter.getItem(listViewPosition).getDataBaseId());

                                noteBook.deleteNoteItem(nlAdapter.getItem(listViewPosition).getDataBaseId());

                                // 重新給予更新過後的ArrayList，使用 setNoteArray(ArrayList<Note>)
                                // 之後再使用 notifyDataSetChanged() 刷新ListView介面

                                // 取得 Note Array ____ 取得 Note Cursor

                                // 要如何通知 NoteBook 物件更新 ArrayList 內容？
                                //nlAdapter.setNoteArray(dbController.getNoteArray());

                                nlAdapter.notifyDataSetChanged();

                                break;

                        }
                        return true;
                    }
                });
                return true;
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // =========== test Tools ============

    /*
    private void setHandler() {

        try{

            final Handler mainListHandler = new Handler();
            mainListHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    //nlAdapter.notifyDataSetChanged();
                    Log.d(KG_LOG_TITLE,"UI Notify Data Set Changed");
                }
            },60*1000);

        }catch (Exception e){

            Log.e(KG_LOG_TITLE,"UI Notify Data Set Changed Was Fail");

        }

    }



    private void setSimpleCursorAdapter() {

        noteItemAdapter =
                new SimpleCursorAdapter(this,
                        R.layout.list_view_item_layout,
                        dbController.getNoteItemCursor(),
                        new String[] {"title", "place", "date"},
                        new int[] {R.id.note_list_main_title, R.id.note_list_sub_title_place, R.id.note_list_sub_title_date},
                        0
                );

        noteListView.setAdapter(noteItemAdapter);

    }

    */
}
