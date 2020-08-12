package com.example.eldhopjames.recyclercardviewsample.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.eldhopjames.recyclercardviewsample.R;
import com.example.eldhopjames.recyclercardviewsample.interfaces.OnItemClickListener;

public class OddViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    /**
     * Define viewHolder views (odd_list_item) here
     */
    public TextView headTv;
    public TextView descTv;
    private OnItemClickListener mListener; // Listener for the OnItemClickListener interface

    //create a constructor with itemView as a params
    public OddViewHolder(View itemView, OnItemClickListener listener) { // with the help of "itemView" we ge the views from xml
        super(itemView);
        mListener = listener;
        //bind views
        headTv = itemView.findViewById(R.id.heading);
        descTv = itemView.findViewById(R.id.description);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            int position = getAdapterPosition(); // Get the index of the view holder
            if (position != RecyclerView.NO_POSITION) { // Makes sure this position is still valid
                if (v == itemView) {
                    mListener.onItemClick(position); // we catch the click on the item view then pass it over the interface and then to our activity
                }
            }
        }
    }
}
