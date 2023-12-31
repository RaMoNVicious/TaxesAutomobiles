package cg.viciousconcepts.taxesautomobiles.ui.widgets

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RulerOffsetDecorator(
    private val offset: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val itemPosition = parent.getChildAdapterPosition(view)

        with(outRect) {
            left = if (itemPosition == 0) offset else 0
            val itemCount: Int = (if (parent.adapter != null) parent.adapter?.itemCount else 0)!!
            right = if (itemPosition == itemCount - 1) offset else 0
        }
    }
}