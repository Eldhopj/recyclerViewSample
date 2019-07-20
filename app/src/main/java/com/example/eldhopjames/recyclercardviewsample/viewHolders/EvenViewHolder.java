package com.example.eldhopjames.recyclercardviewsample.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.eldhopjames.recyclercardviewsample.R;
import com.example.eldhopjames.recyclercardviewsample.interfaces.OnItemClickListener;

//View Holder class caches these references that gonna modify in the adapter
public class EvenViewHolder extends RecyclerView.ViewHolder{
        /**Define viewHolder views (odd_list_item) here*/
         public TextView headTv;
         public TextView descTv;
         public OnItemClickListener mListener; // Listener for the OnItemClickListener interface

        //create a constructor with itemView as a params
        public EvenViewHolder(View itemView, OnItemClickListener listener) { // with the help of "itemView" we ge the views from xml
            super(itemView);
            mListener = listener;
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
