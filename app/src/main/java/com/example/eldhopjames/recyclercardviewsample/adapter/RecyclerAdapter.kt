package com.example.eldhopjames.recyclercardviewsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.eldhopjames.recyclercardviewsample.R
import com.example.eldhopjames.recyclercardviewsample.interfaces.OnItemClickListener
import com.example.eldhopjames.recyclercardviewsample.modelClass.ModelClass
import com.example.eldhopjames.recyclercardviewsample.viewHolders.EmptyViewHolder
import com.example.eldhopjames.recyclercardviewsample.viewHolders.EvenViewHolder
import com.example.eldhopjames.recyclercardviewsample.viewHolders.OddViewHolder
import java.util.*

/**
 * needs RecyclerAdapter and View Holder
 * View Holder : used to caches the data and no need to write findViewById for each items
 * RecyclerAdapter : populates the item  and data about it , data comes from n/w or from DB
 * We create an interface to pass the values of the item clicked into activity
 *
 */
class RecyclerAdapter(private val mContext: Context) : RecyclerView.Adapter<ViewHolder>() {
    private val mListItems: MutableList<ModelClass> = ArrayList()
    private var mListener: OnItemClickListener? = null

    /**
     * interface will forward our click from adapter to our main activity
     */
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder { // this method calls when ever our view method is created , ie; the instance of ViewHolder class is created
        val inflater = LayoutInflater.from(parent.context)
        val view: View
        return when (viewType) {
            0 -> {
                view = inflater
                    .inflate(R.layout.even_list_item, parent, false)
                OddViewHolder(view, mListener, mListItems)
            }
            1 -> {
                view = inflater
                    .inflate(R.layout.odd_list_item, parent, false)
                EvenViewHolder(view, mListener, mListItems)
            }
            else -> {
                view = inflater
                    .inflate(R.layout.item_empty, parent, false)
                EmptyViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (mListItems[position].type == 0) {
            return 0
        } else if (mListItems[position].type == 1) {
            return 1
        }
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) { //populate the data into the list_item (View Holder), as we scroll
        (holder as? EvenViewHolder)?.bindData()
        (holder as? OddViewHolder)?.bindData()
    }

    override fun getItemCount(): Int {
        return mListItems.size
    }

    //-------------------------------------Manipulating RecyclerView--------------------------------//
    fun clearData() {
        if (mListItems.isNotEmpty()) {
            mListItems.clear()
            notifyDataSetChanged()
        }
    }

    fun addItemRange(items: List<ModelClass>?) {
        if (!items.isNullOrEmpty()) {
            val position = mListItems.size
            mListItems.addAll(position, items)
            notifyItemRangeInserted(position, items.size)
        }
    }

    fun addItemRangeInPosition(items: List<ModelClass>?, position: Int) {
        if (!items.isNullOrEmpty()) {
            if (mListItems.size >= position) {
                mListItems.addAll(position, items)
                notifyItemRangeInserted(position, items.size)
            } else {
                mListItems.addAll(items)
                notifyItemRangeInserted(mListItems.size - 1, items.size)
            }
        }
    }

    fun addItem(item: ModelClass?, position: Int) {
        if (item != null) {
            if (mListItems.size >= position) {
                mListItems.add(position, item)
                notifyItemInserted(position)
            } else {
                mListItems.add(item)
                notifyItemInserted(mListItems.size - 1)
            }
        }
    }

    fun removeItem(position: Int) {
        if (mListItems.size > position) {
            mListItems.removeAt(position)
            notifyItemRemoved(position)
        } else {
            val newPosition = mListItems.size - 1
            mListItems.removeAt(newPosition)
            notifyItemRemoved(newPosition)
        }
    }

}