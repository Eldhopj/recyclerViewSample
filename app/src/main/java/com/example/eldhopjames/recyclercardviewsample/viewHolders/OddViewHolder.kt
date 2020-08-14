package com.example.eldhopjames.recyclercardviewsample.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.eldhopjames.recyclercardviewsample.modelClass.ModelClass
import kotlinx.android.synthetic.main.odd_list_item.view.*

class OddViewHolder(
    itemView: View, // with the help of "itemView" we ge the views from xml
    private var mListener: ((ModelClass, Int) -> Unit)?,
    private var mListItems: MutableList<ModelClass>
) :
    ViewHolder(itemView), View.OnClickListener {

    private val heading: TextView = itemView.heading
    private val description: TextView = itemView.description


    init {
        itemView.setOnClickListener(this)
    }

    //Binding of data happens in here
    fun bindData() {
        val position = adapterPosition
        if (position == RecyclerView.NO_POSITION) {
            return
        }
        val listItem = mListItems[position]
        heading.text = listItem.head
        description.text = listItem.desc
    }

    override fun onClick(v: View) {
        val position = adapterPosition // Get the index of the view holder
        if (position == RecyclerView.NO_POSITION) { // Makes sure this position is still valid
            return
        }
        /**
         * All the clicks will come in here*/
        if (v === itemView) {
            mListener?.invoke(
                mListItems[position],
                position
            ) // we catch the click on the item view then pass it over the interface and then to our activity
        }
    }

}