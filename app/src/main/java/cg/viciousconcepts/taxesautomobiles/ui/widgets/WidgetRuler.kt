package cg.viciousconcepts.taxesautomobiles.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import cg.viciousconcepts.taxesautomobiles.R
import cg.viciousconcepts.taxesautomobiles.databinding.WidgetRulerBinding
import kotlin.math.max

class WidgetRuler(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val _binding: WidgetRulerBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.widget_ruler,
        this,
        true
    )

    private var _positionArrow: Int = 0

    private var _items: List<Int> = listOf()

    private var _index = 0

    private lateinit var _valueFormat: (value: Int) -> String

    init {
        _binding.lstValues.post {
            val halfWidth = _binding.lstValues.measuredWidth / 2
            _binding.lstValues.addItemDecoration(RulerOffsetDecorator(halfWidth))
            _positionArrow = halfWidth
        }
        LinearSnapHelper().attachToRecyclerView(_binding.lstValues)
    }

    fun setItems(
        items: List<Int>,
        selected: Int,
        valueFormat: (value: Int) -> String = { value -> "$value"},
        tikHigh: Int = 1000,
        tikMiddle: Int = 500,
        onSelected: (selected: Int) -> Unit
    ) {
        _items = items
        _valueFormat = valueFormat

        setupAdapter(tikHigh, tikMiddle)

        setupRuler(selected, onSelected)
    }

    private fun setupAdapter(tikHigh: Int, tikMiddle: Int) {
        _binding.lstValues.adapter = RulerAdapter(_items.map {
            RulerAdapter.RulerItem(
                value = it,
                tik = when {
                    it % tikHigh == 0 -> RulerAdapter.RulerTik.High
                    it % tikMiddle == 0 -> RulerAdapter.RulerTik.Middle
                    else -> RulerAdapter.RulerTik.Low
                }
            )
        })
    }

    private fun setupRuler(selected: Int, onSelected: (selected: Int) -> Unit) {
        _binding.apply {
            lstValues.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    (lstValues.layoutManager as LinearLayoutManager).let { lm ->
                        (0 until lm.childCount).forEach { i ->
                            lm.getChildAt(i)?.let { child ->
                                val position = IntArray(2)
                                child.getLocationOnScreen(position)
                                val width = child.measuredWidth

                                if (_positionArrow > position[0] && _positionArrow < position[0] + width) {
                                    _index = recyclerView.getChildAdapterPosition(child)
                                    updateDisplayValue()
                                    return@forEach
                                }
                            }
                        }
                    }
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        onSelected(_items[_index])
                    }
                }
            })

            (lstValues.layoutManager as LinearLayoutManager).let {
                val smoothScroller = CenterSmoothScroller(_binding.lstValues.context)
                val selectedItem = max(0, _items.indexOf(selected))

                Log.d("Ruler", "Scrolling to item = $selected on position = $selectedItem")
                smoothScroller.targetPosition = selectedItem

                lstValues.post {
                    it.startSmoothScroll(smoothScroller)
                }
            }
        }
    }

    fun setDisabled(isDisabled: Boolean) {
        _binding.isDisabled = isDisabled
    }

    fun updateDisplayValue() {
        _binding.txtValue.text = _valueFormat(_items[_index])
    }

    inner class CenterSmoothScroller(context: Context) : LinearSmoothScroller(context) {

        override fun calculateDtToFit(viewStart: Int, viewEnd: Int, boxStart: Int, boxEnd: Int, snapPreference: Int): Int {
            return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2)  - _positionArrow
        }
    }

}