package com.example.eldhopjames.recyclercardviewsample.adapter

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
class RecyclerAdapter :
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
        val inflater = LayoutInflater.from(parent.context)
        val binding: Any
        return when (viewType) {
            VIEW_TYPE_ODD -> {
                binding = EvenListItemBinding.inflate(
                    inflater,
                    parent, false
                )
                EvenViewHolder(binding, listener)
            }
            VIEW_TYPE_EVEN -> {
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

    //TODO: Migrate in favor of merge adapter
    override fun getItemViewType(position: Int): Int {
        if (getItem(position).type == 0) {
            return VIEW_TYPE_ODD
        } else if (getItem(position).type == 1) {
            return VIEW_TYPE_EVEN
        }
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) { //populate the data into the list_item (View Holder), as we scroll
        when (holder) {
            is EvenViewHolder -> holder.bindData(getItem(position))
            is OddViewHolder -> holder.bindData(getItem(position))
        }
    }


    //-------------------------------------Manipulating RecyclerView--------------------------------//

    /**
     * Submitting a arrayList into a particular position (if position is not valid, we will append at the end) by default appending at the end
     * */
    fun submitListItem(newDataList: MutableList<ModelClass>?, position: Int = itemCount) {
        if (!newDataList.isNullOrEmpty()) {
            val newItems: MutableList<ModelClass> = currentList.toMutableList()
            if (itemCount >= position) {
                newItems.addAll(position, newDataList)
            } else {
                newItems.addAll(newDataList)
            }
            submitList(newItems)
        }
    }

    /**
     * clearing existing list and submit fresh list
     * */
    fun replaceData(newDataList: MutableList<ModelClass>?) {
        if (!newDataList.isNullOrEmpty()) {
            submitList(newDataList)
        }
    }

    /**
     * Submitting a object into a particular position (if position is not valid, we will append at the end)or appending at the end
     * */
    fun addItem(newData: ModelClass?, position: Int = itemCount) {
        if (newData != null) {
            val newItems: MutableList<ModelClass> = currentList.toMutableList()
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
        val newItems: MutableList<ModelClass> = currentList.toMutableList()
        if (itemCount > position) {
            newItems.removeAt(position)
        } else {
            newItems.removeAt(itemCount - 1)
        }
        submitList(newItems)
    }

    companion object {
        private const val VIEW_TYPE_ODD = 1
        private const val VIEW_TYPE_EVEN = 2
    }
}
