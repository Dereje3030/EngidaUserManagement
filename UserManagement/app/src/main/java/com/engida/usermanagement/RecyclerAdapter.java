package com.engida.usermanagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.myViewHolder>{
     List<ModelClass>list;
     Context context;
     EngidaDB engidaDB;
    public RecyclerAdapter(Context context,List<ModelClass>list){
       this.context=context;
        this.list=list;
        engidaDB=new EngidaDB(context);
    }
    @Override
    public myViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View textView=LayoutInflater.from(parent.getContext()).inflate(R.layout.text_views_layout,parent,false);
        myViewHolder holder=new myViewHolder(textView);

        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder,final int position) {
        holder.Fullname.setText(list.get(position).getFname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, list.get(position).getFname(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, ViewMemberInfo.class);
                intent.putExtra("user", (ModelClass)list.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    public  class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView Fullname;

        public myViewHolder(View itemView) {
            super(itemView);
            Fullname=(TextView)itemView.findViewById(R.id.name);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context,ViewMemberInfo.class);
            intent.putExtra("p",getAdapterPosition());
            context.startActivity(intent);
        }


        @Override
        public boolean onLongClick(View view) {
           list.remove(getAdapterPosition());
            engidaDB.delete(getAdapterPosition());
            notifyDataSetChanged();
            return true;
        }
    }
}
