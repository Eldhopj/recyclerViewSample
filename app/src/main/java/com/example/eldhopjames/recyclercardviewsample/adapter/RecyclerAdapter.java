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
import com.example.eldhopjames.recyclercardviewsample.viewHolders.EmptyViewHolder;
import com.example.eldhopjames.recyclercardviewsample.viewHolders.EvenViewHolder;
import com.example.eldhopjames.recyclercardviewsample.viewHolders.OddViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * needs RecyclerAdapter and View Holder
 *      View Holder : used to caches the data and no need to write findViewById for each items
 *      RecyclerAdapter : populates the item  and data about it , data comes from n/w or from DB
 * We create an interface to pass the values of the item clicked into activity
 *
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<ModelClass> mListItems = new ArrayList<>();
    private Context mContext;
    private int totalListItems = 0;
    private OnItemClickListener mListener;

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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case 0:
                view = inflater
                        .inflate(R.layout.even_list_item, parent, false);
                return new OddViewHolder(view, mListener);
            case 1:
                view = inflater
                        .inflate(R.layout.odd_list_item, parent, false);
                return new EvenViewHolder(view, mListener);
            default:
                view = inflater
                        .inflate(R.layout.item_empty, parent, false);
                return new EmptyViewHolder(view);


//                //NOTE : use this if items height and width not following the match_parent or wrap_content
//                RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//                view.setLayoutParams(layoutParams);
        }
    }

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
    public int getItemCount() {
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
    public void clearData() {
        if (!mListItems.isEmpty()) {
            mListItems.clear();
            notifyDataSetChanged();
            totalListItems = 0;
        }
    }

    public void addItemRange(List<ModelClass> items) {
        if (items != null) {
            int position = totalListItems;
            mListItems.addAll(position, items);
            notifyItemRangeInserted(position, items.size()); //items.size()+1 to disable auto scroll
            totalListItems = mListItems.size();
        }
    }

    public void addItemRangeInPosition(List<ModelClass> items, int position) {
        if (items != null) {
            if (totalListItems >= position) {
                mListItems.addAll(position, items);
                notifyItemRangeInserted(position, items.size());
            } else {
                mListItems.addAll(items);
                notifyItemRangeInserted(totalListItems - 1, items.size());
            }
            totalListItems = mListItems.size();
        }
    }

    public void addItem(ModelClass item, int position) {
        if (item != null) {
            if (totalListItems >= position) {
                mListItems.add(position, item);
                notifyItemInserted(position);
            } else {
                mListItems.add(item);
                notifyItemInserted(totalListItems - 1);
            }
            ++totalListItems;
        }
    }

    public void removeItem(int position) {
        if (totalListItems >= position) {
            mListItems.remove(position);
            notifyItemRemoved(position);
        } else {
            int newPosition = totalListItems - 1;
            mListItems.remove(newPosition);
            notifyItemRemoved(newPosition);
        }
        --totalListItems;
    }
}
