package kenguerrilla.itl.notelocation.View.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import kenguerrilla.itl.notelocation.Model.NoteBook;
import kenguerrilla.itl.notelocation.R;


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
            }


    @Override
    public int getCount() {
        return noteBook.getNoteBookSize();
    }

    @Override
    public NoteBook.Note getItem(int i) {
        return noteBook.getNoteByArrayIndex(i);
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
                    (ImageView) myView.findViewById(R.id.iv_ic_gps)
            );

            tag.listMainTitle.setText(noteBook.getNoteByArrayIndex(i).getTitle());
            tag.listSubTitleDate.setText(noteBook.getNoteByArrayIndex(i).getDate());
            tag.listSubTitlePlace.setText(noteBook.getNoteByArrayIndex(i).getPlace());
            //tag.listSubTitleLocation.setImageResource(R.drawable);


            Log.d(KG_LOG_TITLE,noteBook.getNoteByArrayIndex(i).getGpsStatusToString());

        return myView;
    }

    public class TagView{

        TextView    listMainTitle;
        TextView    listSubTitlePlace;
        TextView    listSubTitleDate;
        ImageView   listSubTitleLocation;

        private TagView(TextView listMainTitle,
                       TextView listSubTitleDate,
                       TextView listSubTitlePlace,
                       ImageView listSubTitleLocation) {

            this.listMainTitle = listMainTitle;
            this.listSubTitlePlace = listSubTitlePlace;
            this.listSubTitleDate = listSubTitleDate;
            this.listSubTitleLocation = listSubTitleLocation;

        }

    }

}
