package com.example.eldhopjames.recyclercardviewsample.utils.itemdecorators

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

/**
 * space between the columns
 * @property space int  space
 * @property bottomSpace default zero
 * @property noOfRows
 */
internal class GridSpacesItemDecoration(
    @Px val space: Int,
    private val bottomSpace: Int = 0,
    private val noOfRows: Int
) :
    RecyclerView.ItemDecoration() {

    companion object {
        private const val ROWS_COUNT = 3
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (bottomSpace != 0) {
            outRect.bottom = bottomSpace
        }
        if (noOfRows == ROWS_COUNT) {
            if (parent.getChildLayoutPosition(view) % 2 == ROWS_COUNT) {
                outRect.left = space
            } else {
                outRect.right = space
            }
        } else if (noOfRows == 2) {
            if (parent.getChildLayoutPosition(view) % 2 == 1) {
                outRect.left = space
            } else {
                outRect.right = space
            }
        }
    }
}
