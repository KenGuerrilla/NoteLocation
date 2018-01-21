package kenguerrilla.itl.notelocation;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by KenGuerrilla on 2018/1/21.
 */

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.ViewHolder> {

    private NoteBook noteBook;

    private String testString = "_X_";

    public NoteRecyclerViewAdapter(Context context) {

        noteBook = NoteBook.getInstance(context);
        // 取的資料
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_view_item_layout, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.listMainTitle.setText(noteBook.getNoteBookItem(position).getTitle());
        holder.listSubplace.setText(noteBook.getNoteBookItem(position).getPlace());
        holder.listSubDate.setText(noteBook.getNoteBookItem(position).getDate());
        holder.listSubAC.setText(testString);
        holder.listSubGPS.setText(testString);

    }

    @Override
    public int getItemCount() {
        return noteBook.getNoteBookSize();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView listMainTitle, listSubDate, listSubplace, listSubAC, listSubGPS;


        public ViewHolder(View itemView) {
            super(itemView);
            listMainTitle = itemView.findViewById(R.id.note_list_main_title);
            listSubplace = itemView.findViewById(R.id.note_list_sub_title_place);
            listSubDate = itemView.findViewById(R.id.note_list_sub_title_date);
            listSubAC = itemView.findViewById(R.id.note_list_sub_title_ac);
            listSubGPS = itemView.findViewById(R.id.note_list_sub_title_gps);

        }


    }

}
