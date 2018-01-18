package kenguerrilla.itl.notelocation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by KenGuerrilla on 2017/12/24.
 */


public class NoteListAdapter extends BaseAdapter {

    private static final String AC_DATA_NOT_YET = "_V_";
    private static final String GPS_DATA_NOT_YET = "_X_";

    private static final String KG_LOG_TITLE = "KG ------ ";

    private LayoutInflater adapterLayoutInflater;

    private NoteBook noteBook;

    NoteListAdapter(Context context){
        adapterLayoutInflater = LayoutInflater.from(context);
        noteBook = NoteBook.getInstance(context);
        //noteArray = dbController.getNoteArray();
    }

/*
    void setNoteArray(ArrayList<Note> noteArray) {
        this.noteArray = noteArray;
        Log.d(KG_LOG_TITLE,"Reset Note Array");
    }
*/



    @Override
    public int getCount() {
        return noteBook.getNoteBookSize();
    }

    @Override
    public NoteBook.Note getItem(int i) {
        return noteBook.getNoteBookItem(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View myView = view;

        myView = adapterLayoutInflater.inflate(R.layout.list_view_item_layout,null);

        TagView tag;

            tag = new TagView(
                    (TextView) myView.findViewById(R.id.note_list_main_title),
                    (TextView) myView.findViewById(R.id.note_list_sub_title_date),
                    (TextView) myView.findViewById(R.id.note_list_sub_title_place),
                    (TextView) myView.findViewById(R.id.note_list_sub_title_location),
                    (TextView) myView.findViewById(R.id.note_list_sub_title_ac)
            );

            tag.listMainTitle.setText(noteBook.getNoteBookItem(i).getTitle());
            tag.listSubTitleDate.setText(noteBook.getNoteBookItem(i).getDate());
            tag.listSubTitlePlace.setText(noteBook.getNoteBookItem(i).getPlace());
            tag.listSubTitleLocation.setText(GPS_DATA_NOT_YET);
            tag.listSubTitleAc.setText(AC_DATA_NOT_YET);

        return myView;
    }

    public class TagView{

        TextView    listMainTitle;
        TextView    listSubTitlePlace;
        TextView    listSubTitleDate;
        TextView    listSubTitleAc;
        TextView    listSubTitleLocation;

        private TagView(TextView listMainTitle,
                       TextView listSubTitleDate,
                       TextView listSubTitlePlace,
                       TextView listSubTitleAc,
                       TextView listSubTitleLocation) {

            this.listMainTitle = listMainTitle;
            this.listSubTitlePlace = listSubTitlePlace;
            this.listSubTitleDate = listSubTitleDate;
            this.listSubTitleAc = listSubTitleAc;
            this.listSubTitleLocation = listSubTitleLocation;

        }

    }

}
