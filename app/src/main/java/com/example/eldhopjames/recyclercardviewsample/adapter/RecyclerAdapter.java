package com.example.eldhopjames.recyclercardviewsample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eldhopjames.recyclercardviewsample.R;
import com.example.eldhopjames.recyclercardviewsample.interfaces.OnItemClickListener;
import com.example.eldhopjames.recyclercardviewsample.modelClass.ModelClass;
import com.example.eldhopjames.recyclercardviewsample.viewHolders.EvenViewHolder;
import com.example.eldhopjames.recyclercardviewsample.viewHolders.OddViewHolder;

import java.util.List;

/**
 * needs RecyclerAdapter and View Holder
 *      View Holder : used to caches the data and no need to write findViewById for each items
 *      RecyclerAdapter : populates the item  and data about it , data comes from n/w or from DB
 * We create an interface to pass the values of the item clicked into activity
 *
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> { //RecyclerAdapter


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

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // this method calls when ever our view method is created , ie; the instance of ViewHolder class is created
        View view;
        if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.odd_list_item, parent, false); /**odd_list_item-> is the Card view which holds the data in the recycler view*/
            return new EvenViewHolder(view, mListener);
        }
        else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.even_list_item, parent, false);
            return new OddViewHolder(view, mListener);
        }
//                //NOTE : use this if items height and width not following the match_parent or wrap_content
//                RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//                view.setLayoutParams(layoutParams);
    }

    /**This method returns which layout has to be populated*/
    @Override
    public int getItemViewType(int position) {
        if (mListItems.get(position).getType() == 0){
            return 0;
        } else if (mListItems.get(position).getType() == 1) {
            return 1;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) { //populate the data into the list_item (View Holder), as we scroll
        EvenViewHolder evenViewHolder;
        OddViewHolder oddViewHolder;

        if (holder instanceof EvenViewHolder){ // checks which viewHolder is it
            evenViewHolder = (EvenViewHolder) holder;
            setEvenViewHolder (evenViewHolder,position);
        }
        if (holder instanceof OddViewHolder){
            oddViewHolder = (OddViewHolder) holder;
            setOddViewHolder(oddViewHolder,position);
        }
    }

    @Override
    public int getItemCount() { // return the size of the list view , NOTE : this must be a fast process
        return mListItems == null ? 0 : mListItems.size();
    }

    private void setOddViewHolder(OddViewHolder oddViewHolder, int position) {
        ModelClass listItem = mListItems.get(position);
        if (listItem != null) {
            oddViewHolder.headTv.setText(listItem.getHead());
            oddViewHolder.descTv.setText(listItem.getDesc());
        }
    }

    private void setEvenViewHolder(EvenViewHolder evenViewHolder, int position) {
        ModelClass listItem = mListItems.get(position);
        if (listItem != null) {
            evenViewHolder.headTv.setText(listItem.getHead());
            evenViewHolder.descTv.setText(listItem.getDesc());
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

        //If we know the position to be inserted use
        notifyItemInserted(position);
    }
}
