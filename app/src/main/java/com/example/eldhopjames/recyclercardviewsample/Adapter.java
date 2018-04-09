package com.example.eldhopjames.recyclercardviewsample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * needs Apdater and View Holder
 * check the comment "for on click event on the recycler view" for to know the code changes for onclick on recyclerView
 */
public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> { //Adapter

    /**ModelClass and its constructor*/
    List<ModelClass> listItems; // List
    private Context context;

    public Adapter(List<ModelClass> listItems, Context context) { // constructor
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {// this method calls when ever our view method is created , ie; the instance of ViewHolder class is created
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false); /**list_item-> is the Card view which holds the data in the recycler view*/
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//binds the data to the list_item (View Holder)
        /**Binding data to the list_item*/
        ModelClass listitem = listItems.get(position);
        holder.headTv.setText(listitem.getHead());
        holder.descTv.setText(listitem.getDesc());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() { // for on click event on the recycler view
            @Override
            public void onClick(View v) {
                // pass the values into another activity using intent value passing methods
            }
        });
    }

    @Override
    public int getItemCount() { // return the size of the list view
        /**Size of the list item*/
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder { //View Holder
        /**Define viewHolder views (list_item) here*/
        public TextView headTv;
        public TextView descTv;
        public LinearLayout linearLayout; // for on click event on the recycler view

        public ViewHolder(View itemView) { // with the help of "itemView" we ge the views from xml
            super(itemView);
            /**bind views*/
            headTv = itemView.findViewById(R.id.heading);
            descTv = itemView.findViewById(R.id.description);
            linearLayout = itemView.findViewById(R.id.clickme);// for on click event on the recycler view
        }
    }
}
