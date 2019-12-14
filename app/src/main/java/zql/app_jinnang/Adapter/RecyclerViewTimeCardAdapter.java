package zql.app_jinnang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.R;
import zql.app_jinnang.UserSeting;
import zql.app_jinnang.View.CalendarActivityImp;
import zql.app_jinnang.View.ListActivityImp;
import zql.app_jinnang.View.NoteinfoActivity;



public class RecyclerViewTimeCardAdapter extends RecyclerView.Adapter<RecyclerViewCardAdapter.Viewholder>{
    private ArrayList<NoteBean> notelist;
    private Context context;
    private UserSeting userSeting;
    private CalendarActivityImp calendarActivityImp;

    public RecyclerViewTimeCardAdapter(ArrayList<NoteBean>mnotelist,Context mcontext,CalendarActivityImp mcalendarActivityImp){
        Collections.reverse(mnotelist);//倒序
        this.notelist=mnotelist;
        this.context=mcontext;
        this.calendarActivityImp=mcalendarActivityImp;
        userSeting=(UserSeting)mcalendarActivityImp.getCalendarApplication();
    }

    @Override
    public RecyclerViewCardAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View iten_recycler= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclercard,parent,false);
        RecyclerViewCardAdapter.Viewholder viewholder=new RecyclerViewCardAdapter.Viewholder(iten_recycler);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewCardAdapter.Viewholder holder, int position) {
        switch (notelist.get(position).getNotetype().toString()){
            case "Vegetables":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_travel);
                holder.recycler_text_note.setText(R.string.note_travel);
                break;
            case "Fruits":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_study);
                holder.recycler_text_note.setText(R.string.note_study);
                break;
            case "Foods":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_work);
                holder.recycler_text_note.setText(R.string.note_work);
                break;
            case "Groceries":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_diary);
                holder.recycler_text_note.setText(R.string.note_diary);
                break;
            case "Others":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_live);
                holder.recycler_text_note.setText(R.string.note_live);
                break;
            default:
                break;
        }
        holder.recycler_text_note.setText(Means.getNotetextOnRecyclerCard(notelist.get(position).getNoteinfo()));
        holder.recycler_text_time.setText(notelist.get(position).getCreatetime());
        startNoteinfoActivity(holder.linearLayout,notelist.get(position));
    }

    @Override
    public int getItemCount() {
        return notelist==null ? 0 : notelist.size();
    }

    public  class Viewholder extends RecyclerView.ViewHolder{
        ImageView recycler_image_notetype,recycler_image_menu;
        TextView recycler_text_note,recycler_text_time;
        LinearLayout linearLayout;
        public Viewholder(View itemView){
            super(itemView);
            recycler_image_notetype=(ImageView)itemView.findViewById(R.id.recycler_image_notetype);
            recycler_image_menu=(ImageView)itemView.findViewById(R.id.recycler_image_menu);
            recycler_text_note=(TextView)itemView.findViewById(R.id.recycler_text_note);
            recycler_text_time=(TextView)itemView.findViewById(R.id.recycler_text_time);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.recycler_item);
        }
    }
    private void startNoteinfoActivity(View view,final NoteBean noteBean){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent=new Intent(context,NoteinfoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("noteinfo", Means.changefromNotebean(noteBean));
                mintent.putExtras(bundle);
                context.startActivity(mintent);
            }
        });
    }
}
