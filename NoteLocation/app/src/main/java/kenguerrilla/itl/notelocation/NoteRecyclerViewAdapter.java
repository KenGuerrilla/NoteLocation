package kenguerrilla.itl.notelocation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

/**
 * Created by KenGuerrilla on 2018/1/21.
 */

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.ViewHolder> {

    private NoteBook noteBook;
    public static final String KG_LOG_TITLE = "KG ------ ";
    private String testString = "_X_";
    private Context context;

    private String intentNoteID = "-1";


    public NoteRecyclerViewAdapter(Context context) {
        this.context = context;
        noteBook = NoteBook.getInstance(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_view_item_layout, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.listMainTitle.setText(noteBook.getNoteByArrayIndex(position).getTitle());
        holder.listSubPlace.setText(noteBook.getNoteByArrayIndex(position).getPlace());
        holder.listSubDate.setText(noteBook.getNoteByArrayIndex(position).getDate());
        //holder.listSubGPS.setImageResource();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showNote(v,position);

            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Log.d(KG_LOG_TITLE,"Item " + position + "Long press");

                PopupMenu popupmenu = new PopupMenu(v.getContext(), v);
                popupmenu.inflate(R.menu.main_list_popup_menu);

                if(context == null){

                    context = v.getContext();

                }

                //listViewPosition = position;
                popupmenu.show();

                Log.d("KG ------ :","Long Click");

                popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){

                            case R.id.popup_edit:

                                Log.d(KG_LOG_TITLE,"Popup Edit");

                                editNote(context, noteBook.getNoteByArrayIndex(position).getDataBaseId());

                                notifyDataSetChanged();

                                break;

                            case R.id.popup_turn_off:

                                Log.d(KG_LOG_TITLE,"Popup On and Off");

                                break;

                            case R.id.popup_delete:

                                Log.d(KG_LOG_TITLE,"PopupMenu Delete ID : " + noteBook.getNoteByArrayIndex(position).getDataBaseId());

                                deleteNote(noteBook.getNoteByArrayIndex(position).getDataBaseId());

                                notifyDataSetChanged();
                                break;

                        }
                        return true;
                    }
                });

                return true;
            }
        });

    }

    private void editNote(Context context, String id){

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(context,EditNote.class);

        Log.d(KG_LOG_TITLE,"Note Info Intent note ID:"+id);

        bundle.putString("NOTE_ID",id);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    private void deleteNote(String id){

        noteBook.deleteNoteItemById(id);

    }

    private void showNote(View v, int position){

        Log.d(KG_LOG_TITLE,"Item " + position + "press ");

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(context,NoteInfo.class);
        bundle.putString("NOTE_ID",noteBook.getNoteByArrayIndex(position).getDataBaseId());
        bundle.putInt("NOTE_ARRAY_POSITION",position);
        intent.putExtras(bundle);
        v.getContext().startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return noteBook.getNoteBookSize();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView listMainTitle, listSubDate, listSubPlace;

        private final ImageView listSubGPS;

        public ViewHolder(View itemView) {
            super(itemView);
            listMainTitle = itemView.findViewById(R.id.note_list_main_title);
            listSubPlace = itemView.findViewById(R.id.note_list_sub_title_place);
            listSubDate = itemView.findViewById(R.id.note_list_sub_title_date);
            listSubGPS = itemView.findViewById(R.id.iv_ic_gps);

        }

    }

}
