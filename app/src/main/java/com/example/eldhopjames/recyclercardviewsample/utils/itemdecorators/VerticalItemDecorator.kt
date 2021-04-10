package com.example.eldhopjames.recyclercardviewsample.utils.itemdecorators

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.eldhopjames.recyclercardviewsample.R

/**
 * Vertical item decorator with line decorator with starting space of needed
 *
 * @property paddingStarting
 * @constructor
 *
 * @param context
 */
@SuppressLint("UseCompatLoadingForDrawables")
internal class VerticalItemDecorator(context: Context, @Px private val paddingStarting: Int = 0) :
    ItemDecoration() {
    /**
     * divider
     */
    private val mDivider: Drawable by lazy { context.getDrawable(R.drawable.divider)!! }
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + paddingStarting
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }
}
