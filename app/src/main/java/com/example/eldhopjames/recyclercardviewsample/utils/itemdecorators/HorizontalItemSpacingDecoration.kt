package com.example.eldhopjames.recyclercardviewsample.utils.itemdecorators

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

/**
 * Horizontal item spacing decoration with starting space and space between items
 *
 * @property itemSpacing
 * @property starting
 * @constructor Create empty Horizontal item spacing decoration
 */
internal class HorizontalItemSpacingDecoration(
    @Px private val itemSpacing: Int,
    @Px private val starting: Int = 0
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val count = parent.adapter?.itemCount ?: 0
        val position = parent.getChildAdapterPosition(view)
        val leading = if (position == 0) starting else itemSpacing
        val trailing = if (position == count - 1) starting else 0
        outRect.run {
            left = leading
            right = trailing
        }
    }
}
