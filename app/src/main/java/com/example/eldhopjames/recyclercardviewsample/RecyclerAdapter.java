package com.example.eldhopjames.recyclercardviewsample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * needs RecyclerAdapter and View Holder
 *      View Holder : used to caches the data and no need to write findViewById for each items
 *      RecyclerAdapter : populates the item  and data about it , data comes from n/w or from DB
 * We create an interface to pass the values of the item clicked into activity
 *
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> { //RecyclerAdapter


    private List<ModelClass> mListItems; // List
    private Context mContext;
    private OnItemClickListener mListener; // Listener for the OnItemClickListener interface

    //constructor
    public RecyclerAdapter( Context context) { // constructor
        this.mContext = context;
    }

    /**
     * interface will forward our click from adapter to our main activity
     */
    public interface OnItemClickListener {
         void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {// this method calls when ever our view method is created , ie; the instance of ViewHolder class is created
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false); /**list_item-> is the Card view which holds the data in the recycler view*/

//                //NOTE : use this if items height and width not following the match_parent or wrap_content
//                RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//                view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//populate the data into the list_item (View Holder), as we scroll
        /**Binding data to the list_item*/
        ModelClass listitem = mListItems.get(position);
        if ( listitem != null) {
            holder.headTv.setText(listitem.getHead());
            holder.descTv.setText(listitem.getDesc());
        }
    }

    @Override
    public int getItemCount() { // return the size of the list view , NOTE : this must be a fast process
        if (mListItems == null) {
            return 0;
        }
        return mListItems.size();
    }

    //View Holder class caches these references that gonna modify in the adapter
    class ViewHolder extends RecyclerView.ViewHolder{
        /**Define viewHolder views (list_item) here*/
        TextView headTv;
        TextView descTv;

        //create a constructor with itemView as a params
        ViewHolder(View itemView) { // with the help of "itemView" we ge the views from xml
            super(itemView);
            //bind views
            headTv = itemView.findViewById(R.id.heading);
            descTv = itemView.findViewById(R.id.description);

            /**Assigning on click listener on the item*/
            itemView.setOnClickListener(new View.OnClickListener() { // we can handle the click as like we do in normal
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition(); // Get the index of the view holder
                        if (position != RecyclerView.NO_POSITION) { // Makes sure this position is still valid
                            mListener.onItemClick(v,position); // we catch the click on the item view then pass it over the interface and then to our activity
                        }
                    }

                }
            });
        }
    }

    //-------------------------------------Manipulating RecyclerView--------------------------------//
    public void setData(List<ModelClass> items) {
        mListItems = items;
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        mListItems.remove(position);

        //notifyDataSetChanged refreshes the entire recycler view rather than updating it
        //mRecyclerAdapter.notifyDataSetChanged();

        //If we know the position to be removed use
        notifyItemRemoved(position);
    }

    public void addItem(int position,ModelClass modelClass){
        mListItems.add( position,modelClass);

        //notifyDataSetChanged refreshes the entire recycler view rather than updating it
        //mRecyclerAdapter.notifyDataSetChanged();

        //If we know the position to be inserted use
        notifyItemInserted(position);
    }
}
