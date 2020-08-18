package com.example.eldhopjames.recyclercardviewsample.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.eldhopjames.recyclercardviewsample.databinding.OddListItemBinding
import com.example.eldhopjames.recyclercardviewsample.modelClass.ModelClass

class OddViewHolder(
    binding: OddListItemBinding, // with the help of "itemView" we ge the views from xml
    private var mListener: ((ModelClass, Int) -> Unit)?
) :
    ViewHolder(binding.root), View.OnClickListener {

    private val heading = binding.heading
    private val description = binding.description
    private var item: ModelClass? = null

    init {
        binding.root.setOnClickListener(this)
    }

    //Binding of data happens in here
    fun bindData(item: ModelClass) {
        this.item = item
        val position = adapterPosition
        if (position == RecyclerView.NO_POSITION) {
            return
        }
        with(item) {
            heading.text = head
            description.text = desc
        }
    }

    override fun onClick(v: View) {
        val position = adapterPosition // Get the index of the view holder
        if (position == RecyclerView.NO_POSITION && item == null) { // Makes sure this position is still valid
            return
        }
        /**
         * All the clicks will come in here*/
        if (v === itemView) {
            mListener?.invoke(
                item!!,
                position
            ) // we catch the click on the item view then pass it over the interface and then to our activity
        }
    }
}