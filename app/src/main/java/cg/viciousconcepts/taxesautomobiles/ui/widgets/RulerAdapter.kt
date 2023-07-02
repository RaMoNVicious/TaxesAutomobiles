package cg.viciousconcepts.taxesautomobiles.ui.widgets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import cg.viciousconcepts.taxesautomobiles.R
import cg.viciousconcepts.taxesautomobiles.databinding.WidgetRulerItemTickHeighBinding
import cg.viciousconcepts.taxesautomobiles.databinding.WidgetRulerItemTickLowBinding
import cg.viciousconcepts.taxesautomobiles.databinding.WidgetRulerItemTickMiddleBinding

class RulerAdapter(private val items: List<RulerItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class RulerTik { High, Middle, Low }

    data class RulerItem(
        val value: Int,
        val tik: RulerTik
    )

    override fun getItemViewType(position: Int): Int {
        return when (items[position].tik) {
            RulerTik.High -> TIK_HIGH
            RulerTik.Middle -> TIK_MIDDLE
            RulerTik.Low -> TIK_LOW
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TIK_HIGH -> TikHighVH(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.widget_ruler_item_tick_heigh, parent, false
                )
            )
            TIK_MIDDLE -> TikMiddleVH(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.widget_ruler_item_tick_middle, parent, false
                )
            )
            else -> TikLowVH(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.widget_ruler_item_tick_low, parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // nothing to do
    }

    override fun getItemCount(): Int = items.size


    inner class TikHighVH(binding: WidgetRulerItemTickHeighBinding) : RecyclerView.ViewHolder(binding.root)

    inner class TikMiddleVH(binding: WidgetRulerItemTickMiddleBinding) : RecyclerView.ViewHolder(binding.root)

    inner class TikLowVH(binding: WidgetRulerItemTickLowBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val TIK_HIGH = 0
        private const val TIK_MIDDLE = 1
        private const val TIK_LOW = 2
    }
}