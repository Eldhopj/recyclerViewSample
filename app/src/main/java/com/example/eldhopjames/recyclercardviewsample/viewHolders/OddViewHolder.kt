package com.example.eldhopjames.recyclercardviewsample.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.eldhopjames.recyclercardviewsample.databinding.OddListItemBinding
import com.example.eldhopjames.recyclercardviewsample.modelClass.ModelClass

class OddViewHolder(
    binding: OddListItemBinding, // with the help of "itemView" we ge the views from xml
    private val listener: ((ModelClass, Int) -> Unit)?
) :
    ViewHolder(binding.root), View.OnClickListener {

    private val heading = binding.heading
    private val description = binding.description
    private var item: ModelClass? = null

    init {
        itemView.setOnClickListener(this)
    }

    //Binding of data happens in here
    fun bindData(item: ModelClass) {
        this.item = item
        item.run {
            heading.text = head
            description.text = desc
        }
    }

    override fun onClick(v: View) {
        val position = bindingAdapterPosition // Get the index of the view holder
        if (position == RecyclerView.NO_POSITION) { // Makes sure this position is still valid
            return
        }
        item?.let {
            /**
             * All the clicks will come in here
             * */
            if (v === itemView) {
                listener?.invoke(
                    it,
                    position
                ) // we catch the click on the item view then pass it over the interface and then to our activity
            }
        }
    }
}
