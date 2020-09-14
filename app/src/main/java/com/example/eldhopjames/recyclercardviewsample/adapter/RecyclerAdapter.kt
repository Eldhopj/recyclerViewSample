package com.example.eldhopjames.recyclercardviewsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.eldhopjames.recyclercardviewsample.databinding.EvenListItemBinding
import com.example.eldhopjames.recyclercardviewsample.databinding.ItemEmptyBinding
import com.example.eldhopjames.recyclercardviewsample.databinding.OddListItemBinding
import com.example.eldhopjames.recyclercardviewsample.modelClass.ModelClass
import com.example.eldhopjames.recyclercardviewsample.viewHolders.EmptyViewHolder
import com.example.eldhopjames.recyclercardviewsample.viewHolders.EvenViewHolder
import com.example.eldhopjames.recyclercardviewsample.viewHolders.OddViewHolder

/**
 * needs RecyclerAdapter and View Holder
 * View Holder : used to caches the data and no need to write findViewById for each items
 * RecyclerAdapter : populates the item  and data about it , data comes from n/w or from DB
 * We create an interface to pass the values of the item clicked into activity
 *
 */
class RecyclerAdapter(private val context: Context) :
    ListAdapter<ModelClass, ViewHolder>(DiffCallback()) {
    private var listener: ((ModelClass, Int) -> Unit)? = null

    /**
     * lambda will forward our click from adapter to our activity/fragment
     * NOTE : we can pass lambda via constructor of the adapter also
     */
    fun setOnContentClickListener(listener: (ModelClass, Int) -> Unit) {
        this.listener = listener
    }

    class DiffCallback : DiffUtil.ItemCallback<ModelClass>() {
        override fun areItemsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean =
            oldItem.primaryKey == newItem.primaryKey

        override fun areContentsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder { // this method calls when ever our view method is created , ie; the instance of ViewHolder class is created
        val inflater = LayoutInflater.from(context)
        val binding: Any
        return when (viewType) {
            0 -> {
                binding = EvenListItemBinding.inflate(
                    inflater,
                    parent, false
                )
                EvenViewHolder(binding, listener)
            }
            1 -> {
                binding = OddListItemBinding.inflate(
                    inflater,
                    parent, false
                )
                OddViewHolder(binding, listener)
            }
            else -> {
                binding = ItemEmptyBinding.inflate(
                    inflater,
                    parent, false
                )
                EmptyViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (getItem(position).type == 0) {
            return 0
        } else if (getItem(position).type == 1) {
            return 1
        }
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) { //populate the data into the list_item (View Holder), as we scroll
        (holder as? EvenViewHolder)?.bindData(getItem(position))
        (holder as? OddViewHolder)?.bindData(getItem(position))
    }


    //-------------------------------------Manipulating RecyclerView--------------------------------//

    /**
     * Submitting a list into a particular position (if position is not valid, we will append at the end) or appending at the end
     * */
    fun submitListItem(newDataList: MutableList<ModelClass>?, position: Int = itemCount) {
        if (!newDataList.isNullOrEmpty()) {
            val newItems = ArrayList<ModelClass>()
            newItems.addAll(currentList)
            if (itemCount >= position) {
                newItems.addAll(position, newDataList)
            } else {
                newItems.addAll(newDataList)
            }
            submitList(newItems)
        }
    }

    /**
     * clearing of data from adapter
     * */
    fun clearData() {
        if (currentList.isNotEmpty()) {
            val newItems = ArrayList<ModelClass>()
            submitList(newItems)
        }
    }

    /**
     * Submitting a object into a particular position (if position is not valid, we will append at the end)or appending at the end
     * */
    fun addOrReplaceItem(newData: ModelClass?, position: Int = itemCount) {
        if (newData != null) {
            val newItems = ArrayList<ModelClass>()
            newItems.addAll(currentList)
            if (itemCount > position) {
                newItems.add(position, newData)
            } else {
                newItems.add(newData)
            }
            submitList(newItems)
        }
    }

    /**
     * clearing of an object from a particular position (if position is not valid, we will remove from the end) or from last adapter
     * */
    fun removeItem(position: Int = itemCount - 1) {
        val newItems = ArrayList<ModelClass>()
        newItems.addAll(currentList)
        if (itemCount > position) {
            newItems.removeAt(position)
        } else {
            newItems.removeAt(itemCount - 1)
        }
        submitList(newItems)
    }

}